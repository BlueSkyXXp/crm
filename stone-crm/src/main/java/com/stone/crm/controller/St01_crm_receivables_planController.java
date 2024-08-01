package com.stone.crm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.stone.crm.domain.St01_crm_receivables_plan;
import com.stone.crm.service.Ist01_crm_receivables_planService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 回款计划Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/plan")
public class St01_crm_receivables_planController extends BaseController
{
    @Autowired
    private Ist01_crm_receivables_planService st01_crm_receivables_planService;

    /**
     * 查询回款计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_receivables_plan st01_crm_receivables_plan)
    {
        startPage();
        List<St01_crm_receivables_plan> list = st01_crm_receivables_planService.selectst01_crm_receivables_planList(st01_crm_receivables_plan);
        return getDataTable(list);
    }

    /**
     * 导出回款计划列表
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:export')")
    @Log(title = "回款计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_receivables_plan st01_crm_receivables_plan)
    {
        List<St01_crm_receivables_plan> list = st01_crm_receivables_planService.selectst01_crm_receivables_planList(st01_crm_receivables_plan);
        ExcelUtil<St01_crm_receivables_plan> util = new ExcelUtil<St01_crm_receivables_plan>(St01_crm_receivables_plan.class);
        util.exportExcel(response, list, "回款计划数据");
    }

    /**
     * 获取回款计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") String planId)
    {
        return success(st01_crm_receivables_planService.selectst01_crm_receivables_planByPlanId(planId));
    }

    /**
     * 新增回款计划
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:add')")
    @Log(title = "回款计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<St01_crm_receivables_plan> st01_crm_receivables_plans)
    {
        return toAjax(st01_crm_receivables_planService.insertst01_crm_receivables_plan(st01_crm_receivables_plans));
    }

    /**
     * 修改回款计划
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:edit')")
    @Log(title = "回款计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_receivables_plan st01_crm_receivables_plan)
    {
        return toAjax(st01_crm_receivables_planService.updatest01_crm_receivables_plan(st01_crm_receivables_plan));
    }

    /**
     * 删除回款计划
     */
    @PreAuthorize("@ss.hasPermi('crm:plan:remove')")
    @Log(title = "回款计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable String[] planIds)
    {
        return toAjax(st01_crm_receivables_planService.deletest01_crm_receivables_planByPlanIds(planIds));
    }
}
