package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_business_productMapper;
import com.stone.crm.domain.St01_crm_business_product;
import com.stone.crm.service.Ist01_crm_business_productService;

/**
 * 商机产品关系Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_business_productServiceImpl implements Ist01_crm_business_productService
{
    @Autowired
    private St01_crm_business_productMapper st01_crm_business_productMapper;

    /**
     * 查询商机产品关系
     * 
     * @param rId 商机产品关系主键
     * @return 商机产品关系
     */
    @Override
    public St01_crm_business_product selectst01_crm_business_productByRId(String rId)
    {
        return st01_crm_business_productMapper.selectst01_crm_business_productByRId(rId);
    }

    /**
     * 查询商机产品关系列表
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 商机产品关系
     */
    @Override
    public List<St01_crm_business_product> selectst01_crm_business_productList(St01_crm_business_product st01_crm_business_product)
    {
        return st01_crm_business_productMapper.selectst01_crm_business_productList(st01_crm_business_product);
    }

    /**
     * 新增商机产品关系
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 结果
     */
    @Override
    public int insertst01_crm_business_product(St01_crm_business_product st01_crm_business_product)
    {
        return st01_crm_business_productMapper.insertst01_crm_business_product(st01_crm_business_product);
    }

    /**
     * 修改商机产品关系
     * 
     * @param st01_crm_business_product 商机产品关系
     * @return 结果
     */
    @Override
    public int updatest01_crm_business_product(St01_crm_business_product st01_crm_business_product)
    {
        return st01_crm_business_productMapper.updatest01_crm_business_product(st01_crm_business_product);
    }

    /**
     * 批量删除商机产品关系
     * 
     * @param rIds 需要删除的商机产品关系主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_business_productByRIds(String[] rIds)
    {
        return st01_crm_business_productMapper.deletest01_crm_business_productByRIds(rIds);
    }

    /**
     * 删除商机产品关系信息
     * 
     * @param rId 商机产品关系主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_business_productByRId(String rId)
    {
        return st01_crm_business_productMapper.deletest01_crm_business_productByRId(rId);
    }
}
