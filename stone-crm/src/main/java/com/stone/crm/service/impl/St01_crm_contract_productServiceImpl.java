package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_contract_productMapper;
import com.stone.crm.domain.St01_crm_contract_product;
import com.stone.crm.service.Ist01_crm_contract_productService;

/**
 * 合同产品关系Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_contract_productServiceImpl implements Ist01_crm_contract_productService
{
    @Autowired
    private St01_crm_contract_productMapper st01_crm_contract_productMapper;

    /**
     * 查询合同产品关系
     * 
     * @param rId 合同产品关系主键
     * @return 合同产品关系
     */
    @Override
    public St01_crm_contract_product selectst01_crm_contract_productByRId(String rId)
    {
        return st01_crm_contract_productMapper.selectst01_crm_contract_productByRId(rId);
    }

    /**
     * 查询合同产品关系列表
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 合同产品关系
     */
    @Override
    public List<St01_crm_contract_product> selectst01_crm_contract_productList(St01_crm_contract_product st01_crm_contract_product)
    {
        return st01_crm_contract_productMapper.selectst01_crm_contract_productList(st01_crm_contract_product);
    }

    /**
     * 新增合同产品关系
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 结果
     */
    @Override
    public int insertst01_crm_contract_product(St01_crm_contract_product st01_crm_contract_product)
    {
        st01_crm_contract_product.setRId(IdWorker.nextId());
        st01_crm_contract_product.setCreateBy(SecurityUtils.getUsername());
        return st01_crm_contract_productMapper.insertst01_crm_contract_product(st01_crm_contract_product);
    }

    /**
     * 修改合同产品关系
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 结果
     */
    @Override
    public int updatest01_crm_contract_product(St01_crm_contract_product st01_crm_contract_product)
    {
        return st01_crm_contract_productMapper.updatest01_crm_contract_product(st01_crm_contract_product);
    }

    /**
     * 批量删除合同产品关系
     * 
     * @param rIds 需要删除的合同产品关系主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contract_productByRIds(String[] rIds)
    {
        return st01_crm_contract_productMapper.deletest01_crm_contract_productByRIds(rIds);
    }

    @Override
    public int deletest01_crm_contract_productByContractId(String contractId){
        return st01_crm_contract_productMapper.deletest01_crm_contract_productByContractId(contractId);
    }

    /**
     * 删除合同产品关系信息
     * 
     * @param rId 合同产品关系主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contract_productByRId(String rId)
    {
        return st01_crm_contract_productMapper.deletest01_crm_contract_productByRId(rId);
    }
}
