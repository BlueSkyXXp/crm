package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_product_category;

/**
 * 产品分类Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_product_categoryMapper
{
    /**
     * 查询产品分类
     * 
     * @param categoryId 产品分类主键
     * @return 产品分类
     */
    public St01_crm_product_category selectst01_crm_product_categoryByCategoryId(String categoryId);

    /**
     * 查询产品分类列表
     * 
     * @param st01_crm_product_category 产品分类
     * @return 产品分类集合
     */
    public List<St01_crm_product_category> selectst01_crm_product_categoryList(St01_crm_product_category st01_crm_product_category);

    /**
     * 新增产品分类
     * 
     * @param st01_crm_product_category 产品分类
     * @return 结果
     */
    public int insertst01_crm_product_category(St01_crm_product_category st01_crm_product_category);

    /**
     * 修改产品分类
     * 
     * @param st01_crm_product_category 产品分类
     * @return 结果
     */
    public int updatest01_crm_product_category(St01_crm_product_category st01_crm_product_category);

    /**
     * 删除产品分类
     * 
     * @param categoryId 产品分类主键
     * @return 结果
     */
    public int deletest01_crm_product_categoryByCategoryId(String categoryId);

    /**
     * 批量删除产品分类
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_product_categoryByCategoryIds(String[] categoryIds);
}
