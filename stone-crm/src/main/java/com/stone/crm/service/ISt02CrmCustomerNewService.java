package com.stone.crm.service;

import java.util.List;

import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmCustomerNew;
import com.stone.crm.domain.vo.CustomerReqVo;

/**
 * 待分配客户Service接口
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
public interface ISt02CrmCustomerNewService
{
    /**
     * 查询待分配客户
     *
     * @param id 待分配客户主键
     * @return 待分配客户
     */
    public St02CrmCustomerNew selectSt02CrmCustomerNewById(Long id);

    public List<St02CrmCustomerNew> selectSt02CrmCustomerNewByIds(Long[] ids);

    /**
     * 查询待分配客户列表
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 待分配客户集合
     */
    public List<St02CrmCustomerNew> selectSt02CrmCustomerNewList(St02CrmCustomerNew st02CrmCustomerNew);

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomerNew> selectCrmCustomerNewList(CustomerReqVo customerReqVo);

    /**
     * 新增待分配客户
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 结果
     */
    public int insertSt02CrmCustomerNew(St02CrmCustomerNew st02CrmCustomerNew);

    /**
     * 修改待分配客户
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 结果
     */
    public int updateSt02CrmCustomerNew(St02CrmCustomerNew st02CrmCustomerNew);

    /**
     * 批量删除待分配客户
     *
     * @param ids 需要删除的待分配客户主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerNewByIds(Long[] ids);

    /**
     * 删除待分配客户信息
     *
     * @param id 待分配客户主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerNewById(Long id);
}
