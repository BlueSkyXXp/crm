package com.stone.crm.service;

import java.util.List;
import java.util.Map;

import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.vo.CustomerReqVo;

/**
 * 客户Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
public interface ISt02CrmCustomerService 
{
    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    public St02CrmCustomer selectSt02CrmCustomerById(Long id);

    public List<St02CrmCustomer> selectSt02CrmCustomerByIds(Long ids);


    /**
     * 查询客户列表
     * 
     * @param st02CrmCustomer 客户
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectSt02CrmCustomerList(St02CrmCustomer st02CrmCustomer);

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCrmCustomerList(CustomerReqVo customerReqVo);

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCrmCustomerListOwner(CustomerReqVo customerReqVo);

    /**
     * 查询客户列表
     *
     * @param customerReqVo 业务员ID
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectTeamCustomerList(CustomerReqVo customerReqVo);

    /**
     * 查询公共池客户列表
     *
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCustomerPublicList(CustomerReqVo customerReqVo);

    /**
     * 新增客户
     * 
     * @param st02CrmCustomer 客户
     * @return 结果
     */
    public int insertSt02CrmCustomer(St02CrmCustomer st02CrmCustomer);

    /**
     * 修改客户
     * 
     * @param st02CrmCustomer 客户
     * @return 结果
     */
    public int updateSt02CrmCustomer(St02CrmCustomer st02CrmCustomer);

    /**
     * 批量分配客户
     * @param map 请求参数
//     * @param userId 业务员ID
//     * @param ids 需要删除的客户主键集合
     * @return 结果
     */
    public int assignCustomerUserIdByIds(Map<String, Object> map);

    /**
     * 批量移动客户至公共池
     *
     * @param ids 需要删除的客户主键集合
     * @return 结果
     */
    public int removeCustomerUserIdByIds(Long[] ids);

    /**
     * 自动移动客户至公共池
     *
     * @param lastRemarkDate 需要删除的客户主键集合
     * @return 结果
     */
    public int autoRemoveCustomer2Public(String lastRemarkDate);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的客户主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerByIds(Long[] ids);

    /**
     * 删除客户信息
     * 
     * @param id 客户主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerById(Long id);

    /**
     *
     * @return 返回所有的客户来源
     */
    public List<String> selectLoanChannelType();
}
