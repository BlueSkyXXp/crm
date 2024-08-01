package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.vo.CustomerReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmCustomerNewMapper;
import com.stone.crm.domain.St02CrmCustomerNew;
import com.stone.crm.service.ISt02CrmCustomerNewService;

/**
 * 待分配客户Service业务层处理
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
@Service
public class St02CrmCustomerNewServiceImpl implements ISt02CrmCustomerNewService
{
    @Autowired
    private St02CrmCustomerNewMapper st02CrmCustomerNewMapper;

    /**
     * 查询待分配客户
     *
     * @param id 待分配客户主键
     * @return 待分配客户
     */
    @Override
    public St02CrmCustomerNew selectSt02CrmCustomerNewById(Long id)
    {
        return st02CrmCustomerNewMapper.selectSt02CrmCustomerNewById(id);
    }


    public List<St02CrmCustomerNew> selectSt02CrmCustomerNewByIds(Long[] ids)
    {
        return st02CrmCustomerNewMapper.selectSt02CrmCustomerNewByIds(ids);
    }
    /**
     * 查询待分配客户列表
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 待分配客户
     */
    @Override
    public List<St02CrmCustomerNew> selectSt02CrmCustomerNewList(St02CrmCustomerNew st02CrmCustomerNew)
    {
        return st02CrmCustomerNewMapper.selectSt02CrmCustomerNewList(st02CrmCustomerNew);
    }

    /**
     * 查询客户列表
     *
     * @param customerReqVo 客户
     * @return 客户集合
     */
    public List<St02CrmCustomerNew> selectCrmCustomerNewList(CustomerReqVo customerReqVo) {
        return st02CrmCustomerNewMapper.selectCrmCustomerNewList(customerReqVo);
    }

    /**
     * 新增待分配客户
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 结果
     */
    @Override
    public int insertSt02CrmCustomerNew(St02CrmCustomerNew st02CrmCustomerNew)
    {
        st02CrmCustomerNew.setCreateTime(DateUtils.getNowDate());
        return st02CrmCustomerNewMapper.insertSt02CrmCustomerNew(st02CrmCustomerNew);
    }

    /**
     * 修改待分配客户
     *
     * @param st02CrmCustomerNew 待分配客户
     * @return 结果
     */
    @Override
    public int updateSt02CrmCustomerNew(St02CrmCustomerNew st02CrmCustomerNew)
    {
        st02CrmCustomerNew.setUpdateTime(DateUtils.getNowDate());
        return st02CrmCustomerNewMapper.updateSt02CrmCustomerNew(st02CrmCustomerNew);
    }

    /**
     * 批量删除待分配客户
     *
     * @param ids 需要删除的待分配客户主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerNewByIds(Long[] ids)
    {
        return st02CrmCustomerNewMapper.deleteSt02CrmCustomerNewByIds(ids);
    }

    /**
     * 删除待分配客户信息
     *
     * @param id 待分配客户主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerNewById(Long id)
    {
        return st02CrmCustomerNewMapper.deleteSt02CrmCustomerNewById(id);
    }
}
