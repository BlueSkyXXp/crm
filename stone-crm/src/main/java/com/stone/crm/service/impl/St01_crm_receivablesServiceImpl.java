package com.stone.crm.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.stone.common.core.service.BaseService;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.IdWorker;
import com.stone.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St01_crm_receivablesMapper;
import com.stone.crm.domain.St01_crm_receivables;
import com.stone.crm.service.Ist01_crm_receivablesService;

/**
 * 回款Service业务层处理
 * 
 * @author stone
 * @date 2024-04-25
 */
@Service
public class St01_crm_receivablesServiceImpl extends BaseService implements Ist01_crm_receivablesService
{
    @Autowired
    private St01_crm_receivablesMapper st01_crm_receivablesMapper;

    /**
     * 查询回款
     * 
     * @param receivablesId 回款主键
     * @return 回款
     */
    @Override
    public St01_crm_receivables selectst01_crm_receivablesByReceivablesId(String receivablesId)
    {
        St01_crm_receivables result = st01_crm_receivablesMapper.selectst01_crm_receivablesByReceivablesId(receivablesId);
        transBean(result);
        return result;
    }

    /**
     * 查询回款列表
     * 
     * @param st01_crm_receivables 回款
     * @return 回款
     */
    @Override
    public List<St01_crm_receivables> selectst01_crm_receivablesList(St01_crm_receivables st01_crm_receivables)
    {
        List<St01_crm_receivables> resultList=st01_crm_receivablesMapper.selectst01_crm_receivablesList(st01_crm_receivables);
        transBeanInList(resultList);
        return resultList;
    }

    /**
     * 新增回款
     * 
     * @param st01_crm_receivables 回款
     * @return 结果
     */
    @Override
    public int insertst01_crm_receivables(St01_crm_receivables st01_crm_receivables)
    {
        st01_crm_receivables.setReceivablesId(IdWorker.nextId());
        st01_crm_receivables.setCheckStatus("0");
        st01_crm_receivables.setCreatedBy(SecurityUtils.getUsername());
        st01_crm_receivables.setCreatedTime(DateUtils.getTime());
        return st01_crm_receivablesMapper.insertst01_crm_receivables(st01_crm_receivables);
    }

    /**
     * 修改回款
     * 
     * @param st01_crm_receivables 回款
     * @return 结果
     */
    @Override
    public int updatest01_crm_receivables(St01_crm_receivables st01_crm_receivables)
    {
        return st01_crm_receivablesMapper.updatest01_crm_receivables(st01_crm_receivables);
    }

    /**
     * 批量删除回款
     * 
     * @param receivablesIds 需要删除的回款主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_receivablesByReceivablesIds(String[] receivablesIds)
    {
        return st01_crm_receivablesMapper.deletest01_crm_receivablesByReceivablesIds(receivablesIds);
    }

    /**
     * 删除回款信息
     * 
     * @param receivablesId 回款主键
     * @return 结果
     */
    @Override
    public int deletest01_crm_receivablesByReceivablesId(String receivablesId)
    {
        return st01_crm_receivablesMapper.deletest01_crm_receivablesByReceivablesId(receivablesId);
    }
}
