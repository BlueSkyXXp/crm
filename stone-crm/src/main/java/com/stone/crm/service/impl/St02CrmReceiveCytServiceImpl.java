package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmReceiveCytMapper;
import com.stone.crm.domain.St02CrmReceiveCyt;
import com.stone.crm.service.ISt02CrmReceiveCytService;

/**
 * 进线数据Service业务层处理
 *
 * @author xiongxiaopeng
 * @date 2024-07-24
 */
@Service
public class St02CrmReceiveCytServiceImpl implements ISt02CrmReceiveCytService
{
    @Autowired
    private St02CrmReceiveCytMapper st02CrmReceiveCytMapper;

    /**
     * 查询进线数据
     *
     * @param id 进线数据主键
     * @return 进线数据
     */
    @Override
    public St02CrmReceiveCyt selectSt02CrmReceiveCytById(Long id)
    {
        return st02CrmReceiveCytMapper.selectSt02CrmReceiveCytById(id);
    }

    /**
     * 查询进线数据列表
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 进线数据
     */
    @Override
    public List<St02CrmReceiveCyt> selectSt02CrmReceiveCytList(St02CrmReceiveCyt st02CrmReceiveCyt)
    {
        return st02CrmReceiveCytMapper.selectSt02CrmReceiveCytList(st02CrmReceiveCyt);
    }

    /**
     * 新增进线数据
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 结果
     */
    @Override
    public int insertSt02CrmReceiveCyt(St02CrmReceiveCyt st02CrmReceiveCyt)
    {
        return st02CrmReceiveCytMapper.insertSt02CrmReceiveCyt(st02CrmReceiveCyt);
    }

    /**
     * 修改进线数据
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 结果
     */
    @Override
    public int updateSt02CrmReceiveCyt(St02CrmReceiveCyt st02CrmReceiveCyt)
    {
        return st02CrmReceiveCytMapper.updateSt02CrmReceiveCyt(st02CrmReceiveCyt);
    }

    /**
     * 批量删除进线数据
     *
     * @param ids 需要删除的进线数据主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmReceiveCytByIds(Long[] ids)
    {
        return st02CrmReceiveCytMapper.deleteSt02CrmReceiveCytByIds(ids);
    }

    /**
     * 删除进线数据信息
     *
     * @param id 进线数据主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmReceiveCytById(Long id)
    {
        return st02CrmReceiveCytMapper.deleteSt02CrmReceiveCytById(id);
    }
}
