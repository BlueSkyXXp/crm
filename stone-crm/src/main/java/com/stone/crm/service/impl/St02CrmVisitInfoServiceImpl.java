package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmVisitDetail;
import com.stone.crm.domain.St02CrmVisitResultInfo;
import com.stone.crm.domain.vo.VisitReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmVisitInfoMapper;
import com.stone.crm.domain.St02CrmVisitInfo;
import com.stone.crm.service.ISt02CrmVisitInfoService;

/**
 * 客户来访Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Service
public class St02CrmVisitInfoServiceImpl implements ISt02CrmVisitInfoService 
{
    @Autowired
    private St02CrmVisitInfoMapper st02CrmVisitInfoMapper;

    /**
     * 查询客户来访
     * 
     * @param id 客户来访主键
     * @return 客户来访
     */
    @Override
    public St02CrmVisitInfo selectSt02CrmVisitInfoById(Long id)
    {
        return st02CrmVisitInfoMapper.selectSt02CrmVisitInfoById(id);
    }

    /**
     * 查询客户来访列表
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 客户来访
     */
    @Override
    public List<St02CrmVisitDetail> selectSt02CrmVisitDetailList(VisitReqVo visitReqVo)
    {
        return st02CrmVisitInfoMapper.selectSt02CrmVisitDetailList(visitReqVo);
    }


    @Override
    public List<St02CrmVisitInfo> selectSt02CrmVisitInfoList(St02CrmVisitInfo st02CrmVisitInfo)
    {
        return st02CrmVisitInfoMapper.selectSt02CrmVisitInfoList(st02CrmVisitInfo);
    }
    /**
     * 客户来访统计表
     *
     * @param visitReqVo 客户来访
     * @return 客户来访集合
     */
    public List<St02CrmVisitResultInfo> selectCrmVisitResult(VisitReqVo visitReqVo) {
        return st02CrmVisitInfoMapper.selectCrmVisitResult(visitReqVo);
    }

    /**
     * 新增客户来访
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 结果
     */
    @Override
    public int insertSt02CrmVisitInfo(St02CrmVisitInfo st02CrmVisitInfo)
    {
        st02CrmVisitInfo.setCreateTime(DateUtils.getNowDate());
        return st02CrmVisitInfoMapper.insertSt02CrmVisitInfo(st02CrmVisitInfo);
    }

    /**
     * 修改客户来访
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 结果
     */
    @Override
    public int updateSt02CrmVisitInfo(St02CrmVisitInfo st02CrmVisitInfo)
    {
        return st02CrmVisitInfoMapper.updateSt02CrmVisitInfo(st02CrmVisitInfo);
    }

    /**
     * 批量删除客户来访
     * 
     * @param ids 需要删除的客户来访主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmVisitInfoByIds(Long[] ids)
    {
        return st02CrmVisitInfoMapper.deleteSt02CrmVisitInfoByIds(ids);
    }

    /**
     * 删除客户来访信息
     * 
     * @param id 客户来访主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmVisitInfoById(Long id)
    {
        return st02CrmVisitInfoMapper.deleteSt02CrmVisitInfoById(id);
    }
}
