package com.stone.crm.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.service.ISt02CrmCustomerService;
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
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.enums.BusinessType;
import com.stone.crm.domain.St02CrmCustomerFlowInfo;
import com.stone.crm.service.ISt02CrmCustomerFlowInfoService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 客户流转记录Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Api(value = "客户流转记录", tags = {"客户流转记录"})
@RestController
@RequestMapping("/crm/customer_flow")
public class St02CrmCustomerFlowInfoController extends BaseController
{
    @Autowired
    private ISt02CrmCustomerFlowInfoService st02CrmCustomerFlowInfoService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    /**
     * 查询客户流转记录列表
     */
    @ApiOperation("查询客户流转记录列表")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        startPage();
        List<St02CrmCustomerFlowInfo> list = st02CrmCustomerFlowInfoService.selectSt02CrmCustomerFlowInfoList(st02CrmCustomerFlowInfo);
        return getDataTable(list);
    }

    /**
     * 导出客户流转记录列表
     */
    @ApiOperation("导出客户流转记录列表")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:export')")
    @Log(title = "客户流转记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        List<St02CrmCustomerFlowInfo> list = st02CrmCustomerFlowInfoService.selectSt02CrmCustomerFlowInfoList(st02CrmCustomerFlowInfo);
        ExcelUtil<St02CrmCustomerFlowInfo> util = new ExcelUtil<St02CrmCustomerFlowInfo>(St02CrmCustomerFlowInfo.class);
        util.exportExcel(response, list, "客户流转记录数据");
    }

    /**
     * 获取客户流转记录详细信息
     */
    @ApiOperation("获取客户流转记录详细信息")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02CrmCustomerFlowInfoService.selectSt02CrmCustomerFlowInfoById(id));
    }

    /**
     * 新增客户流转记录
     */
    @ApiOperation("新增客户流转记录")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:add')")
    @Log(title = "客户流转记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        St02CrmCustomer customer = st02CrmCustomerService.selectSt02CrmCustomerById(Long.valueOf(st02CrmCustomerFlowInfo.getCustomerId()));
        St02CrmCustomer customerNew = new St02CrmCustomer();
        customerNew.setId(customer.getId());
        customerNew.setLastRemarkTime(new Date());
        st02CrmCustomerService.updateSt02CrmCustomer(customerNew);
        return toAjax(st02CrmCustomerFlowInfoService.insertSt02CrmCustomerFlowInfo(st02CrmCustomerFlowInfo));
    }

    /**
     * 修改客户流转记录
     */
    @ApiOperation("修改客户流转记录")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:edit')")
    @Log(title = "客户流转记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        return toAjax(st02CrmCustomerFlowInfoService.updateSt02CrmCustomerFlowInfo(st02CrmCustomerFlowInfo));
    }

    /**
     * 删除客户流转记录
     */
    @ApiOperation("删除客户流转记录")
    @PreAuthorize("@ss.hasPermi('crm:customer_flow:remove')")
    @Log(title = "客户流转记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02CrmCustomerFlowInfoService.deleteSt02CrmCustomerFlowInfoByIds(ids));
    }
}
