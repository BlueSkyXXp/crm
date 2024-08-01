package com.stone.crm.mapper;

import java.util.List;

import com.stone.crm.domain.St02CrmVisitDetail;
import com.stone.crm.domain.St02CrmVisitInfo;
import com.stone.crm.domain.St02CrmVisitResultInfo;
import com.stone.crm.domain.vo.VisitReqVo;

/**
 * 客户来访Mapper接口
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
public interface St02CrmVisitInfoMapper 
{
    /**
     * 查询客户来访
     * 
     * @param id 客户来访主键
     * @return 客户来访
     */
    public St02CrmVisitInfo selectSt02CrmVisitInfoById(Long id);

    /**
     * 查询客户来访列表
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 客户来访集合
     */
    public List<St02CrmVisitInfo> selectSt02CrmVisitInfoList(St02CrmVisitInfo st02CrmVisitInfo);

    public List<St02CrmVisitDetail> selectSt02CrmVisitDetailList(VisitReqVo visitReqVo);

    /**
     * 客户来访统计表
     *
     * @param visitReqVo 客户来访
     * @return 客户来访集合
     */
    public List<St02CrmVisitResultInfo> selectCrmVisitResult(VisitReqVo visitReqVo);

    /**
     * 新增客户来访
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 结果
     */
    public int insertSt02CrmVisitInfo(St02CrmVisitInfo st02CrmVisitInfo);

    /**
     * 修改客户来访
     * 
     * @param st02CrmVisitInfo 客户来访
     * @return 结果
     */
    public int updateSt02CrmVisitInfo(St02CrmVisitInfo st02CrmVisitInfo);

    /**
     * 删除客户来访
     * 
     * @param id 客户来访主键
     * @return 结果
     */
    public int deleteSt02CrmVisitInfoById(Long id);

    /**
     * 批量删除客户来访
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSt02CrmVisitInfoByIds(Long[] ids);
}
