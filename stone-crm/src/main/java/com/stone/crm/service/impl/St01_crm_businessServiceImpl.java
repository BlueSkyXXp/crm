package com.stone.crm.service.impl;

import java.util.List;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import com.stone.common.utils.spring.SpringUtils;
import com.stone.crm.domain.St01_crm_business_product;
import com.stone.crm.mapper.St01_crm_business_productMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_businessMapper;
import com.stone.crm.domain.St01_crm_business;
import com.stone.crm.service.Ist01_crm_businessService;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * 商机Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_businessServiceImpl extends BaseService implements Ist01_crm_businessService
{
    @Autowired
    private St01_crm_businessMapper st01_crm_businessMapper;

    @Autowired
    private St01_crm_business_productMapper st01_crm_business_productMapper;
    /**
     * 查询商机
     * 
     * @param businessId 商机主键
     * @return 商机
     */
    @Override
    public St01_crm_business selectst01_crm_businessByBusinessId(String businessId)
    {
        St01_crm_business result=st01_crm_businessMapper.selectst01_crm_businessByBusinessId(businessId);
        transBean(result);
        St01_crm_business_product businessProduct = new St01_crm_business_product();
        businessProduct.setBusinessId(businessId);
        List<St01_crm_business_product> businessProducts = st01_crm_business_productMapper.selectst01_crm_business_productList(businessProduct);
        result.setProductList(businessProducts);
        return result;
    }

    /**
     * 查询商机列表
     * 
     * @param st01_crm_business 商机
     * @return 商机
     */
    @Override
    public List<St01_crm_business> selectst01_crm_businessList(St01_crm_business st01_crm_business)
    {
        List<St01_crm_business> result=st01_crm_businessMapper.selectst01_crm_businessList(st01_crm_business);
        transBeanInList(result);
        return result;
    }

    /**
     * 新增商机
     * 
     * @param st01_crm_business 商机
     * @return 结果
     */
    @Override
    @Transactional
    public int insertst01_crm_business(St01_crm_business st01_crm_business)
    {
        st01_crm_business.setBusinessId(IdWorker.nextId());
        st01_crm_business.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_business.setCreatedTime(DateUtils.getTime());
        st01_crm_business.getProductList().forEach((item)->{
            item.setRId(IdWorker.nextId());
            item.setBusinessId(st01_crm_business.getBusinessId());
            st01_crm_business_productMapper.insertst01_crm_business_product(item);
        });
        return st01_crm_businessMapper.insertst01_crm_business(st01_crm_business);
    }

    /**
     * 修改商机
     * 
     * @param st01_crm_business 商机
     * @return 结果
     */
    @Override
    @Transactional
    public int updatest01_crm_business(St01_crm_business st01_crm_business)
    {
        int result=st01_crm_businessMapper.updatest01_crm_business(st01_crm_business);
        st01_crm_business_productMapper.deletest01_crm_business_productByBusinessId(st01_crm_business.getBusinessId());
        st01_crm_business.getProductList().forEach((item)->{
            item.setRId(IdWorker.nextId());
            item.setBusinessId(st01_crm_business.getBusinessId());
            st01_crm_business_productMapper.insertst01_crm_business_product(item);
        });
        return result;
    }

    /**
     * 批量删除商机
     * 
     * @param businessIds 需要删除的商机主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_businessByBusinessIds(String[] businessIds)
    {
        int result=st01_crm_businessMapper.deletest01_crm_businessByBusinessIds(businessIds);
        st01_crm_business_productMapper.deletest01_crm_business_productByBusinessIds(businessIds);
        return result;
    }

    /**
     * 删除商机信息
     * 
     * @param businessId 商机主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deletest01_crm_businessByBusinessId(String businessId)
    {
        int result=st01_crm_businessMapper.deletest01_crm_businessByBusinessId(businessId);
        st01_crm_business_productMapper.deletest01_crm_business_productByBusinessId(businessId);
        return result;
    }
}
