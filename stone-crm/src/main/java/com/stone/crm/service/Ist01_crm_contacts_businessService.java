package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_contacts_business;

/**
 * 商机联系人关联Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_contacts_businessService 
{
    /**
     * 查询商机联系人关联
     * 
     * @param id 商机联系人关联主键
     * @return 商机联系人关联
     */
    public St01_crm_contacts_business selectst01_crm_contacts_businessById(String id);

    /**
     * 查询商机联系人关联列表
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 商机联系人关联集合
     */
    public List<St01_crm_contacts_business> selectst01_crm_contacts_businessList(St01_crm_contacts_business st01_crm_contacts_business);

    /**
     * 新增商机联系人关联
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 结果
     */
    public int insertst01_crm_contacts_business(St01_crm_contacts_business st01_crm_contacts_business);

    /**
     * 修改商机联系人关联
     * 
     * @param st01_crm_contacts_business 商机联系人关联
     * @return 结果
     */
    public int updatest01_crm_contacts_business(St01_crm_contacts_business st01_crm_contacts_business);

    /**
     * 批量删除商机联系人关联
     * 
     * @param ids 需要删除的商机联系人关联主键集合
     * @return 结果
     */
    public int deletest01_crm_contacts_businessByIds(String[] ids);

    /**
     * 删除商机联系人关联信息
     * 
     * @param id 商机联系人关联主键
     * @return 结果
     */
    public int deletest01_crm_contacts_businessById(String id);
}
