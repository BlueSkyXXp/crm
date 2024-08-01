package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_leadsMapper;
import com.stone.crm.domain.St01_crm_leads;
import com.stone.crm.service.Ist01_crm_leadsService;

/**
 * 线索Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_leadsServiceImpl implements Ist01_crm_leadsService
{
    @Autowired
    private St01_crm_leadsMapper st01_crm_leadsMapper;

    /**
     * 查询线索
     * 
     * @param leadsId 线索主键
     * @return 线索
     */
    @Override
    public St01_crm_leads selectst01_crm_leadsByLeadsId(String leadsId)
    {
        return st01_crm_leadsMapper.selectst01_crm_leadsByLeadsId(leadsId);
    }

    /**
     * 查询线索列表
     * 
     * @param st01_crm_leads 线索
     * @return 线索
     */
    @Override
    public List<St01_crm_leads> selectst01_crm_leadsList(St01_crm_leads st01_crm_leads)
    {
        return st01_crm_leadsMapper.selectst01_crm_leadsList(st01_crm_leads);
    }

    /**
     * 新增线索
     * 
     * @param st01_crm_leads 线索
     * @return 结果
     */
    @Override
    public int insertst01_crm_leads(St01_crm_leads st01_crm_leads)
    {
        return st01_crm_leadsMapper.insertst01_crm_leads(st01_crm_leads);
    }

    /**
     * 修改线索
     * 
     * @param st01_crm_leads 线索
     * @return 结果
     */
    @Override
    public int updatest01_crm_leads(St01_crm_leads st01_crm_leads)
    {
        return st01_crm_leadsMapper.updatest01_crm_leads(st01_crm_leads);
    }

    /**
     * 批量删除线索
     * 
     * @param leadsIds 需要删除的线索主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_leadsByLeadsIds(String[] leadsIds)
    {
        return st01_crm_leadsMapper.deletest01_crm_leadsByLeadsIds(leadsIds);
    }

    /**
     * 删除线索信息
     * 
     * @param leadsId 线索主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_leadsByLeadsId(String leadsId)
    {
        return st01_crm_leadsMapper.deletest01_crm_leadsByLeadsId(leadsId);
    }
}
