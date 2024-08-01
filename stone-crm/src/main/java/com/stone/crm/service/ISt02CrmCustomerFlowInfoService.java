package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02CrmCustomerFlowInfo;
import com.stone.crm.domain.vo.FlowInfoReqVo;
import com.stone.crm.domain.vo.FlowInfoResult;

/**
 * 客户流转记录Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
public interface ISt02CrmCustomerFlowInfoService 
{
    /**
     * 查询客户流转记录
     * 
     * @param id 客户流转记录主键
     * @return 客户流转记录
     */
    public St02CrmCustomerFlowInfo selectSt02CrmCustomerFlowInfoById(Long id);

    /**
     * 查询客户流转记录列表
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 客户流转记录集合
     */
    public List<St02CrmCustomerFlowInfo> selectSt02CrmCustomerFlowInfoList(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo);

    /**
     * 查询客户流转记录列表
     *
     * @param flowInfoReqVo 客户流转记录
     * @return 客户流转记录集合
     */
    public List<FlowInfoResult> selectCustomerFlowInfoList(FlowInfoReqVo flowInfoReqVo);

    /**
     * 新增客户流转记录
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 结果
     */
    public int insertSt02CrmCustomerFlowInfo(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo);

    /**
     * 修改客户流转记录
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 结果
     */
    public int updateSt02CrmCustomerFlowInfo(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo);

    /**
     * 批量删除客户流转记录
     * 
     * @param ids 需要删除的客户流转记录主键集合
     * @return 结果
     */
    public int deleteSt02CrmCustomerFlowInfoByIds(Long[] ids);

    /**
     * 删除客户流转记录信息
     * 
     * @param id 客户流转记录主键
     * @return 结果
     */
    public int deleteSt02CrmCustomerFlowInfoById(Long id);
}
