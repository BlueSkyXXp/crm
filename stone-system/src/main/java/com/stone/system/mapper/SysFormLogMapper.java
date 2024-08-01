package com.stone.system.mapper;

import java.util.List;
import com.stone.system.domain.SysFormLog;

/**
 * 单变更日志记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-07
 */
public interface SysFormLogMapper
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
    public List<SysFormLog> selectSysFormLogList(SysFormLog sysFormLog);

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
     * 删除单变更日志记录
     *
     * @param customerId 单变更日志记录主键
     * @return 结果
     */
    public int deleteSysFormLogByCustomerId(String customerId);

    /**
     * 批量删除单变更日志记录
     *
     * @param customerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysFormLogByCustomerIds(String[] customerIds);
}
