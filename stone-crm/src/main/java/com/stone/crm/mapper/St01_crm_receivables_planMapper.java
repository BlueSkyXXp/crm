package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St01_crm_receivables_plan;

/**
 * 回款计划Mapper接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface St01_crm_receivables_planMapper
{
    /**
     * 查询回款计划
     * 
     * @param planId 回款计划主键
     * @return 回款计划
     */
    public St01_crm_receivables_plan selectst01_crm_receivables_planByPlanId(String planId);

    /**
     * 查询回款计划列表
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 回款计划集合
     */
    public List<St01_crm_receivables_plan> selectst01_crm_receivables_planList(St01_crm_receivables_plan st01_crm_receivables_plan);

    /**
     * 新增回款计划
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 结果
     */
    public int insertst01_crm_receivables_plan(St01_crm_receivables_plan st01_crm_receivables_plan);

    /**
     * 修改回款计划
     * 
     * @param st01_crm_receivables_plan 回款计划
     * @return 结果
     */
    public int updatest01_crm_receivables_plan(St01_crm_receivables_plan st01_crm_receivables_plan);

    /**
     * 删除回款计划
     * 
     * @param planId 回款计划主键
     * @return 结果
     */
    public int deletest01_crm_receivables_planByPlanId(String planId);

    /**
     * 批量删除回款计划
     * 
     * @param planIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletest01_crm_receivables_planByPlanIds(String[] planIds);
}
