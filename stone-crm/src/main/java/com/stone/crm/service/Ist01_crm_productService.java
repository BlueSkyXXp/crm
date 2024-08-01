package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_product;

/**
 * 产品Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_productService 
{
    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    public St01_crm_product selectst01_crm_productByProductId(String productId);

    /**
     * 查询产品列表
     * 
     * @param st01_crm_product 产品
     * @return 产品集合
     */
    public List<St01_crm_product> selectst01_crm_productList(St01_crm_product st01_crm_product);

    /**
     * 新增产品
     * 
     * @param st01_crm_product 产品
     * @return 结果
     */
    public int insertst01_crm_product(St01_crm_product st01_crm_product);

    /**
     * 修改产品
     * 
     * @param st01_crm_product 产品
     * @return 结果
     */
    public int updatest01_crm_product(St01_crm_product st01_crm_product);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键集合
     * @return 结果
     */
    public int deletest01_crm_productByProductIds(String[] productIds);

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    public int deletest01_crm_productByProductId(String productId);
}
