package com.stone.crm.service.impl;

import java.util.List;

import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_receivables_planMapper;
import com.stone.crm.domain.St01_crm_receivables_plan;
import com.stone.crm.service.Ist01_crm_receivables_planService;

/**
 * 回款计划Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_receivables_planServiceImpl extends BaseService implements Ist01_crm_receivables_planService
{
    @Autowired
    private St01_crm_receivables_planMapper st01_crm_receivables_planMapper;

    /**
     * 查询回款计划
     * 
     * @param planId 回款计划主键
     * @return 回款计划
     */
    @Override
    public St01_crm_receivables_plan selectst01_crm_receivables_planByPlanId(String planId)
    {
        return st01_crm_receivables_planMapper.selectst01_crm_receivables_planByPlanId(planId);
    }

    /**
     * 查询回款计划列表
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 回款计划
     */
    @Override
    public List<St01_crm_receivables_plan> selectst01_crm_receivables_planList(St01_crm_receivables_plan st01_crm_receivables_plan)
    {
        List<St01_crm_receivables_plan> resultList= st01_crm_receivables_planMapper.selectst01_crm_receivables_planList(st01_crm_receivables_plan);
        transBeanInList(resultList);
        return resultList;
    }

    /**
     * 新增回款计划
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 结果
     */
    @Override
    public int insertst01_crm_receivables_plan(List<St01_crm_receivables_plan> st01_crm_receivables_plans)
    {
        st01_crm_receivables_plans.forEach((item)->{
            item.setPlanId(IdWorker.nextId());
            item.setStatus("0");
            item.setCreatedBy(SecurityUtils.getUsername());
            item.setCreatedTime(DateUtils.getTime());
           st01_crm_receivables_planMapper.insertst01_crm_receivables_plan(item);
        });
        return 1;
    }

    /**
     * 修改回款计划
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 结果
     */
    @Override
    public int updatest01_crm_receivables_plan(St01_crm_receivables_plan st01_crm_receivables_plan)
    {
        st01_crm_receivables_plan.setUpdatedBy(SecurityUtils.getUsername());
        st01_crm_receivables_plan.setUpdatedTime(DateUtils.getTime());
        return st01_crm_receivables_planMapper.updatest01_crm_receivables_plan(st01_crm_receivables_plan);
    }

    /**
     * 批量删除回款计划
     * 
     * @param planIds 需要删除的回款计划主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_receivables_planByPlanIds(String[] planIds)
    {
        return st01_crm_receivables_planMapper.deletest01_crm_receivables_planByPlanIds(planIds);
    }

    /**
     * 删除回款计划信息
     * 
     * @param planId 回款计划主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_receivables_planByPlanId(String planId)
    {
        return st01_crm_receivables_planMapper.deletest01_crm_receivables_planByPlanId(planId);
    }
}
