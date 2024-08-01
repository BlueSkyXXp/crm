package com.stone.system.service;

import com.stone.common.utils.equator.EquatorFieldInfo;
import com.stone.system.domain.SysFormLog;

import java.util.List;

/**
 * 单变更日志记录Service接口
 *
 * @author ruoyi
 * @date 2024-06-07
 */
public interface ISysFormLogService
{
    /**
     * 查询单变更日志记录
     *
     * @param customerId 单变更日志记录主键
     * @return 单变更日志记录
     */
    public SysFormLog selectSysFormLogByCustomerId(String customerId);

    /**
     * 查询单变更日志记录列表
     *
     * @param sysFormLog 单变更日志记录
     * @return 单变更日志记录集合
     */
    public List<SysFormLog> selectSysFormLogList(SysFormLog sysFormLog) throws ClassNotFoundException;

    /**
     * 新增单变更日志记录
     *
     * @param sysFormLog 单变更日志记录
     * @return 结果
     */
    public int insertSysFormLog(SysFormLog sysFormLog);

    /**
     * 修改单变更日志记录
     *
     * @param sysFormLog 单变更日志记录
     * @return 结果
     */
    public int updateSysFormLog(SysFormLog sysFormLog);

    /**
     * 批量删除单变更日志记录
     *
     * @param customerIds 需要删除的单变更日志记录主键集合
     * @return 结果
     */
    public int deleteSysFormLogByCustomerIds(String[] customerIds);

    /**
     * 删除单变更日志记录信息
     *
     * @param customerId 单变更日志记录主键
     * @return 结果
     */
    public int deleteSysFormLogByCustomerId(String customerId);
}
