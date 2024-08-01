package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_leads;

/**
 * 线索Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_leadsService 
{
    /**
     * 查询线索
     * 
     * @param leadsId 线索主键
     * @return 线索
     */
    public St01_crm_leads selectst01_crm_leadsByLeadsId(String leadsId);

    /**
     * 查询线索列表
     * 
     * @param st01_crm_leads 线索
     * @return 线索集合
     */
    public List<St01_crm_leads> selectst01_crm_leadsList(St01_crm_leads st01_crm_leads);

    /**
     * 新增线索
     * 
     * @param st01_crm_leads 线索
     * @return 结果
     */
    public int insertst01_crm_leads(St01_crm_leads st01_crm_leads);

    /**
     * 修改线索
     * 
     * @param st01_crm_leads 线索
     * @return 结果
     */
    public int updatest01_crm_leads(St01_crm_leads st01_crm_leads);

    /**
     * 批量删除线索
     * 
     * @param leadsIds 需要删除的线索主键集合
     * @return 结果
     */
    public int deletest01_crm_leadsByLeadsIds(String[] leadsIds);

    /**
     * 删除线索信息
     * 
     * @param leadsId 线索主键
     * @return 结果
     */
    public int deletest01_crm_leadsByLeadsId(String leadsId);
}
