package com.stone.crm.controller;

import com.stone.common.annotation.Log;
import com.stone.common.constant.CacheConstants;
import com.stone.common.core.controller.BaseController;
import com.stone.common.core.domain.AjaxResult;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.page.TableDataInfo;
import com.stone.common.core.redis.RedisCache;
import com.stone.common.enums.BusinessType;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.SecurityUtils;
import com.stone.common.utils.poi.ExcelUtil;
import com.stone.crm.domain.St02CrmTeamManage;
import com.stone.crm.domain.St02CrmTeamStatic;
import com.stone.crm.domain.vo.*;
import com.stone.crm.service.ISt02CrmCustomerFlowInfoService;
import com.stone.crm.service.ISt02CrmTeamManageService;
import com.stone.system.service.ISysDeptService;
import com.stone.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 团队管理Controller
 * 
 * @author xiongxiaopeng
 * @date 2024-06-25
 */
@Api(value = "团队管理", tags = {"团队管理"})
@RestController
@RequestMapping("/crm/team_manage")
public class St02CrmTeamManageController extends BaseController
{
    @Autowired
    private ISt02CrmTeamManageService st02CrmTeamManageService;

    @Autowired
    private ISt02CrmCustomerFlowInfoService flowInfoService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysDeptService deptService;


    /**
     * 日内部数据统计
     */
    @ApiOperation("日内部数据统计")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:list')")
    @PostMapping("/statistics")
    public TableDataInfo statistics(@RequestBody DailyInterDataVo dailyInterDataVo)
    {
        DailyInterDataReqVo dailyInterDataReqVo = new DailyInterDataReqVo(dailyInterDataVo);
        startPage();
        List<St02CrmTeamStatic> list = st02CrmTeamManageService.selectSt02CrmTeamStaticList(dailyInterDataReqVo);
        FlowInfoReqVo flowInfoReqVo = new FlowInfoReqVo();
        flowInfoReqVo.setStartTime(dailyInterDataReqVo.getStartTime());
        flowInfoReqVo.setEndTime(dailyInterDataReqVo.getEndTime());
        flowInfoReqVo.setSourcePlat("API");
        List<FlowInfoResult> followInfoList = flowInfoService.selectCustomerFlowInfoList(flowInfoReqVo);
        Map<Object, Object> datas = new HashMap<>();
        for (FlowInfoResult result : followInfoList) {
            datas.put(result.getTargetPlat(), result.getCount());
        }
        for (St02CrmTeamStatic st02CrmTeamStatic : list) {
            Integer cnt = (Integer) datas.getOrDefault(st02CrmTeamStatic.getUserName(), 0);
            st02CrmTeamStatic.setCnt(cnt.longValue());
            String newDeptName = "";
            // 修改部门名称
            if (st02CrmTeamStatic.getDeptId() != null) {
                String ancestors = deptService.selectDeptById(st02CrmTeamStatic.getDeptId()).getAncestors();

                String[] deptIds = ancestors.split(",");

                for (int i = 1; i < deptIds.length; i++) {
                    String deptId = deptIds[i];
                    String deptName = deptService.selectDeptById(Long.valueOf(deptId)).getDeptName();
                    if (deptName.length() > 0) {
                        newDeptName += deptName + "-";
                    }
                }
                newDeptName += st02CrmTeamStatic.getDeptName();
                st02CrmTeamStatic.setDeptName(newDeptName);
            }
        }
        return getDataTable(list);
    }


    /**
     * 查询团队管理列表
     */
    @ApiOperation("查询团队管理列表")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:list')")
    @GetMapping("/list")
    public TableDataInfo list(St02CrmTeamManage st02CrmTeamManage)
    {
        startPage();

        List<St02CrmTeamManageVo> st02CrmTeamManageVoList = st02CrmTeamManageService.selectSt02CrmTeamManageVoList(st02CrmTeamManage);
        for (St02CrmTeamManageVo st02CrmTeamManageVo : st02CrmTeamManageVoList) {
            Integer haveAssignDoneNum = redisCache.getCacheObject(CacheConstants.DAILY_HAVE_ASSIGN_DONE_NUM + st02CrmTeamManageVo.getUserId() + "_" + DateUtils.getDate(DateUtils.YYYYMMDD));
            if (haveAssignDoneNum != null) {
                st02CrmTeamManageVo.setReceiveDataNum(Long.valueOf(haveAssignDoneNum));
            } else {
                st02CrmTeamManageVo.setReceiveDataNum(0L);
            }

            String newDeptName = "";
            // 修改部门名称
            if (st02CrmTeamManageVo.getDeptId() != null) {
                String ancestors = deptService.selectDeptById(st02CrmTeamManageVo.getDeptId()).getAncestors();

                String[] deptIds = ancestors.split(",");

                for (int i = 1; i < deptIds.length; i++) {
                    String deptId = deptIds[i];
                    String deptName = deptService.selectDeptById(Long.valueOf(deptId)).getDeptName();
                    if (deptName.length() > 0) {
                        newDeptName += deptName + "-";
                    }
                }
                newDeptName += st02CrmTeamManageVo.getDeptName();
                st02CrmTeamManageVo.setDeptName(newDeptName);
            }


        }

        return getDataTable(st02CrmTeamManageVoList);
    }


    /**
     * 一键开启接收或者禁止接收数据
     */
    @ApiOperation("一键开启接收或者禁止接收数据")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:allow_receive')")
    @PutMapping("/allow_receive")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult AllowReceive(@RequestBody AllowReceive allowReceive)
    {

        List<Long> idList = allowReceive.getIds();

        if (idList == null || idList.isEmpty()) {
            return toAjax(0);
        }

        SysUser user = SecurityUtils.getLoginUser().getUser();

        int resultSize = 0;

        try {
            for (Long id : idList) {

                // 需更新缓存
                St02CrmTeamManage st02CrmTeamManage = st02CrmTeamManageService.selectSt02CrmTeamManageById(id);

                if (st02CrmTeamManage == null) {
                    continue;
                }

                St02CrmTeamManage updateSt02CrmTeamManage = new St02CrmTeamManage();
                updateSt02CrmTeamManage.setId(id);
                updateSt02CrmTeamManage.setOperatorId(user.getUserId());
                updateSt02CrmTeamManage.setReceiveNewData(Long.valueOf(allowReceive.getAllowReceive()));

                int ret = st02CrmTeamManageService.updateSt02CrmTeamManage(updateSt02CrmTeamManage);

                if (ret == 0) {
                    continue;
                }


                String allowAssignRedisKey = CacheConstants.ALLOW_ASSIGN_KEY + st02CrmTeamManage.getUserId();
                redisCache.deleteObject(allowAssignRedisKey);
                resultSize++;
            }
            if (allowReceive.getAllowReceive() == 1) {
                redisCache.deleteObject(CacheConstants.ALLOW_SYSTEM_ASSIGN_NEW_CUSTOMER_SWITCH_KEY);
            }
        } catch (Exception e) {
            logger.error("一键开启接收或者禁止接收数据失败", e);
        }


        return toAjax(resultSize);
    }


    /**
     * 导出团队管理列表
     */
    @ApiOperation("导出团队管理列表")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:export')")
    @Log(title = "团队管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmTeamManage st02CrmTeamManage)
    {
        List<St02CrmTeamManage> list = st02CrmTeamManageService.selectSt02CrmTeamManageList(st02CrmTeamManage);
        ExcelUtil<St02CrmTeamManage> util = new ExcelUtil<St02CrmTeamManage>(St02CrmTeamManage.class);
        util.exportExcel(response, list, "团队管理数据");
    }

    /**
     * 获取团队管理详细信息
     */
    @ApiOperation("获取团队管理详细信息")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02CrmTeamManageService.selectSt02CrmTeamManageById(id));
    }

    /**
     * 新增团队管理
     */
    @ApiOperation("新增团队管理")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:add')")
    @Log(title = "团队管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmTeamManage st02CrmTeamManage)
    {
        return toAjax(st02CrmTeamManageService.insertSt02CrmTeamManage(st02CrmTeamManage));
    }

    /**
     * 修改团队管理
     */
    @ApiOperation("修改团队管理")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:edit')")
    @Log(title = "团队管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmTeamManage st02CrmTeamManage)
    {

        String userId = st02CrmTeamManage.getUserId();

        St02CrmTeamManage teamManage = st02CrmTeamManageService.selectSt02CrmTeamManageById(st02CrmTeamManage.getId());

        if (teamManage == null || teamManage.getPlanDataNum() == null) {
            return AjaxResult.error("该数据不存在或者异常");
        }

        st02CrmTeamManage.setOperatorId(SecurityUtils.getLoginUser().getUser().getUserId());

        String allowAssignRedisKey = CacheConstants.ALLOW_ASSIGN_KEY + userId;

        Integer allowAssign = redisCache.getCacheObject(allowAssignRedisKey);

        if (allowAssign != null) {
            // 需更新缓存
            if (!Objects.equals(st02CrmTeamManage.getReceiveNewData(), teamManage.getReceiveNewData()) && st02CrmTeamManage.getReceiveNewData() != null) {

                redisCache.setCacheObject(allowAssignRedisKey, Math.toIntExact(st02CrmTeamManage.getReceiveNewData()));
            }

        } else {
            if (st02CrmTeamManage.getReceiveNewData() != null) {
                // 需更新缓存

                redisCache.setCacheObject(allowAssignRedisKey, Math.toIntExact(st02CrmTeamManage.getReceiveNewData()));
            } else {


                redisCache.setCacheObject(allowAssignRedisKey, Math.toIntExact(teamManage.getReceiveNewData()));
            }

            redisCache.expire(allowAssignRedisKey, 24, TimeUnit.HOURS);
        }

        String dailyCanAssignMaxNumRedisKey = CacheConstants.DAILY_CAN_ASSIGN_MAX_NUM + userId;


        Integer dailyCanAssignMaxNum = redisCache.getCacheObject(dailyCanAssignMaxNumRedisKey);

        if (dailyCanAssignMaxNum != null) {
            // 需更新缓存

            if (!Objects.equals(st02CrmTeamManage.getPlanDataNum(), teamManage.getPlanDataNum())) {
                //更新缓存
                redisCache.setCacheObject(dailyCanAssignMaxNumRedisKey, Math.toIntExact(st02CrmTeamManage.getPlanDataNum()), 1, TimeUnit.DAYS);
            }

        } else {

            if (st02CrmTeamManage.getPlanDataNum() != null) {
                // 需更新缓存

                redisCache.setCacheObject(dailyCanAssignMaxNumRedisKey, Math.toIntExact(st02CrmTeamManage.getPlanDataNum()));
            } else {

                // 需更新缓存

                redisCache.setCacheObject(dailyCanAssignMaxNumRedisKey, Math.toIntExact(teamManage.getPlanDataNum()));
            }
            redisCache.expire(dailyCanAssignMaxNumRedisKey, 1, TimeUnit.DAYS);
        }

        redisCache.deleteObject(CacheConstants.ALLOW_SYSTEM_ASSIGN_NEW_CUSTOMER_SWITCH_KEY);

        return toAjax(st02CrmTeamManageService.updateSt02CrmTeamManage(st02CrmTeamManage));
    }

    /**
     * 删除团队管理
     */
    @ApiOperation("删除团队管理")
    @PreAuthorize("@ss.hasPermi('crm:team_manage:remove')")
    @Log(title = "团队管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02CrmTeamManageService.deleteSt02CrmTeamManageByIds(ids));
    }
}
