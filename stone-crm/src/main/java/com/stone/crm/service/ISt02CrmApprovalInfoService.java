package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02CrmApprovalInfo;
import com.stone.crm.domain.St02CrmApprovalResult;
import com.stone.crm.domain.vo.ApprovalInfoReqVo;
import com.stone.crm.domain.vo.ApprovalReqVo;

/**
 * 审件Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
public interface ISt02CrmApprovalInfoService 
{
    /**
     * 查询审件
     * 
     * @param id 审件主键
     * @return 审件
     */
    public St02CrmApprovalInfo selectSt02CrmApprovalInfoById(Long id);

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonFlowList(ApprovalReqVo approvalReqVo);

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptFlowList(ApprovalReqVo approvalReqVo);

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonVisitList(ApprovalReqVo approvalReqVo);

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptVisitList(ApprovalReqVo approvalReqVo);

    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectPersonResultList(ApprovalReqVo approvalReqVo);



    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptResultList(ApprovalReqVo approvalReqVo);



    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectDeptParentResultList(ApprovalReqVo approvalReqVo);


    /**
     * 查询业绩排行
     *
     * @param approvalReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalResult> selectResultList(ApprovalReqVo approvalReqVo);


    /**
     * 查询审件列表
     * 
     * @param approvalInfoReqVo 审件
     * @return 审件集合
     */
    public List<St02CrmApprovalInfo> selectSt02CrmApprovalInfoList(ApprovalInfoReqVo approvalInfoReqVo);

    /**
     * 新增审件
     * 
     * @param st02CrmApprovalInfo 审件
     * @return 结果
     */
    public int insertSt02CrmApprovalInfo(St02CrmApprovalInfo st02CrmApprovalInfo);

    /**
     * 修改审件
     * 
     * @param st02CrmApprovalInfo 审件
     * @return 结果
     */
    public int updateSt02CrmApprovalInfo(St02CrmApprovalInfo st02CrmApprovalInfo);

    /**
     * 批量删除审件
     * 
     * @param ids 需要删除的审件主键集合
     * @return 结果
     */
    public int deleteSt02CrmApprovalInfoByIds(Long[] ids);

    /**
     * 删除审件信息
     * 
     * @param id 审件主键
     * @return 结果
     */
    public int deleteSt02CrmApprovalInfoById(Long id);
}
