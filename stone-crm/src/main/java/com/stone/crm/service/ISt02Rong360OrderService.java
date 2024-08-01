package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02Rong360Order;

/**
 * 融360订单数据Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
public interface ISt02Rong360OrderService 
{
    /**
     * 查询融360订单数据
     * 
     * @param id 融360订单数据主键
     * @return 融360订单数据
     */
    public St02Rong360Order selectSt02Rong360OrderById(Long id);

    /**
     * 查询融360订单数据列表
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 融360订单数据集合
     */
    public List<St02Rong360Order> selectSt02Rong360OrderList(St02Rong360Order st02Rong360Order);

    public List<St02Rong360Order> selectSt02Rong360OrderListNotDeletedOrderByCreateTimeDesc();

    /**
     * 新增融360订单数据
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 结果
     */
    public int insertSt02Rong360Order(St02Rong360Order st02Rong360Order);

    /**
     * 修改融360订单数据
     * 
     * @param st02Rong360Order 融360订单数据
     * @return 结果
     */
    public int updateSt02Rong360Order(St02Rong360Order st02Rong360Order);

    /**
     * 批量删除融360订单数据
     * 
     * @param ids 需要删除的融360订单数据主键集合
     * @return 结果
     */
    public int deleteSt02Rong360OrderByIds(Long[] ids);

    /**
     * 删除融360订单数据信息
     * 
     * @param id 融360订单数据主键
     * @return 结果
     */
    public int deleteSt02Rong360OrderById(Long id);
}
