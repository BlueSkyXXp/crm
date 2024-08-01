package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_receivables;

/**
 * 回款Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_receivablesMapper
{
    /**
     * 查询回款
     * 
     * @param receivablesId 回款主键
     * @return 回款
     */
    public St01_crm_receivables selectst01_crm_receivablesByReceivablesId(String receivablesId);

    /**
     * 查询回款列表
     * 
     * @param st01_crm_receivables 回款
     * @return 回款集合
     */
    public List<St01_crm_receivables> selectst01_crm_receivablesList(St01_crm_receivables st01_crm_receivables);

    /**
     * 新增回款
     * 
     * @param st01_crm_receivables 回款
     * @return 结果
     */
    public int insertst01_crm_receivables(St01_crm_receivables st01_crm_receivables);

    /**
     * 修改回款
     * 
     * @param st01_crm_receivables 回款
     * @return 结果
     */
    public int updatest01_crm_receivables(St01_crm_receivables st01_crm_receivables);

    /**
     * 删除回款
     * 
     * @param receivablesId 回款主键
     * @return 结果
     */
    public int deletest01_crm_receivablesByReceivablesId(String receivablesId);

    /**
     * 批量删除回款
     * 
     * @param receivablesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_receivablesByReceivablesIds(String[] receivablesIds);
}
