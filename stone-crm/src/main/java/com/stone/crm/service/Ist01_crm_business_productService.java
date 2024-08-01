package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_business_product;

/**
 * 商机产品关系Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_business_productService 
{
    /**
     * 查询商机产品关系
     * 
     * @param rId 商机产品关系主键
     * @return 商机产品关系
     */
    public St01_crm_business_product selectst01_crm_business_productByRId(String rId);

    /**
     * 查询商机产品关系列表
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 商机产品关系集合
     */
    public List<St01_crm_business_product> selectst01_crm_business_productList(St01_crm_business_product st01_crm_business_product);

    /**
     * 新增商机产品关系
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 结果
     */
    public int insertst01_crm_business_product(St01_crm_business_product st01_crm_business_product);

    /**
     * 修改商机产品关系
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 结果
     */
    public int updatest01_crm_business_product(St01_crm_business_product st01_crm_business_product);

    /**
     * 批量删除商机产品关系
     * 
     * @param rIds 需要删除的商机产品关系主键集合
     * @return 结果
     */
    public int deletest01_crm_business_productByRIds(String[] rIds);

    /**
     * 删除商机产品关系信息
     * 
     * @param rId 商机产品关系主键
     * @return 结果
     */
    public int deletest01_crm_business_productByRId(String rId);
}
