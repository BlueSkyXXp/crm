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
import com.stone.crm.domain.St01_crm_business_change;
import com.stone.crm.service.Ist01_crm_business_changeService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 商机阶段变化Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/change")
public class St01_crm_business_changeController extends BaseController
{
    @Autowired
    private Ist01_crm_business_changeService st01_crm_business_changeService;

    /**
     * 查询商机阶段变化列表
     */
    @PreAuthorize("@ss.hasPermi('crm:change:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_business_change st01_crm_business_change)
    {
        startPage();
        List<St01_crm_business_change> list = st01_crm_business_changeService.selectst01_crm_business_changeList(st01_crm_business_change);
        return getDataTable(list);
    }

    /**
     * 导出商机阶段变化列表
     */
    @PreAuthorize("@ss.hasPermi('crm:change:export')")
    @Log(title = "商机阶段变化", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_business_change st01_crm_business_change)
    {
        List<St01_crm_business_change> list = st01_crm_business_changeService.selectst01_crm_business_changeList(st01_crm_business_change);
        ExcelUtil<St01_crm_business_change> util = new ExcelUtil<St01_crm_business_change>(St01_crm_business_change.class);
        util.exportExcel(response, list, "商机阶段变化数据");
    }

    /**
     * 获取商机阶段变化详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:change:query')")
    @GetMapping(value = "/{changeId}")
    public AjaxResult getInfo(@PathVariable("changeId") String changeId)
    {
        return success(st01_crm_business_changeService.selectst01_crm_business_changeByChangeId(changeId));
    }

    /**
     * 新增商机阶段变化
     */
    @PreAuthorize("@ss.hasPermi('crm:change:add')")
    @Log(title = "商机阶段变化", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_business_change st01_crm_business_change)
    {
        return toAjax(st01_crm_business_changeService.insertst01_crm_business_change(st01_crm_business_change));
    }

    /**
     * 修改商机阶段变化
     */
    @PreAuthorize("@ss.hasPermi('crm:change:edit')")
    @Log(title = "商机阶段变化", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_business_change st01_crm_business_change)
    {
        return toAjax(st01_crm_business_changeService.updatest01_crm_business_change(st01_crm_business_change));
    }

    /**
     * 删除商机阶段变化
     */
    @PreAuthorize("@ss.hasPermi('crm:change:remove')")
    @Log(title = "商机阶段变化", businessType = BusinessType.DELETE)
	@DeleteMapping("/{changeIds}")
    public AjaxResult remove(@PathVariable String[] changeIds)
    {
        return toAjax(st01_crm_business_changeService.deletest01_crm_business_changeByChangeIds(changeIds));
    }
}
