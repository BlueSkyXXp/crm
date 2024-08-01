package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_product_categoryMapper;
import com.stone.crm.domain.St01_crm_product_category;
import com.stone.crm.service.Ist01_crm_product_categoryService;

/**
 * 产品分类Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_product_categoryServiceImpl implements Ist01_crm_product_categoryService
{
    @Autowired
    private St01_crm_product_categoryMapper st01_crm_product_categoryMapper;

    /**
     * 查询产品分类
     * 
     * @param categoryId 产品分类主键
     * @return 产品分类
     */
    @Override
    public St01_crm_product_category selectst01_crm_product_categoryByCategoryId(String categoryId)
    {
        return st01_crm_product_categoryMapper.selectst01_crm_product_categoryByCategoryId(categoryId);
    }

    /**
     * 查询产品分类列表
     * 
     * @param st01_crm_product_category 产品分类
     * @return 产品分类
     */
    @Override
    public List<St01_crm_product_category> selectst01_crm_product_categoryList(St01_crm_product_category st01_crm_product_category)
    {
        return st01_crm_product_categoryMapper.selectst01_crm_product_categoryList(st01_crm_product_category);
    }

    /**
     * 新增产品分类
     * 
     * @param st01_crm_product_category 产品分类
     * @return 结果
     */
    @Override
    public int insertst01_crm_product_category(St01_crm_product_category st01_crm_product_category)
    {
        st01_crm_product_category.setCategoryId(IdWorker.nextId());
        st01_crm_product_category.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_product_category.setCreatedTime(DateUtils.getTime());
        return st01_crm_product_categoryMapper.insertst01_crm_product_category(st01_crm_product_category);
    }

    /**
     * 修改产品分类
     * 
     * @param st01_crm_product_category 产品分类
     * @return 结果
     */
    @Override
    public int updatest01_crm_product_category(St01_crm_product_category st01_crm_product_category)
    {
        return st01_crm_product_categoryMapper.updatest01_crm_product_category(st01_crm_product_category);
    }

    /**
     * 批量删除产品分类
     * 
     * @param categoryIds 需要删除的产品分类主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_product_categoryByCategoryIds(String[] categoryIds)
    {
        return st01_crm_product_categoryMapper.deletest01_crm_product_categoryByCategoryIds(categoryIds);
    }

    /**
     * 删除产品分类信息
     * 
     * @param categoryId 产品分类主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_product_categoryByCategoryId(String categoryId)
    {
        return st01_crm_product_categoryMapper.deletest01_crm_product_categoryByCategoryId(categoryId);
    }
}
