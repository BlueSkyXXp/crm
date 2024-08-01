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
import com.stone.crm.domain.St01_crm_receivables;
import com.stone.crm.service.Ist01_crm_receivablesService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 回款Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/receivables")
public class St01_crm_receivablesController extends BaseController
{
    @Autowired
    private Ist01_crm_receivablesService st01_crm_receivablesService;

    /**
     * 查询回款列表
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_receivables st01_crm_receivables)
    {
        startPage();
        List<St01_crm_receivables> list = st01_crm_receivablesService.selectst01_crm_receivablesList(st01_crm_receivables);
        return getDataTable(list);
    }

    /**
     * 导出回款列表
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:export')")
    @Log(title = "回款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_receivables st01_crm_receivables)
    {
        List<St01_crm_receivables> list = st01_crm_receivablesService.selectst01_crm_receivablesList(st01_crm_receivables);
        ExcelUtil<St01_crm_receivables> util = new ExcelUtil<St01_crm_receivables>(St01_crm_receivables.class);
        util.exportExcel(response, list, "回款数据");
    }

    /**
     * 获取回款详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:query')")
    @GetMapping(value = "/{receivablesId}")
    public AjaxResult getInfo(@PathVariable("receivablesId") String receivablesId)
    {
        return success(st01_crm_receivablesService.selectst01_crm_receivablesByReceivablesId(receivablesId));
    }

    /**
     * 新增回款
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:add')")
    @Log(title = "回款", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_receivables st01_crm_receivables)
    {
        return toAjax(st01_crm_receivablesService.insertst01_crm_receivables(st01_crm_receivables));
    }

    /**
     * 修改回款
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:edit')")
    @Log(title = "回款", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_receivables st01_crm_receivables)
    {
        return toAjax(st01_crm_receivablesService.updatest01_crm_receivables(st01_crm_receivables));
    }

    /**
     * 删除回款
     */
    @PreAuthorize("@ss.hasPermi('crm:receivables:remove')")
    @Log(title = "回款", businessType = BusinessType.DELETE)
	@DeleteMapping("/{receivablesIds}")
    public AjaxResult remove(@PathVariable String[] receivablesIds)
    {
        return toAjax(st01_crm_receivablesService.deletest01_crm_receivablesByReceivablesIds(receivablesIds));
    }
}
