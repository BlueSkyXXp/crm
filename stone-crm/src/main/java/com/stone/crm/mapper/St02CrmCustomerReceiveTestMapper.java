package com.stone.crm.mapper;

import java.util.List;
import com.stone.crm.domain.St02CrmCustomerReceiveTest;

/**
 * 待分配客户测试Mapper接口
 * 
 * @author xiongxiaopeng
 * @date 2024-07-10
 */
public interface St02CrmCustomerReceiveTestMapper 
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
     * 删除待分配客户测试
     * 
     * @param id 待分配客户测试主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerReceiveTestById(Long id);

    /**
     * 批量删除待分配客户测试
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerReceiveTestByIds(Long[] ids);
}
