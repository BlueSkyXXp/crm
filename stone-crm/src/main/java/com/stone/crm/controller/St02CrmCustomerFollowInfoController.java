package com.stone.crm.controller;

import java.util.*;
import javax.servlet.http.HttpServletResponse;

import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.utils.DateUtil;
import com.stone.common.utils.SecurityUtils;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.vo.CommentsVo;
import com.stone.crm.domain.vo.IntentRemarkVo;
import com.stone.crm.domain.vo.RemarkVo;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.system.service.ISysUserService;
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
import com.stone.crm.domain.St02CrmCustomerFollowInfo;
import com.stone.crm.service.ISt02CrmCustomerFollowInfoService;
import com.stone.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.stone.common.core.page.TableDataInfo;

/**
 * 客户跟踪Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-27
 */
@Api(value = "客户跟踪", tags = {"客户跟踪"})
@RestController
@RequestMapping("/crm/follow")
public class St02CrmCustomerFollowInfoController extends BaseController
{
    @Autowired
    private ISt02CrmCustomerFollowInfoService followInfoService;

    @Autowired
    private ISt02CrmCustomerService customerService;

    @Autowired
    private ISysUserService userService;

    /**
     * 查詢客户跟踪列表
     */
    @ApiOperation("查询客户跟踪记录列表")
    @PreAuthorize("@ss.hasPermi('crm:follow:list')")
    @GetMapping("/detail/{id}")
    public AjaxResult detail(@PathVariable Long id)
    {
        startPage();
        St02CrmCustomerFollowInfo followInfo = new St02CrmCustomerFollowInfo();
        followInfo.setCustomerId(id);
        List<St02CrmCustomerFollowInfo> list = followInfoService.selectSt02CrmCustomerFollowInfoList(followInfo);
        String userName = "";
        St02CrmCustomer customer = customerService.selectSt02CrmCustomerById(id);
        if (customer != null && customer.getUserId() != null) {
            SysUser sysUser = userService.selectUserById(customer.getUserId());
            userName = sysUser.getUserName();
        }
        List<RemarkVo> remarks = new ArrayList<>();
        List<CommentsVo> comments = new ArrayList<>();
        List<IntentRemarkVo> intentRemarks = new ArrayList<>();
        for (St02CrmCustomerFollowInfo followInfo1 : list) {
            RemarkVo remarkVo = RemarkVo.builder().remark(followInfo1.getRemark()).operator(followInfo1.getOperator()).createTime(DateUtil.timestampToDateString(followInfo1.getCreateTime().getTime())).build();
            CommentsVo commentsVo = CommentsVo.builder().comment(followInfo1.getComments()).operator(followInfo1.getOperator()).createTime(DateUtil.timestampToDateString(followInfo1.getCreateTime().getTime())).build();
            IntentRemarkVo intentRemarkVo = IntentRemarkVo.builder().intentRemark(followInfo1.getIntentPerceptionRemark()).operator(followInfo1.getOperator()).createTime(followInfo1.getCreateTime().toString()).build();
            remarks.add(remarkVo);
            comments.add(commentsVo);
            intentRemarks.add(intentRemarkVo);
        }
        Map data = new HashMap<String, Object>();
        data.put("userName", userName);
        data.put("remark", remarks);
        data.put("comment", comments);
        data.put("ntentPerceptionRemark", intentRemarks);
        return AjaxResult.success(data);
    }





    /**
     * 查询客户跟踪列表
     */
    @ApiOperation("查询客户跟踪列表")
    @PreAuthorize("@ss.hasPermi('crm:follow:list')")
    @GetMapping("/list")
    public TableDataInfo list(St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        startPage();
        List<St02CrmCustomerFollowInfo> list = followInfoService.selectSt02CrmCustomerFollowInfoList(st02CrmCustomerFollowInfo);
        return getDataTable(list);
    }

    /**
     * 导出客户跟踪列表
     */
    @ApiOperation("导出客户跟踪列表")
    @PreAuthorize("@ss.hasPermi('crm:follow:export')")
    @Log(title = "客户跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        List<St02CrmCustomerFollowInfo> list = followInfoService.selectSt02CrmCustomerFollowInfoList(st02CrmCustomerFollowInfo);
        ExcelUtil<St02CrmCustomerFollowInfo> util = new ExcelUtil<St02CrmCustomerFollowInfo>(St02CrmCustomerFollowInfo.class);
        util.exportExcel(response, list, "客户跟踪数据");
    }

    /**
     * 获取客户跟踪详细信息
     */
    @ApiOperation("获取客户跟踪详细信息")
    @PreAuthorize("@ss.hasPermi('crm:follow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(followInfoService.selectSt02CrmCustomerFollowInfoById(id));
    }

    /**
     * 新增客户跟踪
     */
    @ApiOperation("新增客户跟踪")
    @PreAuthorize("@ss.hasPermi('crm:follow:add')")
    @Log(title = "客户跟踪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        St02CrmCustomer customer = customerService.selectSt02CrmCustomerById(st02CrmCustomerFollowInfo.getCustomerId());
        St02CrmCustomer crmCustomer = new St02CrmCustomer();
        crmCustomer.setId(st02CrmCustomerFollowInfo.getCustomerId());
        if (customer.getStatus() == null || customer.getStatus() == 1) {
            crmCustomer.setStatus(2L);
        }
        crmCustomer.setLastRemarkTime(new Date());
        if (st02CrmCustomerFollowInfo.getCustomerStatus() != null) {
            if (customer.getStatus() == null || !customer.getStatus().equals(st02CrmCustomerFollowInfo.getCustomerStatus())) {
                crmCustomer.setStatus(st02CrmCustomerFollowInfo.getCustomerStatus());
            }
        }

        if (st02CrmCustomerFollowInfo.getCustomerLevel() != null) {
            if (customer.getLevel() == null ||
                    !customer.getLevel().equals(st02CrmCustomerFollowInfo.getCustomerLevel())) {
                crmCustomer.setLevel(st02CrmCustomerFollowInfo.getCustomerLevel());
            }
        }

        customerService.updateSt02CrmCustomer(crmCustomer);
        st02CrmCustomerFollowInfo.setComments(null);
        st02CrmCustomerFollowInfo.setOperator(SecurityUtils.getUsername());
        return toAjax(followInfoService.insertSt02CrmCustomerFollowInfo(st02CrmCustomerFollowInfo));
    }

    @ApiOperation("新增客户跟踪点评")
    @PreAuthorize("@ss.hasPermi('crm:follow_comment:add')")
    @Log(title = "客户跟踪点评", businessType = BusinessType.INSERT)
    @PostMapping("/comment/add")
    public AjaxResult followCommentAdd(@RequestBody St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {

        st02CrmCustomerFollowInfo.setRemark(null);
        st02CrmCustomerFollowInfo.setOperator(SecurityUtils.getUsername());
        return toAjax(followInfoService.insertSt02CrmCustomerFollowInfo(st02CrmCustomerFollowInfo));
    }

    /**
     * 修改客户跟踪
     */
    @ApiOperation("修改客户跟踪")
    @PreAuthorize("@ss.hasPermi('crm:follow:edit')")
    @Log(title = "客户跟踪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmCustomerFollowInfo st02CrmCustomerFollowInfo)
    {
        return toAjax(followInfoService.updateSt02CrmCustomerFollowInfo(st02CrmCustomerFollowInfo));
    }

    /**
     * 删除客户跟踪
     */
    @ApiOperation("删除客户跟踪")
    @PreAuthorize("@ss.hasPermi('crm:follow:remove')")
    @Log(title = "客户跟踪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(followInfoService.deleteSt02CrmCustomerFollowInfoByIds(ids));
    }
}
