package com.stone.web.controller.system;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.stone.system.domain.SysArea;
import com.stone.system.service.ISysAreaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stone.common.annotation.Log;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.enums.BusinessType;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 中国标准行政区Controller
 *
 * @author ruoyi
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/system/area")
public class SysAreaController extends BaseController
{
    @Autowired
    private ISysAreaService sysAreaService;

    /**
     * 查询中国标准行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:list')")
    @GetMapping("/list")
    public AjaxResult list(SysArea sysArea)
    {
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        return success(list);
    }

    /**
     * 导出中国标准行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:area:export')")
    @Log(title = "中国标准行政区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysArea sysArea)
    {
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        ExcelUtil<SysArea> util = new ExcelUtil<SysArea>(SysArea.class);
        util.exportExcel(response, list, "中国标准行政区数据");
    }

    /**
     * 获取中国标准行政区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysAreaService.selectSysAreaById(id));
    }

    /**
     * 新增中国标准行政区
     */
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "中国标准行政区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysArea sysArea)
    {
        return toAjax(sysAreaService.insertSysArea(sysArea));
    }

    /**
     * 修改中国标准行政区
     */
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "中国标准行政区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysArea sysArea)
    {
        return toAjax(sysAreaService.updateSysArea(sysArea));
    }

    /**
     * 删除中国标准行政区
     */
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "中国标准行政区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysAreaService.deleteSysAreaByIds(ids));
    }
}
