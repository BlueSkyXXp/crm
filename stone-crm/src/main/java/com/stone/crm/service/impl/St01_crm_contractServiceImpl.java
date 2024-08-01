package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import com.stone.crm.domain.St01_crm_contract_product;
import com.stone.crm.mapper.St01_crm_contract_productMapper;
import com.stone.crm.service.Ist01_crm_contract_productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_contractMapper;
import com.stone.crm.domain.St01_crm_contract;
import com.stone.crm.service.Ist01_crm_contractService;

/**
 * 合同Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_contractServiceImpl extends BaseService implements Ist01_crm_contractService
{
    @Autowired
    private St01_crm_contractMapper st01_crm_contractMapper;

    @Autowired
    private Ist01_crm_contract_productService st01_crm_contract_productService;
    /**
     * 查询合同
     * 
     * @param contractId 合同主键
     * @return 合同
     */
    @Override
    public St01_crm_contract selectst01_crm_contractByContractId(String contractId)
    {
        St01_crm_contract st01_crm_contract=st01_crm_contractMapper.selectst01_crm_contractByContractId(contractId);
        transBean(st01_crm_contract);
        St01_crm_contract_product st01_crm_contract_product=new St01_crm_contract_product();
        st01_crm_contract_product.setContractId(contractId);
        List<St01_crm_contract_product> contract_productList=st01_crm_contract_productService.selectst01_crm_contract_productList(st01_crm_contract_product);
        transBeanInList(contract_productList);
        st01_crm_contract.setProductList(contract_productList);
        return st01_crm_contract;
    }

    /**
     * 查询合同列表
     * 
     * @param st01_crm_contract 合同
     * @return 合同
     */
    @Override
    public List<St01_crm_contract> selectst01_crm_contractList(St01_crm_contract st01_crm_contract)
    {
        List<St01_crm_contract> resultList=st01_crm_contractMapper.selectst01_crm_contractList(st01_crm_contract);
        transBeanInList(resultList);
        return resultList;
    }

    /**
     * 新增合同
     * 
     * @param st01_crm_contract 合同
     * @return 结果
     */
    @Override
    public int insertst01_crm_contract(St01_crm_contract st01_crm_contract)
    {
        st01_crm_contract.setContractId(IdWorker.nextId());
        st01_crm_contract.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_contract.setCreatedTime(DateUtils.getTime());
        st01_crm_contract.getProductList().forEach(item->{
            item.setContractId(st01_crm_contract.getContactsId());
            st01_crm_contract_productService.insertst01_crm_contract_product(item);
        });
        return st01_crm_contractMapper.insertst01_crm_contract(st01_crm_contract);
    }

    /**
     * 修改合同
     * 
     * @param st01_crm_contract 合同
     * @return 结果
     */
    @Override
    public int updatest01_crm_contract(St01_crm_contract st01_crm_contract)
    {
        st01_crm_contract.setUpdatedBy(SecurityUtils.getUsername());
        st01_crm_contract.setUpdatedTime(DateUtils.getTime());
        st01_crm_contract_productService.deletest01_crm_contract_productByContractId(st01_crm_contract.getContractId());
        st01_crm_contract.getProductList().forEach((item)->{
            item.setContractId(st01_crm_contract.getContractId());
            st01_crm_contract_productService.insertst01_crm_contract_product(item);
        });
        return st01_crm_contractMapper.updatest01_crm_contract(st01_crm_contract);
    }

    /**
     * 批量删除合同
     * 
     * @param contractIds 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contractByContractIds(String[] contractIds)
    {
        return st01_crm_contractMapper.deletest01_crm_contractByContractIds(contractIds);
    }

    /**
     * 删除合同信息
     * 
     * @param contractId 合同主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contractByContractId(String contractId)
    {
        return st01_crm_contractMapper.deletest01_crm_contractByContractId(contractId);
    }
}
