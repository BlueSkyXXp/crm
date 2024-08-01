package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.vo.FlowInfoReqVo;
import com.stone.crm.domain.vo.FlowInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmCustomerFlowInfoMapper;
import com.stone.crm.domain.St02CrmCustomerFlowInfo;
import com.stone.crm.service.ISt02CrmCustomerFlowInfoService;

/**
 * 客户流转记录Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Service
public class St02CrmCustomerFlowInfoServiceImpl implements ISt02CrmCustomerFlowInfoService 
{
    @Autowired
    private St02CrmCustomerFlowInfoMapper st02CrmCustomerFlowInfoMapper;

    /**
     * 查询客户流转记录
     * 
     * @param id 客户流转记录主键
     * @return 客户流转记录
     */
    @Override
    public St02CrmCustomerFlowInfo selectSt02CrmCustomerFlowInfoById(Long id)
    {
        return st02CrmCustomerFlowInfoMapper.selectSt02CrmCustomerFlowInfoById(id);
    }

    /**
     * 查询客户流转记录列表
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 客户流转记录
     */
    @Override
    public List<St02CrmCustomerFlowInfo> selectSt02CrmCustomerFlowInfoList(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        return st02CrmCustomerFlowInfoMapper.selectSt02CrmCustomerFlowInfoList(st02CrmCustomerFlowInfo);
    }

    /**
     * 查询客户流转记录列表
     *
     * @param flowInfoReqVo 客户流转记录
     * @return 客户流转记录集合
     */
    public List<FlowInfoResult> selectCustomerFlowInfoList(FlowInfoReqVo flowInfoReqVo ) {
        return st02CrmCustomerFlowInfoMapper.selectCustomerFlowInfoList(flowInfoReqVo);
    }

    /**
     * 新增客户流转记录
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 结果
     */
    @Override
    public int insertSt02CrmCustomerFlowInfo(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        st02CrmCustomerFlowInfo.setCreateTime(DateUtils.getNowDate());
        return st02CrmCustomerFlowInfoMapper.insertSt02CrmCustomerFlowInfo(st02CrmCustomerFlowInfo);
    }

    /**
     * 修改客户流转记录
     * 
     * @param st02CrmCustomerFlowInfo 客户流转记录
     * @return 结果
     */
    @Override
    public int updateSt02CrmCustomerFlowInfo(St02CrmCustomerFlowInfo st02CrmCustomerFlowInfo)
    {
        return st02CrmCustomerFlowInfoMapper.updateSt02CrmCustomerFlowInfo(st02CrmCustomerFlowInfo);
    }

    /**
     * 批量删除客户流转记录
     * 
     * @param ids 需要删除的客户流转记录主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerFlowInfoByIds(Long[] ids)
    {
        return st02CrmCustomerFlowInfoMapper.deleteSt02CrmCustomerFlowInfoByIds(ids);
    }

    /**
     * 删除客户流转记录信息
     * 
     * @param id 客户流转记录主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerFlowInfoById(Long id)
    {
        return st02CrmCustomerFlowInfoMapper.deleteSt02CrmCustomerFlowInfoById(id);
    }
}
