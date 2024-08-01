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
import com.stone.crm.domain.St01_crm_product;
import com.stone.crm.service.Ist01_crm_productService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 产品Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/product")
public class St01_crm_productController extends BaseController
{
    @Autowired
    private Ist01_crm_productService st01_crm_productService;

    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_product st01_crm_product)
    {
        startPage();
        List<St01_crm_product> list = st01_crm_productService.selectst01_crm_productList(st01_crm_product);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_product st01_crm_product)
    {
        List<St01_crm_product> list = st01_crm_productService.selectst01_crm_productList(st01_crm_product);
        ExcelUtil<St01_crm_product> util = new ExcelUtil<St01_crm_product>(St01_crm_product.class);
        util.exportExcel(response, list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") String productId)
    {
        return success(st01_crm_productService.selectst01_crm_productByProductId(productId));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('crm:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_product st01_crm_product)
    {
        return toAjax(st01_crm_productService.insertst01_crm_product(st01_crm_product));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('crm:product:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_product st01_crm_product)
    {
        return toAjax(st01_crm_productService.updatest01_crm_product(st01_crm_product));
    }

    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('crm:product:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable String[] productIds)
    {
        return toAjax(st01_crm_productService.deletest01_crm_productByProductIds(productIds));
    }
}
