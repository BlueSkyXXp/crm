package com.stone.crm.controller;

import com.stone.common.annotation.Log;
import com.stone.common.annotation.RateLimiter;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.enums.BusinessType;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmVisitInfo;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.crm.service.ISt02CrmVisitInfoService;
import com.stone.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "扫码签到", tags = {"扫码签到"})
@RestController
public class St02CrmQianDaoController extends BaseController {

    @Autowired
    private ISt02CrmVisitInfoService st02CrmVisitInfoService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISysUserService sysUserService;
    /**
     * 新增客户来访
     */
    @ApiOperation("新增客户来访")
    @Log(title = "客户来访", businessType = BusinessType.INSERT)
    @PostMapping("/qiandao/jLiljpZlRD8yqqpuiedJZVE1ltyXuUjf")
    @RateLimiter(key = "qiandao", time = 5, count = 1)
    public AjaxResult add(@RequestBody St02CrmVisitInfo st02CrmVisitInfo)
    {
        logger.info("qindao request", st02CrmVisitInfo);

        St02CrmCustomer st02CrmCustomer = new St02CrmCustomer();
        st02CrmCustomer.setPhone(st02CrmVisitInfo.getPhone());
        List<St02CrmCustomer> customer = st02CrmCustomerService.selectSt02CrmCustomerList(st02CrmCustomer);
        if (customer.isEmpty()) {
            st02CrmVisitInfo.setCustomerId(-1L);
            St02CrmCustomer customerNew = new St02CrmCustomer();
            customerNew.setId(customer.get(0).getId());
            customerNew.setStatus(3L);
            st02CrmCustomerService.updateSt02CrmCustomer(customerNew);
        } else {
            st02CrmVisitInfo.setCustomerId(customer.get(0).getId());
        }
        SysUser sysUser = sysUserService.selectUserByUserName(st02CrmVisitInfo.getUserName());
        if (sysUser == null) {
            return AjaxResult.error("客户经理名字不存在,请检查后再录入!!!");
        }

        st02CrmVisitInfo.setUserId(sysUser.getUserId());
        return toAjax(st02CrmVisitInfoService.insertSt02CrmVisitInfo(st02CrmVisitInfo));
    }

}
