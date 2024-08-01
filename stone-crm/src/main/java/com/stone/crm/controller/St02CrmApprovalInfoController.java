package com.stone.crm.controller;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.domain.model.LoginUser;
import com.stone.common.utils.SecurityUtils;
import com.stone.crm.domain.St02CrmApprovalResult;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.vo.*;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.system.service.ISysDeptService;
import com.stone.system.service.ISysRoleService;
import com.stone.system.service.ISysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stone.common.annotation.Log;
import io.swagger.annotations.Api;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.enums.BusinessType;
import com.stone.crm.domain.St02CrmApprovalInfo;
import com.stone.crm.service.ISt02CrmApprovalInfoService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 审件Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Api(value = "审件", tags = {"审件"})
@RestController
@RequestMapping("/crm/approval_info")
public class St02CrmApprovalInfoController extends BaseController
{
    @Autowired
    private ISt02CrmApprovalInfoService st02CrmApprovalInfoService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 综合业绩排行
     */
    @ApiOperation("综合业绩排行")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:list')")
    @PostMapping("/result")
    public TableDataInfo list(@RequestBody ApprovalVo approvalVo)
    {

        ApprovalReqVo approvalReqVo = new ApprovalReqVo(approvalVo);
        List<St02CrmApprovalResult> list = new ArrayList<>();
        switch (approvalReqVo.getType()) {
            case "person":
                startPage();
                List<St02CrmApprovalResult> flowList = st02CrmApprovalInfoService.selectPersonFlowList(approvalReqVo);
                Map<Long, St02CrmApprovalResult> map = new HashMap<>();
                for (St02CrmApprovalResult result : flowList) {
                    map.put(result.getUserId(), result);
                }
                List<St02CrmApprovalResult> visitList = st02CrmApprovalInfoService.selectPersonVisitList(approvalReqVo);
                for (St02CrmApprovalResult visit : visitList) {
                    if (!map.containsKey(visit.getUserId())) {
                        continue;
                    }
                    St02CrmApprovalResult result = map.get(visit.getUserId());
                    result.setVisitDailyCnt(visit.getVisitDailyCnt() == null ? 0 : visit.getVisitDailyCnt());
                    result.setVisitMonthlyCnt(visit.getVisitMonthlyCnt() == null ? 0 : visit.getVisitMonthlyCnt());
                }
                List<St02CrmApprovalResult> resultList = st02CrmApprovalInfoService.selectPersonResultList(approvalReqVo);
                for (St02CrmApprovalResult approvalResult : resultList) {
                    if (!map.containsKey(approvalResult.getUserId())) {
                        continue;
                    }
                    St02CrmApprovalResult result = map.get(approvalResult.getUserId());
                    result.setUserName(approvalResult.getUserName());
                    result.setMonthlyRevenue(approvalResult.getMonthlyRevenue());
                    result.setMonthlyCnt(approvalResult.getMonthlyCnt());
                    result.setMonthlyPayCnt(approvalResult.getMonthlyPayCnt());
                    result.setDailyRevenue(approvalResult.getDailyRevenue());
                    result.setDailyCnt(approvalResult.getDailyCnt());
                    result.setDailyPayCnt(approvalResult.getDailyPayCnt());
                    result.setMonthlyAppro(approvalResult.getMonthlyAppro());
                    result.setYearRevenue(approvalResult.getYearRevenue());
                }

                for (Map.Entry<Long, St02CrmApprovalResult> entry : map.entrySet()) {
                    list.add(entry.getValue());
                }
                break;
            case "dept":
                startPage();
                List<St02CrmApprovalResult> flowDeptList = st02CrmApprovalInfoService.selectDeptFlowList(approvalReqVo);
                Map<Long, St02CrmApprovalResult> deptMap = new HashMap<>();
                for (St02CrmApprovalResult result : flowDeptList) {
                    deptMap.put(result.getDeptId(), result);
                }
                List<St02CrmApprovalResult> visitDeptList = st02CrmApprovalInfoService.selectDeptVisitList(approvalReqVo);
                for (St02CrmApprovalResult visit : visitDeptList) {
                    if (!deptMap.containsKey(visit.getDeptId())) {
                        continue;
                    }
                    St02CrmApprovalResult result = deptMap.get(visit.getDeptId());
                    result.setVisitDailyCnt(visit.getVisitDailyCnt());
                    result.setVisitMonthlyCnt(visit.getVisitMonthlyCnt());
                }

                List<St02CrmApprovalResult> resultDeptList = st02CrmApprovalInfoService.selectDeptResultList(approvalReqVo);
                for (St02CrmApprovalResult approvalResult : resultDeptList) {
                    if (!deptMap.containsKey(approvalResult.getDeptId())) {
                        continue;
                    }
                    St02CrmApprovalResult result = deptMap.get(approvalResult.getDeptId());
                    result.setUserName(approvalResult.getDeptName());
                    result.setMonthlyRevenue(approvalResult.getMonthlyRevenue());
                    result.setMonthlyCnt(approvalResult.getMonthlyCnt());
                    result.setMonthlyPayCnt(approvalResult.getMonthlyPayCnt());
                    result.setDailyRevenue(approvalResult.getDailyRevenue());
                    result.setDailyCnt(approvalResult.getDailyCnt());
                    result.setDailyPayCnt(approvalResult.getDailyPayCnt());
                    result.setMonthlyAppro(approvalResult.getMonthlyAppro());
                    result.setYearRevenue(approvalResult.getYearRevenue());
                }

                for (Map.Entry<Long, St02CrmApprovalResult> entry : deptMap.entrySet()) {
                    list.add(entry.getValue());
                }
                break;
//                目前不用一级部门数据统计
//            case "deptParent":
//                startPage();
//                list = st02CrmApprovalInfoService.selectDeptParentResultList(approvalReqVo);
////                for (St02CrmApprovalResult result : list) {
////                    result.setDeptName(deptService.selectDeptById(result.getDeptId()).getDeptName());
////                }
//                break;
            default:
                startPage();
                list = st02CrmApprovalInfoService.selectResultList(approvalReqVo);
                break;
        }
        return getDataTable(list);
    }

    /**
     * 查询审件列表
     */
    @ApiOperation("查询审件列表")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ApprovalInfoVo approvalInfoVo)
    {
        ApprovalInfoReqVo approvalInfoReqVo = new ApprovalInfoReqVo(approvalInfoVo);
        int roleId = roleService.selectUserRoleByUserId(SecurityUtils.getUserId());

        SysUser sysUser = new SysUser();
        sysUser.setDeptId(SecurityUtils.getDeptId());
        List<String> list = new ArrayList();
//        common角色无其它用户权限
        if (roleId == 2) {
            list.add(SecurityUtils.getUserId().toString());
        } else {
            List<SysUser> sysUserList = sysUserService.selectUserList(sysUser);
            for (SysUser user : sysUserList) {
                list.add(user.getUserId().toString());
            }
        }
        String ids = list.stream().map(String::valueOf).collect(Collectors.joining(","));
        approvalInfoReqVo.setUserIds(ids);
        startPage();
        List<St02CrmApprovalInfo> list1 = st02CrmApprovalInfoService.selectSt02CrmApprovalInfoList(approvalInfoReqVo);

//        common角色无其它用户权限
//        if (roleId == 2) {
//            List<St02CrmApprovalInfo> listNew = new ArrayList();
//            for (St02CrmApprovalInfo approvalInfo : list1) {
//                if (SecurityUtils.getUserId().equals(approvalInfo.getUserId())) {
//                    listNew.add(approvalInfo);
//                }
//            }
//            return getDataTable(listNew);
//        }
        return getDataTable(list1);
    }

    /**
     * 导出审件列表
     */
    @ApiOperation("导出审件列表")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:export')")
    @Log(title = "审件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApprovalInfoVo approvalInfoVo)
    {
        ApprovalInfoReqVo approvalInfoReqVo = new ApprovalInfoReqVo(approvalInfoVo);
        List<St02CrmApprovalInfo> list = st02CrmApprovalInfoService.selectSt02CrmApprovalInfoList(approvalInfoReqVo);
        ExcelUtil<St02CrmApprovalInfo> util = new ExcelUtil<St02CrmApprovalInfo>(St02CrmApprovalInfo.class);
        util.exportExcel(response, list, "审件数据");
    }

    /**
     * 获取审件详细信息
     */
    @ApiOperation("获取审件详细信息")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02CrmApprovalInfoService.selectSt02CrmApprovalInfoById(id));
    }

    /**
     * 新增审件
     */
    @ApiOperation("新增审件")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:add')")
    @Log(title = "审件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmApprovalInfo st02CrmApprovalInfo)
    {
        Long customerId = st02CrmApprovalInfo.getCustomerId();
        if (customerId == null) {
            return AjaxResult.error("客户ID不能为空");
        }

        St02CrmCustomer customer = st02CrmCustomerService.selectSt02CrmCustomerById(customerId);
        if (customer == null) {
            return AjaxResult.error("客户不存在");
        }


        LoginUser loginUser = SecurityUtils.getLoginUser();

        if (customer.getUserId() == null || !customer.getUserId().equals(loginUser.getUserId())) {
            return AjaxResult.error("客户不属于当前用户");
        }
        st02CrmApprovalInfo.setUserId(loginUser.getUserId());
        st02CrmApprovalInfo.setUserName(loginUser.getUsername());
        st02CrmApprovalInfo.setOperatorName(loginUser.getUsername());
        return toAjax(st02CrmApprovalInfoService.insertSt02CrmApprovalInfo(st02CrmApprovalInfo));
    }

    /**
     * 审核审件
     */
    @ApiOperation("审核审件")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:audit')")
    @Log(title = "审批", businessType = BusinessType.UPDATE)
    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody AuditVo auditVo)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        St02CrmApprovalInfo st02CrmApprovalInfo = new St02CrmApprovalInfo();
        st02CrmApprovalInfo.setStatus(auditVo.getStatus());
        st02CrmApprovalInfo.setId(auditVo.getId());
        st02CrmApprovalInfo.setOperatorName(loginUser.getUsername());
        st02CrmApprovalInfo.setCostMoney(auditVo.getCostMoney());
        St02CrmApprovalInfo approvalInfo = st02CrmApprovalInfoService.selectSt02CrmApprovalInfoById(auditVo.getId());
        st02CrmApprovalInfo.setProfit(approvalInfo.getCollectMoney().subtract(auditVo.getCostMoney()));
        return toAjax(st02CrmApprovalInfoService.updateSt02CrmApprovalInfo(st02CrmApprovalInfo));
    }
    /**
     * 修改审件
     */
    @ApiOperation("修改审件")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:edit')")
    @Log(title = "审件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmApprovalInfo st02CrmApprovalInfo)
    {
        Long customerId = st02CrmApprovalInfo.getCustomerId();
        if (customerId != null) {
            St02CrmCustomer customer = st02CrmCustomerService.selectSt02CrmCustomerById(customerId);
            if (customer == null) {
                return AjaxResult.error("客户不存在");
            }
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        st02CrmApprovalInfo.setOperatorName(loginUser.getUsername());
        return toAjax(st02CrmApprovalInfoService.updateSt02CrmApprovalInfo(st02CrmApprovalInfo));
    }

    /**
     * 删除审件
     */
    @ApiOperation("删除审件")
    @PreAuthorize("@ss.hasPermi('crm:approval_info:remove')")
    @Log(title = "审件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02CrmApprovalInfoService.deleteSt02CrmApprovalInfoByIds(ids));
    }
}
