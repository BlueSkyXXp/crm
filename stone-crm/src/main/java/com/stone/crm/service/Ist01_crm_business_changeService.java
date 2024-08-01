package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St01_crm_business_change;

/**
 * 商机阶段变化Service接口
 * 
 * @author stone
 * @date 2024-04-25
 */
public interface Ist01_crm_business_changeService 
{
    /**
     * 查询商机阶段变化
     * 
     * @param changeId 商机阶段变化主键
     * @return 商机阶段变化
     */
    public St01_crm_business_change selectst01_crm_business_changeByChangeId(String changeId);

    /**
     * 查询商机阶段变化列表
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 商机阶段变化集合
     */
    public List<St01_crm_business_change> selectst01_crm_business_changeList(St01_crm_business_change st01_crm_business_change);

    /**
     * 新增商机阶段变化
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 结果
     */
    public int insertst01_crm_business_change(St01_crm_business_change st01_crm_business_change);

    /**
     * 修改商机阶段变化
     * 
     * @param st01_crm_business_change 商机阶段变化
     * @return 结果
     */
    public int updatest01_crm_business_change(St01_crm_business_change st01_crm_business_change);

    /**
     * 批量删除商机阶段变化
     * 
     * @param changeIds 需要删除的商机阶段变化主键集合
     * @return 结果
     */
    public int deletest01_crm_business_changeByChangeIds(String[] changeIds);

    /**
     * 删除商机阶段变化信息
     * 
     * @param changeId 商机阶段变化主键
     * @return 结果
     */
    public int deletest01_crm_business_changeByChangeId(String changeId);
}
