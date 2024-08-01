package com.stone.web.controller.system;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.stone.common.utils.equator.EquatorFieldInfo;
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
import com.stone.system.domain.SysFormLog;
import com.stone.system.service.ISysFormLogService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 单变更日志记录Controller
 *
 * @author ruoyi
 * @date 2024-06-07
 */
@RestController
@RequestMapping("/system/formLog")
public class SysFormLogController extends BaseController
{
    @Autowired
    private ISysFormLogService sysFormLogService;

    /**
     * 查询单变更日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:formLog:list')")
    @GetMapping("/list")
    public AjaxResult list(SysFormLog sysFormLog)
    {
        List<SysFormLog> list = null;
        try {
            list = sysFormLogService.selectSysFormLogList(sysFormLog);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return success(list);
    }

    /**
     * 获取单变更日志记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:formLog:query')")
    @GetMapping(value = "/{customerId}")
    public AjaxResult getInfo(@PathVariable("customerId") String customerId)
    {
        return success(sysFormLogService.selectSysFormLogByCustomerId(customerId));
    }

    /**
     * 新增单变更日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:formLog:add')")
    @Log(title = "单变更日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFormLog sysFormLog)
    {
        return toAjax(sysFormLogService.insertSysFormLog(sysFormLog));
    }

    /**
     * 修改单变更日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:formLog:edit')")
    @Log(title = "单变更日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFormLog sysFormLog)
    {
        return toAjax(sysFormLogService.updateSysFormLog(sysFormLog));
    }

    /**
     * 删除单变更日志记录
     */
    @PreAuthorize("@ss.hasPermi('system:formLog:remove')")
    @Log(title = "单变更日志记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{customerIds}")
    public AjaxResult remove(@PathVariable String[] customerIds)
    {
        return toAjax(sysFormLogService.deleteSysFormLogByCustomerIds(customerIds));
    }
}
