package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_contacts_businessMapper;
import com.stone.crm.domain.St01_crm_contacts_business;
import com.stone.crm.service.Ist01_crm_contacts_businessService;

/**
 * 商机联系人关联Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_contacts_businessServiceImpl implements Ist01_crm_contacts_businessService
{
    @Autowired
    private St01_crm_contacts_businessMapper st01_crm_contacts_businessMapper;

    /**
     * 查询商机联系人关联
     * 
     * @param id 商机联系人关联主键
     * @return 商机联系人关联
     */
    @Override
    public St01_crm_contacts_business selectst01_crm_contacts_businessById(String id)
    {
        return st01_crm_contacts_businessMapper.selectst01_crm_contacts_businessById(id);
    }

    /**
     * 查询商机联系人关联列表
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 商机联系人关联
     */
    @Override
    public List<St01_crm_contacts_business> selectst01_crm_contacts_businessList(St01_crm_contacts_business st01_crm_contacts_business)
    {
        return st01_crm_contacts_businessMapper.selectst01_crm_contacts_businessList(st01_crm_contacts_business);
    }

    /**
     * 新增商机联系人关联
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 结果
     */
    @Override
    public int insertst01_crm_contacts_business(St01_crm_contacts_business st01_crm_contacts_business)
    {
        return st01_crm_contacts_businessMapper.insertst01_crm_contacts_business(st01_crm_contacts_business);
    }

    /**
     * 修改商机联系人关联
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 结果
     */
    @Override
    public int updatest01_crm_contacts_business(St01_crm_contacts_business st01_crm_contacts_business)
    {
        return st01_crm_contacts_businessMapper.updatest01_crm_contacts_business(st01_crm_contacts_business);
    }

    /**
     * 批量删除商机联系人关联
     * 
     * @param ids 需要删除的商机联系人关联主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contacts_businessByIds(String[] ids)
    {
        return st01_crm_contacts_businessMapper.deletest01_crm_contacts_businessByIds(ids);
    }

    /**
     * 删除商机联系人关联信息
     * 
     * @param id 商机联系人关联主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contacts_businessById(String id)
    {
        return st01_crm_contacts_businessMapper.deletest01_crm_contacts_businessById(id);
    }
}
