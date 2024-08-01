package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_contract;

/**
 * 合同Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_contractMapper
{
    /**
     * 查询合同
     * 
     * @param contractId 合同主键
     * @return 合同
     */
    public St01_crm_contract selectst01_crm_contractByContractId(String contractId);

    /**
     * 查询合同列表
     * 
     * @param st01_crm_contract 合同
     * @return 合同集合
     */
    public List<St01_crm_contract> selectst01_crm_contractList(St01_crm_contract st01_crm_contract);

    /**
     * 新增合同
     * 
     * @param st01_crm_contract 合同
     * @return 结果
     */
    public int insertst01_crm_contract(St01_crm_contract st01_crm_contract);

    /**
     * 修改合同
     * 
     * @param st01_crm_contract 合同
     * @return 结果
     */
    public int updatest01_crm_contract(St01_crm_contract st01_crm_contract);

    /**
     * 删除合同
     * 
     * @param contractId 合同主键
     * @return 结果
     */
    public int deletest01_crm_contractByContractId(String contractId);

    /**
     * 批量删除合同
     * 
     * @param contractIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_contractByContractIds(String[] contractIds);
}
