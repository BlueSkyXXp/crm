package com.stone.crm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
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
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.enums.BusinessType;
import com.stone.crm.domain.St01_crm_customer;
import com.stone.crm.service.Ist01_crm_customerService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
//@Api(value = "客户管理--已废弃", tags = {"客户管理--已废弃"})
@RestController
@RequestMapping("/crm/customer1")
public class St01_crm_customerController extends BaseController
{
    @Autowired
    private Ist01_crm_customerService st01_crm_customerService;

    /**
     * 查询客户列表
     */
    //@ApiOperation(value = "查询客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_customer st01_crm_customer)
    {
        startPage();
        List<St01_crm_customer> list = st01_crm_customerService.selectst01_crm_customerList(st01_crm_customer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    //@ApiOperation("导出客户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_customer st01_crm_customer)
    {
        List<St01_crm_customer> list = st01_crm_customerService.selectst01_crm_customerList(st01_crm_customer);
        ExcelUtil<St01_crm_customer> util = new ExcelUtil<St01_crm_customer>(St01_crm_customer.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    //@ApiOperation("获取客户详细信息")
    @PreAuthorize("@ss.hasPermi('crm:customer:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") String customerId)
    {
        return success(st01_crm_customerService.selectst01_crm_customerByCustomerId(customerId));
    }

    /**
     * 新增客户
     */
    //@ApiOperation("新增客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_customer st01_crm_customer)
    {
        return toAjax(st01_crm_customerService.insertst01_crm_customer(st01_crm_customer));
    }

    /**
     * 修改客户
     */
    //@ApiOperation("修改客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_customer st01_crm_customer)
    {
        return toAjax(st01_crm_customerService.updatest01_crm_customer(st01_crm_customer));
    }

    /**
     * 删除客户
     */
    //@ApiOperation("删除客户")
    @PreAuthorize("@ss.hasPermi('crm:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable String[] customerIds)
    {
        return toAjax(st01_crm_customerService.deletest01_crm_customerByCustomerIds(customerIds));
    }
}
