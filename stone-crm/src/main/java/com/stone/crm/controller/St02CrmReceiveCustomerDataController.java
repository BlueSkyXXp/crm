
package com.stone.crm.controller;

import java.util.*;
import java.util.regex.Pattern;

import com.alibaba.fastjson2.JSONObject;
import com.stone.common.annotation.Log;
import com.stone.common.enums.BusinessType;
import com.stone.common.utils.StringUtils;
import com.stone.crm.domain.*;
import com.stone.crm.domain.vo.CytReceiveData;
import com.stone.crm.service.ISt02CrmCustomerReceiveTestService;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.crm.service.ISt02CrmReceiveCytService;
import com.stone.crm.util.AesUtils;
import com.stone.crm.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.crm.service.ISt02CrmCustomerNewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 接收来自外部的数据Controller
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
@Api(value = "进线接口", tags = {"进线接口"})
@RestController
@RequestMapping("/crm/raw_customer")
public class St02CrmReceiveCustomerDataController extends BaseController
{

    @Autowired
    private ISt02CrmCustomerNewService st02CrmCustomerNewService;
    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISt02CrmReceiveCytService receiveCytService;

    @Autowired
    private ISt02CrmCustomerReceiveTestService st02CrmCustomerReceiveTestService;

    Map<String, String> realTokenMap = new HashMap<String, String>(){
        {
            put("test1", "eQgJuzGIt09JrRjQlgiUhRCIy03wgOab");
            put("test2", "xbdGeNGlyNGkiYj4FFS7xHVLkXW8FDJIcy");
        }
    };

    Map<String, String> testTokenMap = new HashMap<String, String>(){
        {
            put("test1", "mWTvouohGzbaoBVpak5pIl31stfv");
            put("test2", "exy9RDaUNnGpUC3gXoTG2noVuzEYy");
        }
    };

    private static final Set<Integer> statusSet = new HashSet<>(Arrays.asList( 0, 1, 2));

    /**
     * 客户撞库校验
     */
    @ApiOperation("客户撞库校验")
    @Log(title = "cyt客户撞库校验", businessType = BusinessType.EXPORT)
    @PostMapping(value = "/check")
    public AjaxResult check(@RequestBody CytReceiveData cytReceiveData, @RequestHeader(name = "token", required = true)String token)
    {
        logger.info("cyt客户撞库校验", cytReceiveData, token);
        if (!realTokenMap.containsKey(cytReceiveData.getBusinessCode())) {
            return AjaxResult.error("The required parameter assign_id is not valid.");
        }

        int tokenFlag = 0;  //  0表示token不正确， 1 表示真实token， 2 表示测试token。

        if (token == null) {
            return AjaxResult.error("The specified token does not exist.");
        }

        if (token.equals(realTokenMap.get(cytReceiveData.getBusinessCode()))) {
            tokenFlag = 1;
        } else if (token.equals(testTokenMap.get(cytReceiveData.getBusinessCode()))) {
            tokenFlag = 2;
        }

        if (tokenFlag == 0) {
            return AjaxResult.error("The specified token is invalid.");
        }

        if (StringUtils.isEmpty(cytReceiveData.getData())) {
            return AjaxResult.error("The required data is not supplied.");
        }

        String data = AesUtils.decrypt(cytReceiveData.getData());
        St02CrmReceiveCyt crmReceiveCyt = JSONObject.parseObject(data, St02CrmReceiveCyt.class);

        if (crmReceiveCyt.getPhone() == null) {
            return AjaxResult.error("The required parameter phone is not supplied.");
        }
        St02CrmCustomer customer = new St02CrmCustomer();
        customer.setPhone(crmReceiveCyt.getPhone());
        List<St02CrmCustomer> customerList = (st02CrmCustomerService.selectSt02CrmCustomerList(customer));
        List<String> res = new ArrayList<>();
        if (customerList == null || customerList.isEmpty()) {
            St02CrmCustomerNew customerNew = new St02CrmCustomerNew();
            customerNew.setPhone(crmReceiveCyt.getPhone());
            List<St02CrmCustomerNew> customerNewList = st02CrmCustomerNewService.selectSt02CrmCustomerNewList(customerNew);
            if (customerNewList == null || customerNewList.isEmpty()) {
                St02CrmCustomerReceiveTest crmCustomerReceiveTest = new St02CrmCustomerReceiveTest();
                crmCustomerReceiveTest.setPhone(crmReceiveCyt.getPhone());
                List<St02CrmCustomerReceiveTest> crmCustomerReceiveTestList = st02CrmCustomerReceiveTestService.selectSt02CrmCustomerReceiveTestList(crmCustomerReceiveTest);
                if (crmCustomerReceiveTestList == null || crmCustomerReceiveTestList.isEmpty()) {
                    return AjaxResult.success("操作成功", res);
                }
                for (St02CrmCustomerReceiveTest customer3 : crmCustomerReceiveTestList) {
                    res.add(MD5Utils.md5(customer3.getPhone()));
                }
                return AjaxResult.success("操作成功", res);
            }
            for (St02CrmCustomerNew customer2 : customerNewList) {
                res.add(MD5Utils.md5(customer2.getPhone()));
            }
            return AjaxResult.success("操作成功", res);
        }
        for (St02CrmCustomer customer1 : customerList) {
            res.add(MD5Utils.md5(customer1.getPhone()));
        }

        return AjaxResult.success("操作成功", res);
    }


    /**
     * 新增待分配客户
     */
    @ApiOperation("新增待分配客户")
    @Log(title = "cyt进线接口", businessType = BusinessType.INSERT)
    @PostMapping(value = "/createCyt")
    public AjaxResult createCyt(@RequestBody CytReceiveData cytReceiveData, @RequestHeader(name = "token", required = true)String token)
    {
        logger.info("cyt进线接口", cytReceiveData, token);

        if (!realTokenMap.containsKey(cytReceiveData.getBusinessCode())) {
            return AjaxResult.error("The required parameter assign_id is not valid.");
        }

        int tokenFlag = 0;  //  0表示token不正确， 1 表示真实token， 2 表示测试token。

        if (token == null) {
            return AjaxResult.error("The specified token does not exist.");
        }

        if (token.equals(realTokenMap.get(cytReceiveData.getBusinessCode()))) {
            tokenFlag = 1;
        } else if (token.equals(testTokenMap.get(cytReceiveData.getBusinessCode()))) {
            tokenFlag = 2;
        }

        if (tokenFlag == 0) {
            return AjaxResult.error("The specified token is invalid.");
        }

        if (StringUtils.isEmpty(cytReceiveData.getData())) {
            return AjaxResult.error("The required data is not supplied.");
        }

        String data = AesUtils.decrypt(cytReceiveData.getData());
        St02CrmReceiveCyt crmReceiveCyt = JSONObject.parseObject(data, St02CrmReceiveCyt.class);

        if (crmReceiveCyt.getName() == null) {
            return AjaxResult.error("The required parameter name is not supplied.");
        } else if (crmReceiveCyt.getName().length() > 128) {
            return AjaxResult.error("The specified name is invalid.");
        }


        if (crmReceiveCyt.getPhone() == null) {
            return AjaxResult.error("The required parameter phone is not supplied.");
            // https://developer.aliyun.com/article/1180998
        } else if (!Pattern.compile("^(13[0-9]|14[5-9]|15[0-3,5-9]|16[6]|17[0-8]|18[0-9]|19[8,9])\\d{8}$").matcher(crmReceiveCyt.getPhone()).matches()) {
            return AjaxResult.error("The specified mobile is invalid.");
        }


        if (crmReceiveCyt.getAge() == null) {
            return AjaxResult.error("The required parameter age is not supplied.");
        }

        if (crmReceiveCyt.getGender() == null) {
            return AjaxResult.error("The required parameter gender is not supplied.");
        } else if (!statusSet.contains(crmReceiveCyt.getGender())) {
            return AjaxResult.error("The specified sex is invalid.");
        }


        if (crmReceiveCyt.getIp() == null) {
            return AjaxResult.error("The required parameter IP is not supplied.");
        }

        if (crmReceiveCyt.getCity() == null) {
            return AjaxResult.error("The required parameter city is not supplied.");
        }

        if (crmReceiveCyt.getProfession() == null) {
            return AjaxResult.error("The required parameter profession is not supplied.");
        }

        if (crmReceiveCyt.getLoanAmount() == null) {
            return AjaxResult.error("The required parameter loanAmount is not supplied.");
        }

        if (crmReceiveCyt.getSesameSeed() == null) {
            return AjaxResult.error("The required parameter sesameSeed is not supplied.");
        }

        if (crmReceiveCyt.getSocialSecurity() == null) {
            return AjaxResult.error("The required parameter socialSecurity is not supplied.");
        }

        if (crmReceiveCyt.getProvidentFund() == null) {
            return AjaxResult.error("The required parameter providentFund is not supplied.");
        }

        if (crmReceiveCyt.getVehicle() == null) {
            return AjaxResult.error("The required parameter Vehicle is not supplied.");
        } else if (!statusSet.contains(crmReceiveCyt.getVehicle())) {
            return AjaxResult.error("The specified Vehicle is invalid.");
        }

        if (crmReceiveCyt.getHouse() == null) {
            return AjaxResult.error("The required parameter House is not supplied.");
        } else if (!statusSet.contains(crmReceiveCyt.getHouse())) {
            return AjaxResult.error("The specified House is invalid.");
        }

        St02CrmCustomerNew customerNew = new St02CrmCustomerNew();
        customerNew.setSource(cytReceiveData.getBusinessCode());
        customerNew.setName(crmReceiveCyt.getName());
        customerNew.setPhone(crmReceiveCyt.getPhone());
        customerNew.setAge(crmReceiveCyt.getAge());

        if (crmReceiveCyt.getGender() == 1) {
            customerNew.setSex(0L);
        } else if (crmReceiveCyt.getGender() == 2) {
            customerNew.setSex(1L);
        }

        customerNew.setCity(crmReceiveCyt.getCity());
        customerNew.setPropertyInfo(crmReceiveCyt.getHouse() == 1 ? 1L : 2L);

//        公积金
        if (crmReceiveCyt.getProvidentFund() < 4) {
            customerNew.setAccFund(1L);
        }
//        社保
        if (crmReceiveCyt.getSocialSecurity() < 4) {
            customerNew.setSocialSecurity(1L);
        }

        customerNew.setCarInfo(crmReceiveCyt.getVehicle() == 1 ? 1L : 2L);
//        职业
        if (crmReceiveCyt.getProfession() != 2) {
            customerNew.setProfessional(1L);
        }
//        需求金额
        if (crmReceiveCyt.getLoanAmount() == 1) {
            customerNew.setMinDemandMoney(30000L);
            customerNew.setMaxDemandMoney(50000L);
        } else if (crmReceiveCyt.getLoanAmount() == 2) {
            customerNew.setMinDemandMoney(50000L);
            customerNew.setMaxDemandMoney(100000L);
        } else if (crmReceiveCyt.getLoanAmount() == 3) {
            customerNew.setMinDemandMoney(100000L);
            customerNew.setMaxDemandMoney(200000L);
        }

        St02CrmCustomerReceiveTest rawCustomerTest = new St02CrmCustomerReceiveTest();

        BeanUtils.copyProperties(customerNew, rawCustomerTest);
        rawCustomerTest.setId(null);

        try {
            if (tokenFlag == 2) {
                st02CrmCustomerReceiveTestService.insertSt02CrmCustomerReceiveTest(rawCustomerTest);
            } else {
                st02CrmCustomerNewService.insertSt02CrmCustomerNew(customerNew);
            }
            receiveCytService.insertSt02CrmReceiveCyt(crmReceiveCyt);
        } catch (Exception e) {
            if (!(e instanceof DuplicateKeyException)) {
                // 如果是其他类型的异常，打印错误日志
                logger.error("inser St02CrmCustomer by createCyt openapi:", e, crmReceiveCyt);
                return AjaxResult.error();
            }
            return AjaxResult.error();
        }

        return AjaxResult.success();
    }


    /**
     * 新增待分配客户
     */
    @ApiOperation("新增待分配客户")
    @Log(title = "qx期贷进线接口", businessType = BusinessType.INSERT)
    @PostMapping(value = "/create")
    public AjaxResult create(@RequestBody St02CrmReceiveData data, @RequestHeader(name = "token", required = true)String token)
    {
        logger.info("qx期贷进线接口", data, token);

        if (!realTokenMap.containsKey(data.getAssignId())) {
            return AjaxResult.error("The required parameter assign_id is not valid.");
        }

        int tokenFlag = 0;  //  0表示token不正确， 1 表示真实token， 2 表示测试token。

        if (token == null) {
            return AjaxResult.error("The specified token does not exist.");
        }

        if (token.equals(realTokenMap.get(data.getAssignId()))) {
            tokenFlag = 1;
        } else if (token.equals(testTokenMap.get(data.getAssignId()))) {
            tokenFlag = 2;
        }

        if (tokenFlag == 0) {
            return AjaxResult.error("The specified token is invalid.");
        }

        if (data.getName() == null) {
            return AjaxResult.error("The required parameter name is not supplied.");
        } else if (data.getName().length() > 128) {
            return AjaxResult.error("The specified name is invalid.");
        }


        if (data.getMobile() == null) {
            return AjaxResult.error("The required parameter mobile is not supplied.");
            // https://developer.aliyun.com/article/1180998
        } else if (!Pattern.compile("^(13[0-9]|14[5-9]|15[0-3,5-9]|16[6]|17[0-8]|18[0-9]|19[8,9])\\d{8}$").matcher(data.getMobile()).matches()) {
            return AjaxResult.error("The specified mobile is invalid.");
        }


        if (data.getAge() == null) {
            return AjaxResult.error("The required parameter age is not supplied.");
        } else if (data.getAge() < 18 || data.getAge() > 120) {
            return AjaxResult.error("The specified age is invalid.");
        }

        if (data.getSex() == null) {
            return AjaxResult.error("The required parameter sex is not supplied.");
//        } else if (data.getSex() != -1 && data.getSex() != 0 && data.getSex() != 1) {
        } else if (!statusSet.contains(data.getSex())) {
            return AjaxResult.error("The specified sex is invalid.");
        }

        if (data.getCity() == null) {
            return AjaxResult.error("The required parameter city is not supplied.");
        }

        if (data.getHasHouse() == null) {
            return AjaxResult.error("The required parameter hasHouse is not supplied.");
//        } else if (data.getHasHouse() != -1 && data.getHasHouse() != 0 && data.getHasHouse() != 1) {
        } else if (!statusSet.contains(data.getHasHouse())) {
            return AjaxResult.error("The specified hasHouse is invalid.");
        }

        if (data.getHasCar() == null) {
            return AjaxResult.error("The required parameter hasCar is not supplied.");
//        } else if (data.getHasCar() != -1 && data.getHasCar() != 0 && data.getHasCar() != 1) {
        } else if (!statusSet.contains(data.getHasCar())) {
            return AjaxResult.error("The specified hasCar is invalid.");
        }

        if (data.getHasInsurance() == null) {
            return AjaxResult.error("The required parameter hasInsurance is not supplied.");
//        } else if (data.getHasInsurance() != -1 && data.getHasInsurance() != 0 && data.getHasInsurance() != 1) {
        } else if (!statusSet.contains(data.getHasInsurance())) {
            return AjaxResult.error("The specified hasInsurance is invalid.");
        }

//        if (data.getHasSocialSecurity() == null) {
//            return AjaxResult.error("The required parameter hasSocialSecurity is not supplied.");
//        } else if (!statusSet.contains(data.getHasSocialSecurity())) {
//            return AjaxResult.error("The specified hasSocialSecurity is invalid.");
//        }

        if (data.getHasCredit() == null) {
            return AjaxResult.error("The required parameter hasCredit is not supplied.");
//        } else if (data.getHasCredit() != -1 && data.getHasCredit() != 0 && data.getHasCredit() != 1) {
        } else if (!statusSet.contains(data.getHasCredit())) {
            return AjaxResult.error("The specified hasCredit is invalid.");
        }
        if (data.getHasFund() == null) {
            return AjaxResult.error("The required parameter hasFund is not supplied.");
//        } else if (data.getHasFund() != -1 && data.getHasFund() != 0 && data.getHasFund() != 1) {
        } else if (!statusSet.contains(data.getHasFund())) {
            return AjaxResult.error("The specified hasFund is invalid.");
        }

        if (data.getHasWork() == null) {
            return AjaxResult.error("The required parameter hasWork is not supplied.");
//        } else if (data.getHasWork() != -1 && data.getHasWork() != 0 && data.getHasWork() != 1) {
        } else if (!statusSet.contains(data.getHasWork())) {
            return AjaxResult.error("The specified hasWork is invalid.");
        }

        if (data.getMoneyDemand() == null) {
            return AjaxResult.error("The required parameter moneyDemand is not supplied.");
        }

        if (data.getWebank() == null) {
            return AjaxResult.error("The required parameter webank is not supplied.");
//        } else if (data.getWebank() != -1 && data.getWebank() != 0 && data.getWebank() != 1) {
        } else if (!statusSet.contains(data.getWebank())) {
            return AjaxResult.error("The specified hasHouse is invalid.");
        }



        St02CrmCustomerNew customerNew = new St02CrmCustomerNew();
        customerNew.setSource(data.getAssignId());
        customerNew.setName(data.getName());
        customerNew.setPhone(data.getMobile());
        customerNew.setAge(String.valueOf(data.getAge()));

        if (data.getSex() == 1) {
            customerNew.setSex(0L);
        } else if (data.getSex() == 2) {
            customerNew.setSex(1L);
        }

        customerNew.setCity(data.getCity());
        customerNew.setPropertyInfo(Long.valueOf(data.getHasHouse()));
        customerNew.setPolicyInfo(Long.valueOf(data.getHasInsurance()));
        customerNew.setCreditCardInfo(Long.valueOf(data.getHasCredit()));
        customerNew.setAccFund(Long.valueOf(data.getHasFund()));

        if (data.getHasSocialSecurity() != null) {
            customerNew.setSocialSecurity(Long.valueOf(data.getHasSocialSecurity()));
        }

        customerNew.setCarInfo(Long.valueOf(data.getHasCar()));
        customerNew.setProfessional(Long.valueOf(data.getHasWork()));
        customerNew.setLoanMoney(Long.valueOf(data.getMoneyDemand()));

        St02CrmCustomerReceiveTest rawCustomerTest = new St02CrmCustomerReceiveTest();

        BeanUtils.copyProperties(customerNew, rawCustomerTest);
        rawCustomerTest.setId(null);

        try {
            if (tokenFlag == 2) {
                st02CrmCustomerReceiveTestService.insertSt02CrmCustomerReceiveTest(rawCustomerTest);
            } else {
                st02CrmCustomerNewService.insertSt02CrmCustomerNew(customerNew);
            }

        } catch (Exception e) {
            if (!(e instanceof DuplicateKeyException)) {
                // 如果是其他类型的异常，打印错误日志
                logger.error("inser St02CrmCustomer by create openapi:", e, data);
                return AjaxResult.error();
            }
            return AjaxResult.error();
        }

        return AjaxResult.success();
    }

}
