package com.stone.system.service.impl;

import java.util.List;

import com.stone.system.domain.SysArea;
import com.stone.system.mapper.SysAreaMapper;
import com.stone.system.service.ISysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 中国标准行政区Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-03
 */
@Service
public class SysAreaServiceImpl implements ISysAreaService
{
    @Autowired
    private SysAreaMapper sysAreaMapper;

    /**
     * 查询中国标准行政区
     *
     * @param id 中国标准行政区主键
     * @return 中国标准行政区
     */
    @Override
    public SysArea selectSysAreaById(Long id)
    {
        return sysAreaMapper.selectSysAreaById(id);
    }

    /**
     * 查询中国标准行政区列表
     *
     * @param sysArea 中国标准行政区
     * @return 中国标准行政区
     */
    @Override
    public List<SysArea> selectSysAreaList(SysArea sysArea)
    {
        return sysAreaMapper.selectSysAreaList(sysArea);
    }

    /**
     * 新增中国标准行政区
     *
     * @param sysArea 中国标准行政区
     * @return 结果
     */
    @Override
    public int insertSysArea(SysArea sysArea)
    {
        return sysAreaMapper.insertSysArea(sysArea);
    }

    /**
     * 修改中国标准行政区
     *
     * @param sysArea 中国标准行政区
     * @return 结果
     */
    @Override
    public int updateSysArea(SysArea sysArea)
    {
        return sysAreaMapper.updateSysArea(sysArea);
    }

    /**
     * 批量删除中国标准行政区
     *
     * @param ids 需要删除的中国标准行政区主键
     * @return 结果
     */
    @Override
    public int deleteSysAreaByIds(Long[] ids)
    {
        return sysAreaMapper.deleteSysAreaByIds(ids);
    }

    /**
     * 删除中国标准行政区信息
     *
     * @param id 中国标准行政区主键
     * @return 结果
     */
    @Override
    public int deleteSysAreaById(Long id)
    {
        return sysAreaMapper.deleteSysAreaById(id);
    }
}