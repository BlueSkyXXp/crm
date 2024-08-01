package com.stone.crm.service;

import java.util.List;
import com.stone.crm.domain.St02CrmTeamManage;
import com.stone.crm.domain.St02CrmTeamStatic;
import com.stone.crm.domain.vo.DailyInterDataReqVo;
import com.stone.crm.domain.vo.St02CrmTeamManageVo;

/**
 * 团队管理Service接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
public interface ISt02CrmTeamManageService 
{
    /**
     * 查询团队管理
     * 
     * @param id 团队管理主键
     * @return 团队管理
     */
    public St02CrmTeamManage selectSt02CrmTeamManageById(Long id);

    /**
     * 查询团队管理
     *
     * @param userId 团队管理主键
     * @return 团队管理
     */
    public St02CrmTeamManage selectSt02CrmTeamManageByUserId(Long userId);

    /**
     * 查询团队管理列表
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 团队管理集合
     */
    public List<St02CrmTeamManage> selectSt02CrmTeamManageList(St02CrmTeamManage st02CrmTeamManage);

    public List<St02CrmTeamManageVo> selectSt02CrmTeamManageVoList(St02CrmTeamManage st02CrmTeamManage);

    /**
     * 查询团队管理列表
     *
     * @param dailyInterDataReqVo 团队管理
     * @return 团队管理集合
     */

    public List<St02CrmTeamStatic> selectSt02CrmTeamStaticList(DailyInterDataReqVo dailyInterDataReqVo);

    /**
     * 新增团队管理
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 结果
     */
    public int insertSt02CrmTeamManage(St02CrmTeamManage st02CrmTeamManage);

    /**
     * 修改团队管理
     * 
     * @param st02CrmTeamManage 团队管理
     * @return 结果
     */
    public int updateSt02CrmTeamManage(St02CrmTeamManage st02CrmTeamManage);

    public int updateSt02CrmTeamManageByUserId(St02CrmTeamManage st02CrmTeamManage);

    /**
     * 批量删除团队管理
     * 
     * @param ids 需要删除的团队管理主键集合
     * @return 结果
     */
    public int deleteSt02CrmTeamManageByIds(Long[] ids);

    /**
     * 删除团队管理信息
     * 
     * @param id 团队管理主键
     * @return 结果
     */
    public int deleteSt02CrmTeamManageById(Long id);
}
