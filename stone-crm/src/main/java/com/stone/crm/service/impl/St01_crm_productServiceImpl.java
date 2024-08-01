package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.constant.CacheConstants;
import com.stone.common.core.domain.entity.SysDictData;
import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.DictUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_productMapper;
import com.stone.crm.domain.St01_crm_product;
import com.stone.crm.service.Ist01_crm_productService;

import javax.annotation.PostConstruct;

/**
 * 产品Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_productServiceImpl extends BaseService implements Ist01_crm_productService
{
    @Autowired
    private St01_crm_productMapper st01_crm_productMapper;

    /**
     * 项目启动时，产品列表到缓存
     */
    @PostConstruct
    public void init(){
        loadingDictCache();
    }

    private void loadingDictCache(){
        List<SysDictData>  sysDictDataList=st01_crm_productMapper.selectst01_crm_productListForCache(new St01_crm_product());
        DictUtils.setDictCache("product_list",sysDictDataList);
    }

    /**
     * 查询产品
     * 
     * @param productId 产品主键
     * @return 产品
     */
    @Override
    public St01_crm_product selectst01_crm_productByProductId(String productId)
    {
        return st01_crm_productMapper.selectst01_crm_productByProductId(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param st01_crm_product 产品
     * @return 产品
     */
    @Override
    public List<St01_crm_product> selectst01_crm_productList(St01_crm_product st01_crm_product)
    {
        List<St01_crm_product> result=st01_crm_productMapper.selectst01_crm_productList(st01_crm_product);
        transBeanInList(result);
        return result;
    }

    /**
     * 新增产品
     * 
     * @param st01_crm_product 产品
     * @return 结果
     */
    @Override
    public int insertst01_crm_product(St01_crm_product st01_crm_product)
    {
        st01_crm_product.setProductId(IdWorker.nextId());
        st01_crm_product.setCreatedBy(SecurityUtils.getUsername());
        //st01_crm_product.setCreateTime(DateUtils.getNowDate());
        return st01_crm_productMapper.insertst01_crm_product(st01_crm_product);
    }

    /**
     * 修改产品
     * 
     * @param st01_crm_product 产品
     * @return 结果
     */
    @Override
    public int updatest01_crm_product(St01_crm_product st01_crm_product)
    {
        st01_crm_product.setUpdateTime(DateUtils.getNowDate());
        return st01_crm_productMapper.updatest01_crm_product(st01_crm_product);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_productByProductIds(String[] productIds)
    {
        return st01_crm_productMapper.deletest01_crm_productByProductIds(productIds);
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_productByProductId(String productId)
    {
        return st01_crm_productMapper.deletest01_crm_productByProductId(productId);
    }
}
