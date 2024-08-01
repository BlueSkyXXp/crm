package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_contacts;

/**
 * 联系人Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_contactsService 
{
    /**
     * 查询联系人
     * 
     * @param contactsId 联系人主键
     * @return 联系人
     */
    public St01_crm_contacts selectst01_crm_contactsByContactsId(String contactsId);

    /**
     * 查询联系人列表
     * 
     * @param st01_crm_contacts 联系人
     * @return 联系人集合
     */
    public List<St01_crm_contacts> selectst01_crm_contactsList(St01_crm_contacts st01_crm_contacts);

    /**
     * 新增联系人
     * 
     * @param st01_crm_contacts 联系人
     * @return 结果
     */
    public int insertst01_crm_contacts(St01_crm_contacts st01_crm_contacts);

    /**
     * 修改联系人
     * 
     * @param st01_crm_contacts 联系人
     * @return 结果
     */
    public int updatest01_crm_contacts(St01_crm_contacts st01_crm_contacts);

    /**
     * 批量删除联系人
     * 
     * @param contactsIds 需要删除的联系人主键集合
     * @return 结果
     */
    public int deletest01_crm_contactsByContactsIds(String[] contactsIds);

    /**
     * 删除联系人信息
     * 
     * @param contactsId 联系人主键
     * @return 结果
     */
    public int deletest01_crm_contactsByContactsId(String contactsId);
}
