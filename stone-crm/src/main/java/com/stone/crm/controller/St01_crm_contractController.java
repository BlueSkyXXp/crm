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
import com.stone.crm.domain.St01_crm_contract;
import com.stone.crm.service.Ist01_crm_contractService;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.common.core.page.TableDataInfo;

/**
 * 合同Controller
 * 
 * @author stone
 * @date 2024-04-25
 */
@RestController
@RequestMapping("/crm/contract")
public class St01_crm_contractController extends BaseController
{
    @Autowired
    private Ist01_crm_contractService st01_crm_contractService;

    /**
     * 查询合同列表
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(St01_crm_contract st01_crm_contract)
    {
        startPage();
        List<St01_crm_contract> list = st01_crm_contractService.selectst01_crm_contractList(st01_crm_contract);
        return getDataTable(list);
    }

    /**
     * 导出合同列表
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St01_crm_contract st01_crm_contract)
    {
        List<St01_crm_contract> list = st01_crm_contractService.selectst01_crm_contractList(st01_crm_contract);
        ExcelUtil<St01_crm_contract> util = new ExcelUtil<St01_crm_contract>(St01_crm_contract.class);
        util.exportExcel(response, list, "合同数据");
    }

    /**
     * 获取合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:query')")
    @GetMapping(value = "/{contractId}")
    public AjaxResult getInfo(@PathVariable("contractId") String contractId)
    {
        return success(st01_crm_contractService.selectst01_crm_contractByContractId(contractId));
    }

    /**
     * 新增合同
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St01_crm_contract st01_crm_contract)
    {
        return toAjax(st01_crm_contractService.insertst01_crm_contract(st01_crm_contract));
    }

    /**
     * 修改合同
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St01_crm_contract st01_crm_contract)
    {
        return toAjax(st01_crm_contractService.updatest01_crm_contract(st01_crm_contract));
    }

    /**
     * 删除合同
     */
    @PreAuthorize("@ss.hasPermi('crm:contract:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{contractIds}")
    public AjaxResult remove(@PathVariable String[] contractIds)
    {
        return toAjax(st01_crm_contractService.deletest01_crm_contractByContractIds(contractIds));
    }
}
