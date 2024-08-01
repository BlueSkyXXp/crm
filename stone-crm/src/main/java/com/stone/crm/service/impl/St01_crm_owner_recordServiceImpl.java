package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_owner_recordMapper;
import com.stone.crm.domain.St01_crm_owner_record;
import com.stone.crm.service.Ist01_crm_owner_recordService;

/**
 * 负责人变更记录Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_owner_recordServiceImpl implements Ist01_crm_owner_recordService
{
    @Autowired
    private St01_crm_owner_recordMapper st01_crm_owner_recordMapper;

    /**
     * 查询负责人变更记录
     * 
     * @param recordId 负责人变更记录主键
     * @return 负责人变更记录
     */
    @Override
    public St01_crm_owner_record selectst01_crm_owner_recordByRecordId(String recordId)
    {
        return st01_crm_owner_recordMapper.selectst01_crm_owner_recordByRecordId(recordId);
    }

    /**
     * 查询负责人变更记录列表
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 负责人变更记录
     */
    @Override
    public List<St01_crm_owner_record> selectst01_crm_owner_recordList(St01_crm_owner_record st01_crm_owner_record)
    {
        return st01_crm_owner_recordMapper.selectst01_crm_owner_recordList(st01_crm_owner_record);
    }

    /**
     * 新增负责人变更记录
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 结果
     */
    @Override
    public int insertst01_crm_owner_record(St01_crm_owner_record st01_crm_owner_record)
    {
        return st01_crm_owner_recordMapper.insertst01_crm_owner_record(st01_crm_owner_record);
    }

    /**
     * 修改负责人变更记录
     * 
     * @param st01_crm_owner_record 负责人变更记录
     * @return 结果
     */
    @Override
    public int updatest01_crm_owner_record(St01_crm_owner_record st01_crm_owner_record)
    {
        return st01_crm_owner_recordMapper.updatest01_crm_owner_record(st01_crm_owner_record);
    }

    /**
     * 批量删除负责人变更记录
     * 
     * @param recordIds 需要删除的负责人变更记录主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_owner_recordByRecordIds(String[] recordIds)
    {
        return st01_crm_owner_recordMapper.deletest01_crm_owner_recordByRecordIds(recordIds);
    }

    /**
     * 删除负责人变更记录信息
     * 
     * @param recordId 负责人变更记录主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_owner_recordByRecordId(String recordId)
    {
        return st01_crm_owner_recordMapper.deletest01_crm_owner_recordByRecordId(recordId);
    }
}
