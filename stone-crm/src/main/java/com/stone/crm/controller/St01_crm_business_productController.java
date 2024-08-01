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
import com.stone.crm.domain.St01_crm_business_product;
import com.stone.crm.service.Ist01_crm_business_productService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 商机产品关系Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/business/product")
public class St01_crm_business_productController extends BaseController
{
    @Autowired
    private Ist01_crm_business_productService st01_crm_business_productService;

    /**
     * 查询商机产品关系列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_business_product st01_crm_business_product)
    {
        startPage();
        List<St01_crm_business_product> list = st01_crm_business_productService.selectst01_crm_business_productList(st01_crm_business_product);
        return getDataTable(list);
    }

    /**
     * 导出商机产品关系列表
     */
    @PreAuthorize("@ss.hasPermi('crm:product:export')")
    @Log(title = "商机产品关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_business_product st01_crm_business_product)
    {
        List<St01_crm_business_product> list = st01_crm_business_productService.selectst01_crm_business_productList(st01_crm_business_product);
        ExcelUtil<St01_crm_business_product> util = new ExcelUtil<St01_crm_business_product>(St01_crm_business_product.class);
        util.exportExcel(response, list, "商机产品关系数据");
    }

    /**
     * 获取商机产品关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:product:query')")
    @GetMapping(value = "/{rId}")
    public AjaxResult getInfo(@PathVariable("rId") String rId)
    {
        return success(st01_crm_business_productService.selectst01_crm_business_productByRId(rId));
    }

    /**
     * 新增商机产品关系
     */
    @PreAuthorize("@ss.hasPermi('crm:product:add')")
    @Log(title = "商机产品关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_business_product st01_crm_business_product)
    {
        return toAjax(st01_crm_business_productService.insertst01_crm_business_product(st01_crm_business_product));
    }

    /**
     * 修改商机产品关系
     */
    @PreAuthorize("@ss.hasPermi('crm:product:edit')")
    @Log(title = "商机产品关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_business_product st01_crm_business_product)
    {
        return toAjax(st01_crm_business_productService.updatest01_crm_business_product(st01_crm_business_product));
    }

    /**
     * 删除商机产品关系
     */
    @PreAuthorize("@ss.hasPermi('crm:product:remove')")
    @Log(title = "商机产品关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{rIds}")
    public AjaxResult remove(@PathVariable String[] rIds)
    {
        return toAjax(st01_crm_business_productService.deletest01_crm_business_productByRIds(rIds));
    }
}
