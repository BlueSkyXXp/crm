package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02CrmCustomerFollowInfo;

/**
 * 客户跟踪Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
public interface ISt02CrmCustomerFollowInfoService 
{
    /**
     * 查询客户跟踪
     * 
     * @param id 客户跟踪主键
     * @return 客户跟踪
     */
    public St02CrmCustomerFollowInfo selectSt02CrmCustomerFollowInfoById(Long id);

    /**
     * 查询客户跟踪列表
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 客户跟踪集合
     */
    public List<St02CrmCustomerFollowInfo> selectSt02CrmCustomerFollowInfoList(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo);

    /**
     * 查询客户跟踪列表(限制条数)
     *
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 客户跟踪集合
     */
    public List<St02CrmCustomerFollowInfo> selectFollowInfoListLimit(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo);

    /**
     * 新增客户跟踪
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 结果
     */
    public int insertSt02CrmCustomerFollowInfo(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo);

    /**
     * 修改客户跟踪
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 结果
     */
    public int updateSt02CrmCustomerFollowInfo(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo);

    /**
     * 批量删除客户跟踪
     * 
     * @param ids 需要删除的客户跟踪主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerFollowInfoByIds(Long[] ids);

    /**
     * 删除客户跟踪信息
     * 
     * @param id 客户跟踪主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerFollowInfoById(Long id);
}
