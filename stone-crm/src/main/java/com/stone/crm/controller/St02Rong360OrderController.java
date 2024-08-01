package com.stone.crm.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.stone.crm.domain.St02Rong360Order;
import com.stone.crm.service.ISt02Rong360OrderService;
import com.stone.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.stone.common.core.page.TableDataInfo;

/**
 * 融360订单数据Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@Api(value = "融360订单数据", tags = {"融360订单数据"})
@RestController
@RequestMapping("/crm/order")
public class St02Rong360OrderController extends BaseController
{
    @Autowired
    private ISt02Rong360OrderService st02Rong360OrderService;

    /**
     * 查询融360订单数据列表
     */
    @ApiOperation("查询融360订单数据列表")
    @PreAuthorize("@ss.hasPermi('crm:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(St02Rong360Order st02Rong360Order)
    {
        startPage();
        List<St02Rong360Order> list = st02Rong360OrderService.selectSt02Rong360OrderList(st02Rong360Order);
        return getDataTable(list);
    }

    /**
     * 导出融360订单数据列表
     */
    @ApiOperation("导出融360订单数据列表")
    @PreAuthorize("@ss.hasPermi('crm:order:export')")
    @Log(title = "融360订单数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02Rong360Order st02Rong360Order)
    {
        List<St02Rong360Order> list = st02Rong360OrderService.selectSt02Rong360OrderList(st02Rong360Order);
        ExcelUtil<St02Rong360Order> util = new ExcelUtil<St02Rong360Order>(St02Rong360Order.class);
        util.exportExcel(response, list, "融360订单数据数据");
    }

    /**
     * 获取融360订单数据详细信息
     */
    @ApiOperation("获取融360订单数据详细信息")
    @PreAuthorize("@ss.hasPermi('crm:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02Rong360OrderService.selectSt02Rong360OrderById(id));
    }

    /**
     * 新增融360订单数据
     */
    @ApiOperation("新增融360订单数据")
    @PreAuthorize("@ss.hasPermi('crm:order:add')")
    @Log(title = "融360订单数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02Rong360Order st02Rong360Order)
    {
        return toAjax(st02Rong360OrderService.insertSt02Rong360Order(st02Rong360Order));
    }

    /**
     * 修改融360订单数据
     */
    @ApiOperation("修改融360订单数据")
    @PreAuthorize("@ss.hasPermi('crm:order:edit')")
    @Log(title = "融360订单数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02Rong360Order st02Rong360Order)
    {
        return toAjax(st02Rong360OrderService.updateSt02Rong360Order(st02Rong360Order));
    }

    /**
     * 删除融360订单数据
     */
    @ApiOperation("删除融360订单数据")
    @PreAuthorize("@ss.hasPermi('crm:order:remove')")
    @Log(title = "融360订单数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02Rong360OrderService.deleteSt02Rong360OrderByIds(ids));
    }
}
