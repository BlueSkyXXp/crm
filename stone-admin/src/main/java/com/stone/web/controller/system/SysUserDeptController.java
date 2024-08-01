package com.stone.web.controller.system;

import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.core.domain.entity.SysDept;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.page.TableDataInfo;
import com.stone.common.utils.SecurityUtils;
import com.stone.framework.web.domain.server.Sys;
import com.stone.system.domain.SysUserSimple;
import com.stone.system.domain.vo.DeptVo;
import com.stone.system.service.ISysDeptService;
import com.stone.system.service.ISysRoleService;
import com.stone.system.service.ISysUserService;
import com.stone.web.controller.tool.ParseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门信息
 *
 * @author ruoyi
 */
@Api(value = "用户部门", tags = {"用户部门信息"})
@RestController
@RequestMapping("/system/userdept")
public class SysUserDeptController extends BaseController {

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 根据部门ID获取部门列表
     */
    @ApiOperation("获取当前用户部门列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @PostMapping("/deptlist")
    public AjaxResult deptlist(@RequestBody DeptVo deptVo)
    {
        SysDept dept = new SysDept();
        if (deptVo.getDeptId() != null) {
            dept.setDeptId(deptVo.getDeptId());
        }
        List<SysDept> depts = deptService.selectDeptListByAncestors(dept);
        String msg = ParseUtil.list2Json(depts);
        logger.info(msg);
        return AjaxResult.success("操作成功", msg);
    }


    /**
     * 根据部门ID获取用户列表
     */
    @ApiOperation("获取当前用户列表")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/list/{deptId}")
    public TableDataInfo list(@PathVariable Long deptId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(deptId);
        int roleId = roleService.selectUserRoleByUserId(SecurityUtils.getUserId());
//        common角色无其它用户权限
        if (roleId == 2) {
            SysUserSimple sysUserSimple = new SysUserSimple();
            sysUserSimple.setUserName(SecurityUtils.getUsername());
            sysUserSimple.setUserId(SecurityUtils.getUserId());
            List<SysUserSimple> list = new ArrayList();
            list.add(sysUserSimple);
            return getDataTable(list);
        }
        List<SysUser> list = userService.selectUserListNotContainAdmin(sysUser);
        return getDataTable(list);
    }


    /**
     * 根据用户ID获取用户信息
     */
    @ApiOperation("根据用户ID获取用户信息")
    @PreAuthorize("@ss.hasPermi('crm:customer:list')")
    @GetMapping("/user/{userId}")
    public AjaxResult userInfo(@PathVariable Long userId)
    {
        SysUser sysUser = userService.selectUserById(userId);
        return AjaxResult.success(sysUser);
    }
}
