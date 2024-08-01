package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St02CrmReceiveCyt;

/**
 * 进线数据Mapper接口
 *
 * @author xiongxiaopeng
 * @date 2024-07-24
 */
public interface St02CrmReceiveCytMapper
{
    /**
     * 查询进线数据
     *
     * @param id 进线数据主键
     * @return 进线数据
     */
    public St02CrmReceiveCyt selectSt02CrmReceiveCytById(Long id);

    /**
     * 查询进线数据列表
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 进线数据集合
     */
    public List<St02CrmReceiveCyt> selectSt02CrmReceiveCytList(St02CrmReceiveCyt st02CrmReceiveCyt);

    /**
     * 新增进线数据
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 结果
     */
    public int insertSt02CrmReceiveCyt(St02CrmReceiveCyt st02CrmReceiveCyt);

    /**
     * 修改进线数据
     *
     * @param st02CrmReceiveCyt 进线数据
     * @return 结果
     */
    public int updateSt02CrmReceiveCyt(St02CrmReceiveCyt st02CrmReceiveCyt);

    /**
     * 删除进线数据
     *
     * @param id 进线数据主键
     * @return 结果
     */
    public int deleteSt02CrmReceiveCytById(Long id);

    /**
     * 批量删除进线数据
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSt02CrmReceiveCytByIds(Long[] ids);
}
