package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_contract_product;

/**
 * 合同产品关系Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_contract_productMapper
{
    /**
     * 查询合同产品关系
     * 
     * @param rId 合同产品关系主键
     * @return 合同产品关系
     */
    public St01_crm_contract_product selectst01_crm_contract_productByRId(String rId);

    /**
     * 查询合同产品关系列表
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 合同产品关系集合
     */
    public List<St01_crm_contract_product> selectst01_crm_contract_productList(St01_crm_contract_product st01_crm_contract_product);

    /**
     * 新增合同产品关系
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 结果
     */
    public int insertst01_crm_contract_product(St01_crm_contract_product st01_crm_contract_product);

    /**
     * 修改合同产品关系
     * 
     * @param st01_crm_contract_product 合同产品关系
     * @return 结果
     */
    public int updatest01_crm_contract_product(St01_crm_contract_product st01_crm_contract_product);

    /**
     * 删除合同产品关系
     * 
     * @param rId 合同产品关系主键
     * @return 结果
     */
    public int deletest01_crm_contract_productByRId(String rId);

    /**
     * 批量删除合同产品关系
     * 
     * @param rIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_contract_productByRIds(String[] rIds);

    public int deletest01_crm_contract_productByContractId(String contractId);
}
