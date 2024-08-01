package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_owner_record;

/**
 * 负责人变更记录Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_owner_recordService 
{
    /**
     * 查询负责人变更记录
     * 
     * @param recordId 负责人变更记录主键
     * @return 负责人变更记录
     */
    public St01_crm_owner_record selectst01_crm_owner_recordByRecordId(String recordId);

    /**
     * 查询负责人变更记录列表
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 负责人变更记录集合
     */
    public List<St01_crm_owner_record> selectst01_crm_owner_recordList(St01_crm_owner_record st01_crm_owner_record);

    /**
     * 新增负责人变更记录
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 结果
     */
    public int insertst01_crm_owner_record(St01_crm_owner_record st01_crm_owner_record);

    /**
     * 修改负责人变更记录
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 结果
     */
    public int updatest01_crm_owner_record(St01_crm_owner_record st01_crm_owner_record);

    /**
     * 批量删除负责人变更记录
     * 
     * @param recordIds 需要删除的负责人变更记录主键集合
     * @return 结果
     */
    public int deletest01_crm_owner_recordByRecordIds(String[] recordIds);

    /**
     * 删除负责人变更记录信息
     * 
     * @param recordId 负责人变更记录主键
     * @return 结果
     */
    public int deletest01_crm_owner_recordByRecordId(String recordId);
}