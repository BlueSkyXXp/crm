package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_contactsMapper;
import com.stone.crm.domain.St01_crm_contacts;
import com.stone.crm.service.Ist01_crm_contactsService;

/**
 * 联系人Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_contactsServiceImpl implements Ist01_crm_contactsService
{
    @Autowired
    private St01_crm_contactsMapper st01_crm_contactsMapper;

    /**
     * 查询联系人
     * 
     * @param contactsId 联系人主键
     * @return 联系人
     */
    @Override
    public St01_crm_contacts selectst01_crm_contactsByContactsId(String contactsId)
    {
        return st01_crm_contactsMapper.selectst01_crm_contactsByContactsId(contactsId);
    }

    /**
     * 查询联系人列表
     * 
     * @param st01_crm_contacts 联系人
     * @return 联系人
     */
    @Override
    public List<St01_crm_contacts> selectst01_crm_contactsList(St01_crm_contacts st01_crm_contacts)
    {
        return st01_crm_contactsMapper.selectst01_crm_contactsList(st01_crm_contacts);
    }

    /**
     * 新增联系人
     * 
     * @param st01_crm_contacts 联系人
     * @return 结果
     */
    @Override
    public int insertst01_crm_contacts(St01_crm_contacts st01_crm_contacts)
    {
        st01_crm_contacts.setContactsId(IdWorker.nextId());
        st01_crm_contacts.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_contacts.setCreatedTime(DateUtils.getTime());
        return st01_crm_contactsMapper.insertst01_crm_contacts(st01_crm_contacts);
    }

    /**
     * 修改联系人
     * 
     * @param st01_crm_contacts 联系人
     * @return 结果
     */
    @Override
    public int updatest01_crm_contacts(St01_crm_contacts st01_crm_contacts)
    {
        return st01_crm_contactsMapper.updatest01_crm_contacts(st01_crm_contacts);
    }

    /**
     * 批量删除联系人
     * 
     * @param contactsIds 需要删除的联系人主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contactsByContactsIds(String[] contactsIds)
    {
        return st01_crm_contactsMapper.deletest01_crm_contactsByContactsIds(contactsIds);
    }

    /**
     * 删除联系人信息
     * 
     * @param contactsId 联系人主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_contactsByContactsId(String contactsId)
    {
        return st01_crm_contactsMapper.deletest01_crm_contactsByContactsId(contactsId);
    }
}
