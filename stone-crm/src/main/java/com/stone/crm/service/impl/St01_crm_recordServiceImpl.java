package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.DictUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_recordMapper;
import com.stone.crm.domain.St01_crm_record;
import com.stone.crm.service.Ist01_crm_recordService;

/**
 * 跟进记录Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_recordServiceImpl extends BaseService implements Ist01_crm_recordService
{
    @Autowired
    private St01_crm_recordMapper st01_crm_recordMapper;

    /**
     * 查询跟进记录
     * 
     * @param recordId 跟进记录主键
     * @return 跟进记录
     */
    @Override
    public St01_crm_record selectst01_crm_recordByRecordId(String recordId)
    {
        return st01_crm_recordMapper.selectst01_crm_recordByRecordId(recordId);
    }

    /**
     * 查询跟进记录列表
     * 
     * @param st01_crm_record 跟进记录
     * @return 跟进记录
     */
    @Override
    public List<St01_crm_record> selectst01_crm_recordList(St01_crm_record st01_crm_record)
    {
        List<St01_crm_record> resultList=st01_crm_recordMapper.selectst01_crm_recordList(st01_crm_record);
        transBeanInList(resultList);
        return resultList;
    }

    /**
     * 新增跟进记录
     * 
     * @param st01_crm_record 跟进记录
     * @return 结果
     */
    @Override
    public int insertst01_crm_record(St01_crm_record st01_crm_record)
    {
        st01_crm_record.setRecordId(IdWorker.nextId());
        st01_crm_record.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_record.setCreatedTime(DateUtils.getTime());
        return st01_crm_recordMapper.insertst01_crm_record(st01_crm_record);
    }

    /**
     * 修改跟进记录
     * 
     * @param st01_crm_record 跟进记录
     * @return 结果
     */
    @Override
    public int updatest01_crm_record(St01_crm_record st01_crm_record)
    {
        return st01_crm_recordMapper.updatest01_crm_record(st01_crm_record);
    }

    /**
     * 批量删除跟进记录
     * 
     * @param recordIds 需要删除的跟进记录主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_recordByRecordIds(String[] recordIds)
    {
        return st01_crm_recordMapper.deletest01_crm_recordByRecordIds(recordIds);
    }

    /**
     * 删除跟进记录信息
     * 
     * @param recordId 跟进记录主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_recordByRecordId(String recordId)
    {
        return st01_crm_recordMapper.deletest01_crm_recordByRecordId(recordId);
    }
}
