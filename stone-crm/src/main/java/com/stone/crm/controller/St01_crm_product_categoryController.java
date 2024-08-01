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
import com.stone.crm.domain.St01_crm_product_category;
import com.stone.crm.service.Ist01_crm_product_categoryService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 产品分类Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/category")
public class St01_crm_product_categoryController extends BaseController
{
    @Autowired
    private Ist01_crm_product_categoryService st01_crm_product_categoryService;

    /**
     * 查询产品分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_product_category st01_crm_product_category)
    {
        startPage();
        List<St01_crm_product_category> list = st01_crm_product_categoryService.selectst01_crm_product_categoryList(st01_crm_product_category);
        return getDataTable(list);
    }

    @GetMapping("allList")
    public AjaxResult allList(St01_crm_product_category st01_crm_product_category){
        List<St01_crm_product_category> list = st01_crm_product_categoryService.selectst01_crm_product_categoryList(st01_crm_product_category);
        return AjaxResult.success(list);
    }

    /**
     * 导出产品分类列表
     */
    @PreAuthorize("@ss.hasPermi('crm:category:export')")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_product_category st01_crm_product_category)
    {
        List<St01_crm_product_category> list = st01_crm_product_categoryService.selectst01_crm_product_categoryList(st01_crm_product_category);
        ExcelUtil<St01_crm_product_category> util = new ExcelUtil<St01_crm_product_category>(St01_crm_product_category.class);
        util.exportExcel(response, list, "产品分类数据");
    }

    /**
     * 获取产品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") String categoryId)
    {
        return success(st01_crm_product_categoryService.selectst01_crm_product_categoryByCategoryId(categoryId));
    }

    /**
     * 新增产品分类
     */
    @PreAuthorize("@ss.hasPermi('crm:category:add')")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_product_category st01_crm_product_category)
    {
        return toAjax(st01_crm_product_categoryService.insertst01_crm_product_category(st01_crm_product_category));
    }

    /**
     * 修改产品分类
     */
    @PreAuthorize("@ss.hasPermi('crm:category:edit')")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_product_category st01_crm_product_category)
    {
        return toAjax(st01_crm_product_categoryService.updatest01_crm_product_category(st01_crm_product_category));
    }

    /**
     * 删除产品分类
     */
    @PreAuthorize("@ss.hasPermi('crm:category:remove')")
    @Log(title = "产品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable String[] categoryIds)
    {
        return toAjax(st01_crm_product_categoryService.deletest01_crm_product_categoryByCategoryIds(categoryIds));
    }
}
