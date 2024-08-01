package com.stone.crm.controller;

import com.stone.common.annotation.Log;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.page.TableDataInfo;
import com.stone.common.enums.BusinessType;
import com.stone.common.utils.SecurityUtils;
import com.stone.common.utils.StringUtils;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmCustomerFlowInfo;
import com.stone.crm.domain.St02CrmCustomerFollowInfo;
import com.stone.crm.domain.vo.CustomerAssignVo;
import com.stone.crm.domain.vo.CustomerReqVo;
import com.stone.crm.domain.vo.CustomerVo;
import com.stone.crm.service.ISt02CrmCustomerFlowInfoService;
import com.stone.crm.service.ISt02CrmCustomerFollowInfoService;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.crm.util.BeanUtil;
import com.stone.common.utils.DateUtil;
import com.stone.framework.websocket.WebSocketServer;
import com.stone.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 客户Controller
 *
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Api(value = "客户", tags = {"客户"})
@RestController
@RequestMapping("/crm/customer")
public class St02CrmCustomerController extends BaseController {
    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISt02CrmCustomerFlowInfoService st02CrmCustomerFlowInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISt02CrmCustomerFollowInfoService followInfoService;

    /**
     * 查询客户列表
     */
    @ApiOperation("我的客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody CustomerVo customerVo) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 设置为当前用户
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        customerReqVo.setUserId(user.getUserId().toString());
        startPage();
        List<St02CrmCustomer> list = st02CrmCustomerService.selectCrmCustomerListOwner(customerReqVo);

        return addFollowInfoRemark(customerReqVo, list);
    }


    /**
     * 查询客户列表
     */
    @ApiOperation("我的未处理客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/list/unprocess")
    public TableDataInfo unprocessList(@RequestBody CustomerVo customerVo) {
        startPage();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 设置为当前用户, 已经状态
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        customerReqVo.setUserId(user.getUserId().toString());
        customerReqVo.setStatus(1L);
        List<St02CrmCustomer> list = st02CrmCustomerService.selectCrmCustomerList(customerReqVo);
        return getDataTable(list);
    }

    /**
     * 查询客户列表
     */
    @ApiOperation("团队客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/list/team")
    public TableDataInfo teamList(@RequestBody CustomerVo customerVo) {
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        if(!StringUtils.isEmpty(customerVo.getUserId())) {
            customerReqVo.setUserId(customerVo.getUserId());
        } else {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            SysUser sysUser = new SysUser();
            sysUser.setDeptId(user.getDeptId());
            List<SysUser> userList = sysUserService.selectUserList(sysUser);
            List<Long> userIds = new ArrayList<>();
            for (SysUser sysUser1 : userList) {
                if (user.getUserId().equals(sysUser1.getUserId())) {
                    continue;
                }
                userIds.add(sysUser1.getUserId());
            }
            String ids = userIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            customerReqVo.setUserId(ids);
        }
        startPage();
        List<St02CrmCustomer> list = st02CrmCustomerService.selectTeamCustomerList(customerReqVo);

        return addFollowInfoRemark(customerReqVo, list);
    }


    /**
     * 查询客户列表
     */
    @ApiOperation("公共池")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/list/public")
    public TableDataInfo publicList(@RequestBody CustomerVo customerVo) {
        startPage();
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        List<St02CrmCustomer> list = st02CrmCustomerService.selectCustomerPublicList(customerReqVo);

        return addFollowInfoRemark(customerReqVo, list);
    }

    private TableDataInfo addFollowInfoRemark(CustomerReqVo customerReqVo, List<St02CrmCustomer> list) {
        for (St02CrmCustomer customer : list) {
            St02CrmCustomerFollowInfo followInfo = new St02CrmCustomerFollowInfo();
            followInfo.setCustomerId(customer.getId());
            if (!StringUtils.isEmpty(customerReqVo.getRemark())) {
                followInfo.setRemark(customerReqVo.getRemark());
            }
            List<St02CrmCustomerFollowInfo> followInfoList = followInfoService.selectFollowInfoListLimit(followInfo);
            List<String> remarks = new ArrayList<>();
            for (St02CrmCustomerFollowInfo follow : followInfoList) {
                String remark = StringUtils.isEmpty(follow.getRemark()) ? "-" : follow.getRemark();
                String format = String.format("%s %s %s", remark, follow.getOperator(), DateUtil.timestampToDateString(follow.getCreateTime().getTime()));
                remarks.add(format);
            }
            if (!remarks.isEmpty()) {
                customer.setRemark(remarks.stream().map(String::valueOf).collect(Collectors.joining("\n")));
            }
        }
        return getDataTable(list);
    }


    /**
     * 查询客户列表
     */
    @ApiOperation("离职人员客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/list/resign")
    public TableDataInfo resignList(@RequestBody CustomerVo customerVo) {
        startPage();
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        customerReqVo.setUserId("-1");
        List<St02CrmCustomer> list = st02CrmCustomerService.selectCrmCustomerList(customerReqVo);
        return getDataTable(list);
    }


    /**
     * 导出客户列表
     */
    @ApiOperation("导出客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmCustomer st02CrmCustomer) {
        List<St02CrmCustomer> list = st02CrmCustomerService.selectSt02CrmCustomerList(st02CrmCustomer);
        ExcelUtil<St02CrmCustomer> util = new ExcelUtil<St02CrmCustomer>(St02CrmCustomer.class);
        util.exportExcel(response, list, "客户数据");
    }


    /**
     * 获取客户详细信息
     */
    @ApiOperation("获取客户详细信息")
    @PreAuthorize("@ss.hasPermi('crm:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(st02CrmCustomerService.selectSt02CrmCustomerById(id));
    }


    /**
     * 新增客户
     */
    @ApiOperation("新增客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmCustomer st02CrmCustomer) {
        return toAjax(st02CrmCustomerService.insertSt02CrmCustomer(st02CrmCustomer));
    }


    /**
     * 修改客户
     */
    @ApiOperation("修改客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmCustomer st02CrmCustomer) {
        return toAjax(st02CrmCustomerService.updateSt02CrmCustomer(st02CrmCustomer));
    }

    /**
     * 删除客户
     */
    @ApiOperation("删除客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(st02CrmCustomerService.deleteSt02CrmCustomerByIds(ids));
    }

    /**
     * 分配客户
     */
    @ApiOperation("分配客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:assign')")
    @Log(title = "客户", businessType = BusinessType.ASSIGN)
    @PutMapping("/assign")
    public AjaxResult assignCustomer2PublicPool(@RequestBody CustomerAssignVo customerAssignVo) {
        long userId = customerAssignVo.getUserId();
        SysUser user1 = sysUserService.selectUserById(userId);
        List<Long> idArr = customerAssignVo.getCustomerIds();
        St02CrmCustomer customer1 = new St02CrmCustomer();
        customer1.setUserId(userId);
        List<St02CrmCustomer> customerList = st02CrmCustomerService.selectSt02CrmCustomerList(customer1);
        if (customerList.size() + idArr.size() > 300) {
            return AjaxResult.error("Customer pool is full!!!");
        }
        // 新增分配记录
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (idArr == null || idArr.isEmpty()) {
            return toAjax(0);
        }
        Map map = new HashMap<Long, Long[]>();
        map.put("userId", userId);
        map.put("customerIds", idArr.toArray(new Long[0]));
        int rows = st02CrmCustomerService.assignCustomerUserIdByIds(map);
        for (Long id : idArr) {
            St02CrmCustomer customer = st02CrmCustomerService.selectSt02CrmCustomerById(id);
            St02CrmCustomerFlowInfo customerFlowInfo = new St02CrmCustomerFlowInfo();
            customerFlowInfo.setCustomerId(id.toString());
            customerFlowInfo.setUserId(user.getUserId());
            customerFlowInfo.setUserName(user.getUserName());
            customerFlowInfo.setTargetPlat(user1.getUserName());
//            if (customer.getUserId() == null) {
                customerFlowInfo.setSourcePlat("公共池");
//            } else {
//                SysUser sysUser = sysUserService.selectUserById(customer.getUserId());
//                customerFlowInfo.setSourcePlat(sysUser.getUserName());
//            }
            St02CrmCustomer customerNew = new St02CrmCustomer();
            customerNew.setStatus(1L);
            customerNew.setId(customer.getId());
            customerNew.setLastAssignTime(new Date());
            if (customer.getFollowTimes() == 0) {
                customerNew.setFirstAssignTime(new Date());
            }
            customerNew.setFollowTimes(customer.getFollowTimes()+1);
            st02CrmCustomerService.updateSt02CrmCustomer(customerNew);
            st02CrmCustomerFlowInfoService.insertSt02CrmCustomerFlowInfo(customerFlowInfo);
        }
        try {
            WebSocketServer.sendInfo(user.getUserName() + "给你分配了" + idArr.size() + "个新客户", String.valueOf(userId));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return toAjax(rows);
    }


    /**
     * 移动客户至公共池
     */
    @ApiOperation("移动客户至公共池")
    @PreAuthorize("@ss.hasPermi('crm:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.ASSIGN)
    @PutMapping("/public")
    public AjaxResult removeCustomer2PublicPool(@RequestBody CustomerAssignVo customerAssignVo) {
        List<Long> idArr = customerAssignVo.getCustomerIds();
        // 新增分配记录
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (idArr == null || idArr.isEmpty()) {
            return toAjax(0);
        }
        int rows = st02CrmCustomerService.removeCustomerUserIdByIds(idArr.toArray(new Long[0]));
        for (Long id : idArr) {
            St02CrmCustomerFlowInfo customerFlowInfo = new St02CrmCustomerFlowInfo();
            customerFlowInfo.setCustomerId(id.toString());
            customerFlowInfo.setUserId(user.getUserId());
            customerFlowInfo.setUserName(user.getUserName());
            customerFlowInfo.setTargetPlat("公共池");
            customerFlowInfo.setSourcePlat(user.getUserName());
            st02CrmCustomerFlowInfoService.insertSt02CrmCustomerFlowInfo(customerFlowInfo);
        }
        return toAjax(rows);
    }


    /**
     * 客户来源
     */
    @ApiOperation("客户来源")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/source")
    public TableDataInfo selectLoanChannelType() {
        startPage();
        List<String> sources = st02CrmCustomerService.selectLoanChannelType();
        return getDataTable(sources);
    }
}
