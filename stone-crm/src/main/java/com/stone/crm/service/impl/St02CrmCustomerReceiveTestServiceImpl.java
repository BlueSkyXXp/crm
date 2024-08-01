package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmCustomerReceiveTestMapper;
import com.stone.crm.domain.St02CrmCustomerReceiveTest;
import com.stone.crm.service.ISt02CrmCustomerReceiveTestService;

/**
 * 待分配客户测试Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-07-10
 */
@Service
public class St02CrmCustomerReceiveTestServiceImpl implements ISt02CrmCustomerReceiveTestService 
{
    @Autowired
    private St02CrmCustomerReceiveTestMapper st02CrmCustomerReceiveTestMapper;

    /**
     * 查询待分配客户测试
     * 
     * @param id 待分配客户测试主键
     * @return 待分配客户测试
     */
    @Override
    public St02CrmCustomerReceiveTest selectSt02CrmCustomerReceiveTestById(Long id)
    {
        return st02CrmCustomerReceiveTestMapper.selectSt02CrmCustomerReceiveTestById(id);
    }

    /**
     * 查询待分配客户测试列表
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 待分配客户测试
     */
    @Override
    public List<St02CrmCustomerReceiveTest> selectSt02CrmCustomerReceiveTestList(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest)
    {
        return st02CrmCustomerReceiveTestMapper.selectSt02CrmCustomerReceiveTestList(st02CrmCustomerReceiveTest);
    }

    /**
     * 新增待分配客户测试
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 结果
     */
    @Override
    public int insertSt02CrmCustomerReceiveTest(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest)
    {
        st02CrmCustomerReceiveTest.setCreateTime(DateUtils.getNowDate());
        return st02CrmCustomerReceiveTestMapper.insertSt02CrmCustomerReceiveTest(st02CrmCustomerReceiveTest);
    }

    /**
     * 修改待分配客户测试
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 结果
     */
    @Override
    public int updateSt02CrmCustomerReceiveTest(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest)
    {
        st02CrmCustomerReceiveTest.setUpdateTime(DateUtils.getNowDate());
        return st02CrmCustomerReceiveTestMapper.updateSt02CrmCustomerReceiveTest(st02CrmCustomerReceiveTest);
    }

    /**
     * 批量删除待分配客户测试
     * 
     * @param ids 需要删除的待分配客户测试主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerReceiveTestByIds(Long[] ids)
    {
        return st02CrmCustomerReceiveTestMapper.deleteSt02CrmCustomerReceiveTestByIds(ids);
    }

    /**
     * 删除待分配客户测试信息
     * 
     * @param id 待分配客户测试主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerReceiveTestById(Long id)
    {
        return st02CrmCustomerReceiveTestMapper.deleteSt02CrmCustomerReceiveTestById(id);
    }
}
