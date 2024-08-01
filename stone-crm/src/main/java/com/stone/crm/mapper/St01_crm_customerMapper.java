package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_customer;

/**
 * 客户Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_customerMapper
{
    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    public St01_crm_customer selectst01_crm_customerByCustomerId(String customerId);

    /**
     * 查询客户列表
     * 
     * @param st01_crm_customer 客户
     * @return 客户集合
     */
    public List<St01_crm_customer> selectst01_crm_customerList(St01_crm_customer st01_crm_customer);

    /**
     * 新增客户
     * 
     * @param st01_crm_customer 客户
     * @return 结果
     */
    public int insertst01_crm_customer(St01_crm_customer st01_crm_customer);

    /**
     * 修改客户
     * 
     * @param st01_crm_customer 客户
     * @return 结果
     */
    public int updatest01_crm_customer(St01_crm_customer st01_crm_customer);

    /**
     * 删除客户
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    public int deletest01_crm_customerByCustomerId(String customerId);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_customerByCustomerIds(String[] customerIds);
}
