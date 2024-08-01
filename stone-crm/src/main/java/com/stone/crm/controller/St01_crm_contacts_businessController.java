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
import com.stone.crm.domain.St01_crm_contacts_business;
import com.stone.crm.service.Ist01_crm_contacts_businessService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 商机联系人关联Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/contacts/business")
public class St01_crm_contacts_businessController extends BaseController
{
    @Autowired
    private Ist01_crm_contacts_businessService st01_crm_contacts_businessService;

    /**
     * 查询商机联系人关联列表
     */
    @PreAuthorize("@ss.hasPermi('crm:business:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_contacts_business st01_crm_contacts_business)
    {
        startPage();
        List<St01_crm_contacts_business> list = st01_crm_contacts_businessService.selectst01_crm_contacts_businessList(st01_crm_contacts_business);
        return getDataTable(list);
    }

    /**
     * 导出商机联系人关联列表
     */
    @PreAuthorize("@ss.hasPermi('crm:business:export')")
    @Log(title = "商机联系人关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_contacts_business st01_crm_contacts_business)
    {
        List<St01_crm_contacts_business> list = st01_crm_contacts_businessService.selectst01_crm_contacts_businessList(st01_crm_contacts_business);
        ExcelUtil<St01_crm_contacts_business> util = new ExcelUtil<St01_crm_contacts_business>(St01_crm_contacts_business.class);
        util.exportExcel(response, list, "商机联系人关联数据");
    }

    /**
     * 获取商机联系人关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:business:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(st01_crm_contacts_businessService.selectst01_crm_contacts_businessById(id));
    }

    /**
     * 新增商机联系人关联
     */
    @PreAuthorize("@ss.hasPermi('crm:business:add')")
    @Log(title = "商机联系人关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_contacts_business st01_crm_contacts_business)
    {
        return toAjax(st01_crm_contacts_businessService.insertst01_crm_contacts_business(st01_crm_contacts_business));
    }

    /**
     * 修改商机联系人关联
     */
    @PreAuthorize("@ss.hasPermi('crm:business:edit')")
    @Log(title = "商机联系人关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_contacts_business st01_crm_contacts_business)
    {
        return toAjax(st01_crm_contacts_businessService.updatest01_crm_contacts_business(st01_crm_contacts_business));
    }

    /**
     * 删除商机联系人关联
     */
    @PreAuthorize("@ss.hasPermi('crm:business:remove')")
    @Log(title = "商机联系人关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(st01_crm_contacts_businessService.deletest01_crm_contacts_businessByIds(ids));
    }
}
