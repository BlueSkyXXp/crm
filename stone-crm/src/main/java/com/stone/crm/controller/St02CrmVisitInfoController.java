package com.stone.crm.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.stone.common.core.domain.entity.SysUser;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmVisitDetail;
import com.stone.crm.domain.St02CrmVisitResultInfo;
import com.stone.crm.domain.vo.VisitDetailReqVo;
import com.stone.crm.domain.vo.VisitReqVo;
import com.stone.crm.domain.vo.VisitVo;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.framework.web.domain.server.Sys;
import com.stone.system.service.ISysDeptService;
import com.stone.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.stone.common.annotation.Log;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.enums.BusinessType;
import com.stone.crm.domain.St02CrmVisitInfo;
import com.stone.crm.service.ISt02CrmVisitInfoService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 客户来访Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Api(value = "客户来访", tags = {"客户来访"})
@RestController
@RequestMapping("/crm/visit")
public class St02CrmVisitInfoController extends BaseController
{
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISt02CrmVisitInfoService st02CrmVisitInfoService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISysDeptService sysDeptService;


    /**
     * 查询客户来访列表
     */
    @ApiOperation("查询客户来访列表")
    @PreAuthorize("@ss.hasPermi('crm:visit:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody VisitDetailReqVo visitDetailReqVo)
    {
        startPage();
        List<St02CrmVisitDetail> list = st02CrmVisitInfoService.selectSt02CrmVisitDetailList(new VisitReqVo(visitDetailReqVo));

        for (St02CrmVisitDetail st02CrmVisitDetail : list) {
            if (st02CrmVisitDetail.getPhone() != null) {
                st02CrmVisitDetail.setPhone(st02CrmVisitDetail.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出客户来访列表
     */
//    @ApiOperation("导出客户来访列表")
    @PreAuthorize("@ss.hasPermi('crm:visit:export')")
    @Log(title = "客户来访", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmVisitInfo st02CrmVisitInfo)
    {
        List<St02CrmVisitInfo> list = st02CrmVisitInfoService.selectSt02CrmVisitInfoList(st02CrmVisitInfo);
        ExcelUtil<St02CrmVisitInfo> util = new ExcelUtil<St02CrmVisitInfo>(St02CrmVisitInfo.class);
        util.exportExcel(response, list, "客户来访数据");
    }

    /**
     * 获取客户来访详细信息
     */
    @ApiOperation("获取客户来访详细信息")
    @PreAuthorize("@ss.hasPermi('crm:visit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02CrmVisitInfoService.selectSt02CrmVisitInfoById(id));
    }

    /**
     * 新增客户来访
     */
    @ApiOperation("新增客户来访")
    @PreAuthorize("@ss.hasPermi('crm:visit:add')")
    @Log(title = "客户来访", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmVisitInfo st02CrmVisitInfo)
    {
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

    /**
     * 来访登记统计表
     */
    @ApiOperation("来访登记统计表")
    @PreAuthorize("@ss.hasPermi('crm:visit:list')")
    @PostMapping("/result")
    public TableDataInfo result(@RequestBody VisitDetailReqVo visitDetailReqVo)
    {
        startPage();
        VisitReqVo visitReqVo = new VisitReqVo(visitDetailReqVo);
        List<St02CrmVisitResultInfo> list = st02CrmVisitInfoService.selectCrmVisitResult(visitReqVo);

        for (St02CrmVisitResultInfo st02CrmVisitResultInfo : list) {
            String newDeptName = "";
            // 修改部门名称
            if (st02CrmVisitResultInfo.getDeptId() != null) {
                String ancestors = sysDeptService.selectDeptById(st02CrmVisitResultInfo.getDeptId()).getAncestors();

                String[] deptIds = ancestors.split(",");

                for (int i = 1; i < deptIds.length; i++) {
                    String deptId = deptIds[i];
                    String deptName = sysDeptService.selectDeptById(Long.valueOf(deptId)).getDeptName();
                    if (deptName.length() > 0) {
                        newDeptName += deptName + "-";
                    }
                }
                newDeptName += st02CrmVisitResultInfo.getDeptName();
                st02CrmVisitResultInfo.setDeptName(newDeptName);
            }
        }
        return getDataTable(list);
    }


    /**
     * 修改客户来访
     */
    @ApiOperation("修改客户来访")
    @PreAuthorize("@ss.hasPermi('crm:visit:edit')")
    @Log(title = "客户来访", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmVisitInfo st02CrmVisitInfo)
    {
        return toAjax(st02CrmVisitInfoService.updateSt02CrmVisitInfo(st02CrmVisitInfo));
    }

    /**
     * 删除客户来访
     */
    @ApiOperation("删除客户来访")
    @PreAuthorize("@ss.hasPermi('crm:visit:remove')")
    @Log(title = "客户来访", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02CrmVisitInfoService.deleteSt02CrmVisitInfoByIds(ids));
    }
}
