package com.stone.crm.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.pooled.DbConfig;
import com.stone.common.core.service.BaseService;
import com.stone.common.utils.*;
import com.stone.common.utils.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_customerMapper;
import com.stone.crm.domain.St01_crm_customer;
import com.stone.crm.service.Ist01_crm_customerService;

import javax.sql.DataSource;

/**
 * 客户Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_customerServiceImpl extends BaseService implements Ist01_crm_customerService
{
    @Autowired
    private St01_crm_customerMapper st01_crm_customerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户主键
     * @return 客户
     */
    @Override
    public St01_crm_customer selectst01_crm_customerByCustomerId(String customerId)
    {
        St01_crm_customer result=st01_crm_customerMapper.selectst01_crm_customerByCustomerId(customerId);
        transBean(result);
        return result;
    }

    /**
     * 查询客户列表
     * 
     * @param st01_crm_customer 客户
     * @return 客户
     */
    @Override
    public List<St01_crm_customer> selectst01_crm_customerList(St01_crm_customer st01_crm_customer)
    {
        List<St01_crm_customer> list=st01_crm_customerMapper.selectst01_crm_customerList(st01_crm_customer);
        transBeanInList(list);
        return list;
    }

    /**
     * 新增客户
     * 
     * @param st01_crm_customer 客户
     * @return 结果
     */
    @Override
    public int insertst01_crm_customer(St01_crm_customer st01_crm_customer)
    {
        st01_crm_customer.setCustomerId(IdWorker.nextId());
        st01_crm_customer.setDeltag("0");
        st01_crm_customer.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_customer.setCreatedTime(DateUtils.getTime());
        return st01_crm_customerMapper.insertst01_crm_customer(st01_crm_customer);
    }

    /**
     * 修改客户
     * 
     * @param st01_crm_customer 客户
     * @return 结果
     */
    @Override
    public int updatest01_crm_customer(St01_crm_customer st01_crm_customer)
    {
        return st01_crm_customerMapper.updatest01_crm_customer(st01_crm_customer);
    }

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_customerByCustomerIds(String[] customerIds)
    {
        return st01_crm_customerMapper.deletest01_crm_customerByCustomerIds(customerIds);
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_customerByCustomerId(String customerId)
    {
        return st01_crm_customerMapper.deletest01_crm_customerByCustomerId(customerId);
    }
}
