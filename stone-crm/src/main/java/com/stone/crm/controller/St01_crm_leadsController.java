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
import com.stone.crm.domain.St01_crm_leads;
import com.stone.crm.service.Ist01_crm_leadsService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 线索Controller
 * 
 * @author stone
 * @date 2024-04-25
 */

//@Api(value = "线索管理---已废弃", tags = {"线索管理---已废弃"})
@RestController
@RequestMapping("/crm/leads")
public class St01_crm_leadsController extends BaseController
{
    @Autowired
    private Ist01_crm_leadsService st01_crm_leadsService;

    /**
     * 查询线索列表
     */

    //@ApiOperation("查询线索列表")
    @PreAuthorize("@ss.hasPermi('crm:leads:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_leads st01_crm_leads)
    {
        startPage();
        List<St01_crm_leads> list = st01_crm_leadsService.selectst01_crm_leadsList(st01_crm_leads);
        return getDataTable(list);
    }

    /**
     * 导出线索列表
     */
    @PreAuthorize("@ss.hasPermi('crm:leads:export')")
    @Log(title = "线索", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_leads st01_crm_leads)
    {
        List<St01_crm_leads> list = st01_crm_leadsService.selectst01_crm_leadsList(st01_crm_leads);
        ExcelUtil<St01_crm_leads> util = new ExcelUtil<St01_crm_leads>(St01_crm_leads.class);
        util.exportExcel(response, list, "线索数据");
    }

    /**
     * 获取线索详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:leads:query')")
    @GetMapping(value = "/{leadsId}")
    public AjaxResult getInfo(@PathVariable("leadsId") String leadsId)
    {
        return success(st01_crm_leadsService.selectst01_crm_leadsByLeadsId(leadsId));
    }

    /**
     * 新增线索
     */
    @PreAuthorize("@ss.hasPermi('crm:leads:add')")
    @Log(title = "线索", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_leads st01_crm_leads)
    {
        return toAjax(st01_crm_leadsService.insertst01_crm_leads(st01_crm_leads));
    }

    /**
     * 修改线索
     */
    @PreAuthorize("@ss.hasPermi('crm:leads:edit')")
    @Log(title = "线索", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_leads st01_crm_leads)
    {
        return toAjax(st01_crm_leadsService.updatest01_crm_leads(st01_crm_leads));
    }

    /**
     * 删除线索
     */
    @PreAuthorize("@ss.hasPermi('crm:leads:remove')")
    @Log(title = "线索", businessType = BusinessType.DELETE)
	@DeleteMapping("/{leadsIds}")
    public AjaxResult remove(@PathVariable String[] leadsIds)
    {
        return toAjax(st01_crm_leadsService.deletest01_crm_leadsByLeadsIds(leadsIds));
    }
}
