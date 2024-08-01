package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmCustomerFollowInfoMapper;
import com.stone.crm.domain.St02CrmCustomerFollowInfo;
import com.stone.crm.service.ISt02CrmCustomerFollowInfoService;

/**
 * 客户跟踪Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@Service
public class St02CrmCustomerFollowInfoServiceImpl implements ISt02CrmCustomerFollowInfoService 
{
    @Autowired
    private St02CrmCustomerFollowInfoMapper st02CrmCustomerFollowInfoMapper;

    /**
     * 查询客户跟踪
     * 
     * @param id 客户跟踪主键
     * @return 客户跟踪
     */
    @Override
    public St02CrmCustomerFollowInfo selectSt02CrmCustomerFollowInfoById(Long id)
    {
        return st02CrmCustomerFollowInfoMapper.selectSt02CrmCustomerFollowInfoById(id);
    }

    /**
     * 查询客户跟踪列表
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 客户跟踪
     */
    @Override
    public List<St02CrmCustomerFollowInfo> selectSt02CrmCustomerFollowInfoList(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        return st02CrmCustomerFollowInfoMapper.selectSt02CrmCustomerFollowInfoList(st02CrmCustomerFollowInfo);
    }

    /**
     * 查询客户跟踪列表(限制条数)
     *
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 客户跟踪集合
     */
    public List<St02CrmCustomerFollowInfo> selectFollowInfoListLimit(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo) {
        return st02CrmCustomerFollowInfoMapper.selectFollowInfoListLimit(st02CrmCustomerFollowInfo);
    }

    /**
     * 新增客户跟踪
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 结果
     */
    @Override
    public int insertSt02CrmCustomerFollowInfo(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        st02CrmCustomerFollowInfo.setCreateTime(DateUtils.getNowDate());
        return st02CrmCustomerFollowInfoMapper.insertSt02CrmCustomerFollowInfo(st02CrmCustomerFollowInfo);
    }

    /**
     * 修改客户跟踪
     * 
     * @param st02CrmCustomerFollowInfo 客户跟踪
     * @return 结果
     */
    @Override
    public int updateSt02CrmCustomerFollowInfo(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        st02CrmCustomerFollowInfo.setUpdateTime(DateUtils.getNowDate());
        return st02CrmCustomerFollowInfoMapper.updateSt02CrmCustomerFollowInfo(st02CrmCustomerFollowInfo);
    }

    /**
     * 批量删除客户跟踪
     * 
     * @param ids 需要删除的客户跟踪主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerFollowInfoByIds(Long[] ids)
    {
        return st02CrmCustomerFollowInfoMapper.deleteSt02CrmCustomerFollowInfoByIds(ids);
    }

    /**
     * 删除客户跟踪信息
     * 
     * @param id 客户跟踪主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmCustomerFollowInfoById(Long id)
    {
        return st02CrmCustomerFollowInfoMapper.deleteSt02CrmCustomerFollowInfoById(id);
    }
}
