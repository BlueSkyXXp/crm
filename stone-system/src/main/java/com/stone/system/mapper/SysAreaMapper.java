package com.stone.system.mapper;

import com.stone.system.domain.SysArea;

import java.util.List;

/**
 * 中国标准行政区Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-03
 */
public interface SysAreaMapper
{
    /**
     * 查询中国标准行政区
     *
     * @param id 中国标准行政区主键
     * @return 中国标准行政区
     */
    public SysArea selectSysAreaById(Long id);

    /**
     * 查询中国标准行政区列表
     *
     * @param sysArea 中国标准行政区
     * @return 中国标准行政区集合
     */
    public List<SysArea> selectSysAreaList(SysArea sysArea);

    /**
     * 新增中国标准行政区
     *
     * @param sysArea 中国标准行政区
     * @return 结果
     */
    public int insertSysArea(SysArea sysArea);

    /**
     * 修改中国标准行政区
     *
     * @param sysArea 中国标准行政区
     * @return 结果
     */
    public int updateSysArea(SysArea sysArea);

    /**
     * 删除中国标准行政区
     *
     * @param id 中国标准行政区主键
     * @return 结果
     */
    public int deleteSysAreaById(Long id);

    /**
     * 批量删除中国标准行政区
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAreaByIds(Long[] ids);
}
