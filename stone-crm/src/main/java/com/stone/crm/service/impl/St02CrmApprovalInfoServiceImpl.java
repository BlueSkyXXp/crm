package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.St02CrmApprovalResult;
import com.stone.crm.domain.vo.ApprovalInfoReqVo;
import com.stone.crm.domain.vo.ApprovalReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmApprovalInfoMapper;
import com.stone.crm.domain.St02CrmApprovalInfo;
import com.stone.crm.service.ISt02CrmApprovalInfoService;

/**
 * 审件Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Service
public class St02CrmApprovalInfoServiceImpl implements ISt02CrmApprovalInfoService 
{
    @Autowired
    private St02CrmApprovalInfoMapper st02CrmApprovalInfoMapper;

    /**
     * 查询审件
     * 
     * @param id 审件主键
     * @return 审件
     */
    @Override
    public St02CrmApprovalInfo selectSt02CrmApprovalInfoById(Long id)
    {
        return st02CrmApprovalInfoMapper.selectSt02CrmApprovalInfoById(id);
    }

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonFlowList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectPersonFlowList(approvalReqVo);
    }

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptFlowList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectDeptFlowList(approvalReqVo);
    }

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonVisitList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectPersonVisitList(approvalReqVo);
    }

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptVisitList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectDeptVisitList(approvalReqVo);
    }

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonResultList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectPersonResultList(approvalReqVo);
    }



    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptResultList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectDeptResultList(approvalReqVo);
    }



    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptParentResultList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectDeptParentResultList(approvalReqVo);
    }


    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectResultList(ApprovalReqVo approvalReqVo) {
        return st02CrmApprovalInfoMapper.selectResultList(approvalReqVo);
    }



    /**
     * 查询审件列表
     * 
     * @param approvalInfoReqVo 审件
     * @return 审件
     */
    @Override
    public List<St02CrmApprovalInfo> selectSt02CrmApprovalInfoList(ApprovalInfoReqVo approvalInfoReqVo)
    {
        return st02CrmApprovalInfoMapper.selectSt02CrmApprovalInfoList(approvalInfoReqVo);
    }

    /**
     * 新增审件
     * 
     * @param st02CrmApprovalInfo 审件
     * @return 结果
     */
    @Override
    public int insertSt02CrmApprovalInfo(St02CrmApprovalInfo st02CrmApprovalInfo)
    {
        st02CrmApprovalInfo.setCreateTime(DateUtils.getNowDate());
        return st02CrmApprovalInfoMapper.insertSt02CrmApprovalInfo(st02CrmApprovalInfo);
    }

    /**
     * 修改审件
     * 
     * @param st02CrmApprovalInfo 审件
     * @return 结果
     */
    @Override
    public int updateSt02CrmApprovalInfo(St02CrmApprovalInfo st02CrmApprovalInfo)
    {
        st02CrmApprovalInfo.setUpdateTime(DateUtils.getNowDate());
        return st02CrmApprovalInfoMapper.updateSt02CrmApprovalInfo(st02CrmApprovalInfo);
    }

    /**
     * 批量删除审件
     * 
     * @param ids 需要删除的审件主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmApprovalInfoByIds(Long[] ids)
    {
        return st02CrmApprovalInfoMapper.deleteSt02CrmApprovalInfoByIds(ids);
    }

    /**
     * 删除审件信息
     * 
     * @param id 审件主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmApprovalInfoById(Long id)
    {
        return st02CrmApprovalInfoMapper.deleteSt02CrmApprovalInfoById(id);
    }
}
