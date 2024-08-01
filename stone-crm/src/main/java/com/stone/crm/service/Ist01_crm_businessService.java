package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_business;

/**
 * 商机Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_businessService 
{
    /**
     * 查询商机
     * 
     * @param businessId 商机主键
     * @return 商机
     */
    public St01_crm_business selectst01_crm_businessByBusinessId(String businessId);

    /**
     * 查询商机列表
     * 
     * @param st01_crm_business 商机
     * @return 商机集合
     */
    public List<St01_crm_business> selectst01_crm_businessList(St01_crm_business st01_crm_business);

    /**
     * 新增商机
     * 
     * @param st01_crm_business 商机
     * @return 结果
     */
    public int insertst01_crm_business(St01_crm_business st01_crm_business);

    /**
     * 修改商机
     * 
     * @param st01_crm_business 商机
     * @return 结果
     */
    public int updatest01_crm_business(St01_crm_business st01_crm_business);

    /**
     * 批量删除商机
     * 
     * @param businessIds 需要删除的商机主键集合
     * @return 结果
     */
    public int deletest01_crm_businessByBusinessIds(String[] businessIds);

    /**
     * 删除商机信息
     * 
     * @param businessId 商机主键
     * @return 结果
     */
    public int deletest01_crm_businessByBusinessId(String businessId);
}
