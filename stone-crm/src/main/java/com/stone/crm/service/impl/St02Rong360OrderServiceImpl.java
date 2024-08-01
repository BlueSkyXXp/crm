package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02Rong360OrderMapper;
import com.stone.crm.domain.St02Rong360Order;
import com.stone.crm.service.ISt02Rong360OrderService;

/**
 * 融360订单数据Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@Service
public class St02Rong360OrderServiceImpl implements ISt02Rong360OrderService 
{
    @Autowired
    private St02Rong360OrderMapper st02Rong360OrderMapper;

    /**
     * 查询融360订单数据
     * 
     * @param id 融360订单数据主键
     * @return 融360订单数据
     */
    @Override
    public St02Rong360Order selectSt02Rong360OrderById(Long id)
    {
        return st02Rong360OrderMapper.selectSt02Rong360OrderById(id);
    }

    /**
     * 查询融360订单数据列表
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 融360订单数据
     */
    @Override
    public List<St02Rong360Order> selectSt02Rong360OrderList(St02Rong360Order st02Rong360Order)
    {
        return st02Rong360OrderMapper.selectSt02Rong360OrderList(st02Rong360Order);
    }


    @Override
    public List<St02Rong360Order> selectSt02Rong360OrderListNotDeletedOrderByCreateTimeDesc()
    {
        return st02Rong360OrderMapper.selectSt02Rong360OrderListNotDeletedOrderByCreateTimeDesc();
    }

    /**
     * 新增融360订单数据
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 结果
     */
    @Override
    public int insertSt02Rong360Order(St02Rong360Order st02Rong360Order)
    {
        st02Rong360Order.setCreateTime(DateUtils.getNowDate());
        return st02Rong360OrderMapper.insertSt02Rong360Order(st02Rong360Order);
    }

    /**
     * 修改融360订单数据
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 结果
     */
    @Override
    public int updateSt02Rong360Order(St02Rong360Order st02Rong360Order)
    {
        return st02Rong360OrderMapper.updateSt02Rong360Order(st02Rong360Order);
    }

    /**
     * 批量删除融360订单数据
     * 
     * @param ids 需要删除的融360订单数据主键
     * @return 结果
     */
    @Override
    public int deleteSt02Rong360OrderByIds(Long[] ids)
    {
        return st02Rong360OrderMapper.deleteSt02Rong360OrderByIds(ids);
    }

    /**
     * 删除融360订单数据信息
     * 
     * @param id 融360订单数据主键
     * @return 结果
     */
    @Override
    public int deleteSt02Rong360OrderById(Long id)
    {
        return st02Rong360OrderMapper.deleteSt02Rong360OrderById(id);
    }
}
