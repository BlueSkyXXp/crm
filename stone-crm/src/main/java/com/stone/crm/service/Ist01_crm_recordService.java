package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_record;

/**
 * 跟进记录Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_recordService 
{
    /**
     * 查询跟进记录
     * 
     * @param recordId 跟进记录主键
     * @return 跟进记录
     */
    public St01_crm_record selectst01_crm_recordByRecordId(String recordId);

    /**
     * 查询跟进记录列表
     * 
     * @param st01_crm_record 跟进记录
     * @return 跟进记录集合
     */
    public List<St01_crm_record> selectst01_crm_recordList(St01_crm_record st01_crm_record);

    /**
     * 新增跟进记录
     * 
     * @param st01_crm_record 跟进记录
     * @return 结果
     */
    public int insertst01_crm_record(St01_crm_record st01_crm_record);

    /**
     * 修改跟进记录
     * 
     * @param st01_crm_record 跟进记录
     * @return 结果
     */
    public int updatest01_crm_record(St01_crm_record st01_crm_record);

    /**
     * 批量删除跟进记录
     * 
     * @param recordIds 需要删除的跟进记录主键集合
     * @return 结果
     */
    public int deletest01_crm_recordByRecordIds(String[] recordIds);

    /**
     * 删除跟进记录信息
     * 
     * @param recordId 跟进记录主键
     * @return 结果
     */
    public int deletest01_crm_recordByRecordId(String recordId);
}
