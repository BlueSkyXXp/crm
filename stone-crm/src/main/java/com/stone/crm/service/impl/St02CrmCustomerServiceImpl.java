package com.stone.crm.service.impl;

import java.util.List;
import java.util.Map;

import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.vo.CustomerReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmCustomerMapper;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.service.ISt02CrmCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Service
public class St02CrmCustomerServiceImpl implements ISt02CrmCustomerService 
{
    @Autowired
    private St02CrmCustomerMapper st02CrmCustomerMapper;

    /**
     * 查询客户
     * 
     * @param id 客户主键
     * @return 客户
     */
    @Override
    public St02CrmCustomer selectSt02CrmCustomerById(Long id)
    {
        return st02CrmCustomerMapper.selectSt02CrmCustomerById(id);
    }

    public List<St02CrmCustomer> selectSt02CrmCustomerByIds(Long ids)
    {
        return st02CrmCustomerMapper.selectSt02CrmCustomerByIds(ids);
    }

    /**
     * 查询客户列表
     * 
     * @param st02CrmCustomer 客户
     * @return 客户
     */
    @Override
    public List<St02CrmCustomer> selectSt02CrmCustomerList(St02CrmCustomer st02CrmCustomer)
    {
        return st02CrmCustomerMapper.selectSt02CrmCustomerList(st02CrmCustomer);
    }

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCrmCustomerList(CustomerReqVo customerReqVo) {
        return st02CrmCustomerMapper.selectCrmCustomerList(customerReqVo);
    }

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCrmCustomerListOwner(CustomerReqVo customerReqVo) {
        return st02CrmCustomerMapper.selectCrmCustomerListOwner(customerReqVo);
    }

    /**
     * 查询客户列表
     *
     * @param customerReqVo 业务员ID
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectTeamCustomerList(CustomerReqVo customerReqVo) {
        return st02CrmCustomerMapper.selectTeamCustomerList(customerReqVo);

    }

    /**
     * 查询公共池客户列表
     *
     * @return 客户集合
     */
    public List<St02CrmCustomer> selectCustomerPublicList(CustomerReqVo customerReqVo) {
        return st02CrmCustomerMapper.selectCustomerPublicList(customerReqVo);
    }

    /**
     * 新增客户
     * 
     * @param st02CrmCustomer 客户
     * @return 结果
     */
    @Override
    public int insertSt02CrmCustomer(St02CrmCustomer st02CrmCustomer)
    {
        st02CrmCustomer.setCreateTime(DateUtils.getNowDate());
        return st02CrmCustomerMapper.insertSt02CrmCustomer(st02CrmCustomer);
    }

    /**
     * 修改客户
     * 
     * @param st02CrmCustomer 客户
     * @return 结果
     */
    @Override
    public int updateSt02CrmCustomer(St02CrmCustomer st02CrmCustomer)
    {
        st02CrmCustomer.setUpdateTime(DateUtils.getNowDate());
        return st02CrmCustomerMapper.updateSt02CrmCustomer(st02CrmCustomer);
    }


    /**
     * 批量分配客户
     * @param map 请求参数
    //     * @param userId 业务员ID
    //     * @param ids 需要删除的客户主键集合
     * @return 结果
     */
    @Override
    public int assignCustomerUserIdByIds(Map<String, Object> map)
    {
        return st02CrmCustomerMapper.assignCustomerUserIdByIds(map);
    }

    /**
     * 批量移动客户至公共池
     *
     * @param ids 需要删除的客户主键集合
     * @return 结果
     */
    @Override
    public int removeCustomerUserIdByIds(Long[] ids)
    {
        return st02CrmCustomerMapper.removeCustomerUserIdByIds(ids);
    }

    /**
     * 自动移动客户至公共池
     *
     * @param lastRemarkDate 需要删除的客户主键集合
     * @return 结果
     */
    public int autoRemoveCustomer2Public(String lastRemarkDate) {
        return st02CrmCustomerMapper.autoRemoveCustomer2Public(lastRemarkDate);
    }

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerByIds(Long[] ids)
    {
        return st02CrmCustomerMapper.deleteSt02CrmCustomerByIds(ids);
    }

    /**
     * 删除客户信息
     * 
     * @param id 客户主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerById(Long id)
    {
        return st02CrmCustomerMapper.deleteSt02CrmCustomerById(id);
    }


    /**
     *
     * @return 返回所有的客户来源
     */
    public List<String> selectLoanChannelType() {
        return st02CrmCustomerMapper.selectLoanChannelType();
    }
}
