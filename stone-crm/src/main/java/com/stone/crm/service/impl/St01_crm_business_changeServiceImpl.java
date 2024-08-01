package com.stone.crm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_business_changeMapper;
import com.stone.crm.domain.St01_crm_business_change;
import com.stone.crm.service.Ist01_crm_business_changeService;

/**
 * 商机阶段变化Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_business_changeServiceImpl implements Ist01_crm_business_changeService
{
    @Autowired
    private St01_crm_business_changeMapper st01_crm_business_changeMapper;

    /**
     * 查询商机阶段变化
     * 
     * @param changeId 商机阶段变化主键
     * @return 商机阶段变化
     */
    @Override
    public St01_crm_business_change selectst01_crm_business_changeByChangeId(String changeId)
    {
        return st01_crm_business_changeMapper.selectst01_crm_business_changeByChangeId(changeId);
    }

    /**
     * 查询商机阶段变化列表
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 商机阶段变化
     */
    @Override
    public List<St01_crm_business_change> selectst01_crm_business_changeList(St01_crm_business_change st01_crm_business_change)
    {
        return st01_crm_business_changeMapper.selectst01_crm_business_changeList(st01_crm_business_change);
    }

    /**
     * 新增商机阶段变化
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 结果
     */
    @Override
    public int insertst01_crm_business_change(St01_crm_business_change st01_crm_business_change)
    {
        return st01_crm_business_changeMapper.insertst01_crm_business_change(st01_crm_business_change);
    }

    /**
     * 修改商机阶段变化
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 结果
     */
    @Override
    public int updatest01_crm_business_change(St01_crm_business_change st01_crm_business_change)
    {
        return st01_crm_business_changeMapper.updatest01_crm_business_change(st01_crm_business_change);
    }

    /**
     * 批量删除商机阶段变化
     * 
     * @param changeIds 需要删除的商机阶段变化主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_business_changeByChangeIds(String[] changeIds)
    {
        return st01_crm_business_changeMapper.deletest01_crm_business_changeByChangeIds(changeIds);
    }

    /**
     * 删除商机阶段变化信息
     * 
     * @param changeId 商机阶段变化主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_business_changeByChangeId(String changeId)
    {
        return st01_crm_business_changeMapper.deletest01_crm_business_changeByChangeId(changeId);
    }
}
