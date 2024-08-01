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
import com.stone.crm.domain.St01_crm_owner_record;
import com.stone.crm.service.Ist01_crm_owner_recordService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 负责人变更记录Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/owner/record")
public class St01_crm_owner_recordController extends BaseController
{
    @Autowired
    private Ist01_crm_owner_recordService st01_crm_owner_recordService;

    /**
     * 查询负责人变更记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_owner_record st01_crm_owner_record)
    {
        startPage();
        List<St01_crm_owner_record> list = st01_crm_owner_recordService.selectst01_crm_owner_recordList(st01_crm_owner_record);
        return getDataTable(list);
    }

    /**
     * 导出负责人变更记录列表
     */
    @PreAuthorize("@ss.hasPermi('crm:record:export')")
    @Log(title = "负责人变更记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_owner_record st01_crm_owner_record)
    {
        List<St01_crm_owner_record> list = st01_crm_owner_recordService.selectst01_crm_owner_recordList(st01_crm_owner_record);
        ExcelUtil<St01_crm_owner_record> util = new ExcelUtil<St01_crm_owner_record>(St01_crm_owner_record.class);
        util.exportExcel(response, list, "负责人变更记录数据");
    }

    /**
     * 获取负责人变更记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") String recordId)
    {
        return success(st01_crm_owner_recordService.selectst01_crm_owner_recordByRecordId(recordId));
    }

    /**
     * 新增负责人变更记录
     */
    @PreAuthorize("@ss.hasPermi('crm:record:add')")
    @Log(title = "负责人变更记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_owner_record st01_crm_owner_record)
    {
        return toAjax(st01_crm_owner_recordService.insertst01_crm_owner_record(st01_crm_owner_record));
    }

    /**
     * 修改负责人变更记录
     */
    @PreAuthorize("@ss.hasPermi('crm:record:edit')")
    @Log(title = "负责人变更记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_owner_record st01_crm_owner_record)
    {
        return toAjax(st01_crm_owner_recordService.updatest01_crm_owner_record(st01_crm_owner_record));
    }

    /**
     * 删除负责人变更记录
     */
    @PreAuthorize("@ss.hasPermi('crm:record:remove')")
    @Log(title = "负责人变更记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable String[] recordIds)
    {
        return toAjax(st01_crm_owner_recordService.deletest01_crm_owner_recordByRecordIds(recordIds));
    }
}
