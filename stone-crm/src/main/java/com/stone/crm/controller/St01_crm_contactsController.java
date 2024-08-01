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
import com.stone.crm.domain.St01_crm_contacts;
import com.stone.crm.service.Ist01_crm_contactsService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 联系人Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/contacts")
public class St01_crm_contactsController extends BaseController
{
    @Autowired
    private Ist01_crm_contactsService st01_crm_contactsService;

    /**
     * 查询联系人列表
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_contacts st01_crm_contacts)
    {
        startPage();
        List<St01_crm_contacts> list = st01_crm_contactsService.selectst01_crm_contactsList(st01_crm_contacts);
        return getDataTable(list);
    }

    /**
     * 导出联系人列表
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:export')")
    @Log(title = "联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_contacts st01_crm_contacts)
    {
        List<St01_crm_contacts> list = st01_crm_contactsService.selectst01_crm_contactsList(st01_crm_contacts);
        ExcelUtil<St01_crm_contacts> util = new ExcelUtil<St01_crm_contacts>(St01_crm_contacts.class);
        util.exportExcel(response, list, "联系人数据");
    }

    /**
     * 获取联系人详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:query')")
    @GetMapping(value = "/{contactsId}")
    public AjaxResult getInfo(@PathVariable("contactsId") String contactsId)
    {
        return success(st01_crm_contactsService.selectst01_crm_contactsByContactsId(contactsId));
    }

    /**
     * 新增联系人
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:add')")
    @Log(title = "联系人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_contacts st01_crm_contacts)
    {
        return toAjax(st01_crm_contactsService.insertst01_crm_contacts(st01_crm_contacts));
    }

    /**
     * 修改联系人
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:edit')")
    @Log(title = "联系人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_contacts st01_crm_contacts)
    {
        return toAjax(st01_crm_contactsService.updatest01_crm_contacts(st01_crm_contacts));
    }

    /**
     * 删除联系人
     */
    @PreAuthorize("@ss.hasPermi('crm:contacts:remove')")
    @Log(title = "联系人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contactsIds}")
    public AjaxResult remove(@PathVariable String[] contactsIds)
    {
        return toAjax(st01_crm_contactsService.deletest01_crm_contactsByContactsIds(contactsIds));
    }
}
