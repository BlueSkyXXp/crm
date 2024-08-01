package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02CrmCustomerReceiveTest;

/**
 * 待分配客户测试Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-07-10
 */
public interface ISt02CrmCustomerReceiveTestService 
{
    /**
     * 查询待分配客户测试
     * 
     * @param id 待分配客户测试主键
     * @return 待分配客户测试
     */
    public St02CrmCustomerReceiveTest selectSt02CrmCustomerReceiveTestById(Long id);

    /**
     * 查询待分配客户测试列表
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 待分配客户测试集合
     */
    public List<St02CrmCustomerReceiveTest> selectSt02CrmCustomerReceiveTestList(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest);

    /**
     * 新增待分配客户测试
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 结果
     */
    public int insertSt02CrmCustomerReceiveTest(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest);

    /**
     * 修改待分配客户测试
     * 
     * @param st02CrmCustomerReceiveTest 待分配客户测试
     * @return 结果
     */
    public int updateSt02CrmCustomerReceiveTest(St02CrmCustomerReceiveTest st02CrmCustomerReceiveTest);

    /**
     * 批量删除待分配客户测试
     * 
     * @param ids 需要删除的待分配客户测试主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerReceiveTestByIds(Long[] ids);

    /**
     * 删除待分配客户测试信息
     * 
     * @param id 待分配客户测试主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerReceiveTestById(Long id);
}
