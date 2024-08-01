package com.stone.crm.service.impl;

import java.util.List;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.St02CrmTeamStatic;
import com.stone.crm.domain.vo.DailyInterDataReqVo;
import com.stone.crm.domain.vo.St02CrmTeamManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.crm.mapper.St02CrmTeamManageMapper;
import com.stone.crm.domain.St02CrmTeamManage;
import com.stone.crm.service.ISt02CrmTeamManageService;

/**
 * 团队管理Service业务层处理
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Service
public class St02CrmTeamManageServiceImpl implements ISt02CrmTeamManageService 
{
    @Autowired
    private St02CrmTeamManageMapper st02CrmTeamManageMapper;

    /**
     * 查询团队管理
     * 
     * @param id 团队管理主键
     * @return 团队管理
     */
    @Override
    public St02CrmTeamManage selectSt02CrmTeamManageById(Long id)
    {
        return st02CrmTeamManageMapper.selectSt02CrmTeamManageById(id);
    }

    /**
     * 查询团队管理
     *
     * @param userId 团队管理主键
     * @return 团队管理
     */
    @Override
    public St02CrmTeamManage selectSt02CrmTeamManageByUserId(Long userId)
    {
        return st02CrmTeamManageMapper.selectSt02CrmTeamManageByUserId(userId);
    }

    /**
     * 查询团队管理列表
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 团队管理
     */
    @Override
    public List<St02CrmTeamManage> selectSt02CrmTeamManageList(St02CrmTeamManage st02CrmTeamManage)
    {
        return st02CrmTeamManageMapper.selectSt02CrmTeamManageList(st02CrmTeamManage);
    }

    @Override
    public List<St02CrmTeamManageVo> selectSt02CrmTeamManageVoList(St02CrmTeamManage st02CrmTeamManage)
    {
        return st02CrmTeamManageMapper.selectSt02CrmTeamManageVoList(st02CrmTeamManage);
    }

    /**
     * 查询团队管理列表
     *
     * @param dailyInterDataReqVo 团队管理
     * @return 团队管理集合
     */

    public List<St02CrmTeamStatic> selectSt02CrmTeamStaticList(DailyInterDataReqVo dailyInterDataReqVo) {
        return st02CrmTeamManageMapper.selectSt02CrmTeamStaticList(dailyInterDataReqVo);
    }

    /**
     * 新增团队管理
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 结果
     */
    @Override
    public int insertSt02CrmTeamManage(St02CrmTeamManage st02CrmTeamManage)
    {
        st02CrmTeamManage.setCreateTime(DateUtils.getNowDate());
        return st02CrmTeamManageMapper.insertSt02CrmTeamManage(st02CrmTeamManage);
    }

    /**
     * 修改团队管理
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 结果
     */
    @Override
    public int updateSt02CrmTeamManage(St02CrmTeamManage st02CrmTeamManage)
    {
        st02CrmTeamManage.setUpdateTime(DateUtils.getNowDate());
        return st02CrmTeamManageMapper.updateSt02CrmTeamManage(st02CrmTeamManage);
    }


    public int updateSt02CrmTeamManageByUserId(St02CrmTeamManage st02CrmTeamManage)
    {
        st02CrmTeamManage.setUpdateTime(DateUtils.getNowDate());
        return st02CrmTeamManageMapper.updateSt02CrmTeamManageByUserId(st02CrmTeamManage);
    }

    /**
     * 批量删除团队管理
     * 
     * @param ids 需要删除的团队管理主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmTeamManageByIds(Long[] ids)
    {
        return st02CrmTeamManageMapper.deleteSt02CrmTeamManageByIds(ids);
    }

    /**
     * 删除团队管理信息
     * 
     * @param id 团队管理主键
     * @return 结果
     */
    @Override
    public int deleteSt02CrmTeamManageById(Long id)
    {
        return st02CrmTeamManageMapper.deleteSt02CrmTeamManageById(id);
    }
}
