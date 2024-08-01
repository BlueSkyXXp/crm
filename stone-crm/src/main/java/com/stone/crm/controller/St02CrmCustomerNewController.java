package com.stone.crm.controller;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;

import com.stone.common.constant.CacheConstants;
import com.stone.common.core.domain.entity.SysRole;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.redis.RedisCache;
import com.stone.common.utils.DateUtils;
import com.stone.common.utils.SecurityUtils;
import com.stone.common.utils.bean.BeanUtils;
import com.stone.crm.domain.St02CrmCustomer;
import com.stone.crm.domain.St02CrmCustomerFlowInfo;
import com.stone.crm.domain.St02CrmTeamManage;
import com.stone.crm.domain.vo.CustomerAssignVo;
import com.stone.crm.domain.vo.CustomerReqVo;
import com.stone.crm.domain.vo.CustomerVo;
import com.stone.crm.service.ISt02CrmCustomerFlowInfoService;
import com.stone.crm.service.ISt02CrmCustomerService;
import com.stone.crm.service.ISt02CrmTeamManageService;
import com.stone.crm.util.BeanUtil;
import com.stone.framework.websocket.WebSocketServer;
import com.stone.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.stone.crm.domain.St02CrmCustomerNew;
import com.stone.crm.service.ISt02CrmCustomerNewService;
import com.stone.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.stone.common.core.page.TableDataInfo;
import org.springframework.web.socket.client.WebSocketClient;

/**
 * 待分配客户Controller
 *
 * @author xiongxiaopeng
 * @date 2024-06-29
 */
@Api(value = "待分配客户", tags = {"待分配客户"})
@RestController
@RequestMapping("/crm/new")
public class St02CrmCustomerNewController extends BaseController
{
    @Autowired
    private ISt02CrmCustomerNewService st02CrmCustomerNewService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISt02CrmTeamManageService st02CrmTeamManageService;

    @Autowired
    private ISt02CrmCustomerFlowInfoService st02CrmCustomerFlowInfoService;

    /**
     * 查询待分配客户列表
     */
    @ApiOperation("查询待分配客户列表")
    @PreAuthorize("@ss.hasPermi('crm:new:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody CustomerVo customerVo)
    {
        startPage();
        CustomerReqVo customerReqVo = BeanUtil.toSt02CrmCustomer(customerVo);
        List<St02CrmCustomerNew> list = st02CrmCustomerNewService.selectCrmCustomerNewList(customerReqVo);
        return getDataTable(list);
    }

    /**
     * 导出待分配客户列表
     */
    @ApiOperation("导出待分配客户列表")
    @PreAuthorize("@ss.hasPermi('crm:new:export')")
    @Log(title = "待分配客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, St02CrmCustomerNew st02CrmCustomerNew)
    {
        List<St02CrmCustomerNew> list = st02CrmCustomerNewService.selectSt02CrmCustomerNewList(st02CrmCustomerNew);
        ExcelUtil<St02CrmCustomerNew> util = new ExcelUtil<St02CrmCustomerNew>(St02CrmCustomerNew.class);
        util.exportExcel(response, list, "待分配客户数据");
    }

    /**
     * 获取待分配客户详细信息
     */
    @ApiOperation("获取待分配客户详细信息")
    @PreAuthorize("@ss.hasPermi('crm:new:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(st02CrmCustomerNewService.selectSt02CrmCustomerNewById(id));
    }

    /**
     * 新增待分配客户
     */
    @ApiOperation("新增待分配客户")
    @PreAuthorize("@ss.hasPermi('crm:new:add')")
    @Log(title = "待分配客户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody St02CrmCustomerNew st02CrmCustomerNew)
    {
        return toAjax(st02CrmCustomerNewService.insertSt02CrmCustomerNew(st02CrmCustomerNew));
    }

    /**
     * 修改待分配客户
     */
    @ApiOperation("修改待分配客户")
    @PreAuthorize("@ss.hasPermi('crm:new:edit')")
    @Log(title = "待分配客户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody St02CrmCustomerNew st02CrmCustomerNew)
    {
        return toAjax(st02CrmCustomerNewService.updateSt02CrmCustomerNew(st02CrmCustomerNew));
    }

    /**
     * 删除待分配客户
     */
    @ApiOperation("删除待分配客户")
    @PreAuthorize("@ss.hasPermi('crm:new:remove')")
    @Log(title = "待分配客户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(st02CrmCustomerNewService.deleteSt02CrmCustomerNewByIds(ids));
    }


    @ApiOperation("待分配客户的分配客户")
    @PreAuthorize("@ss.hasPermi('crm:new:assign')")
    @Log(title = "客户", businessType = BusinessType.ASSIGN)
    @PutMapping("/assign")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult assignCustomerNew2Customer(@RequestBody CustomerAssignVo customerAssignVo) {

        long targetUserId = customerAssignVo.getUserId();
        SysUser targetUser = sysUserService.selectUserById(targetUserId);
        List<Long> idArr = customerAssignVo.getCustomerIds();
        // 新增分配记录
        SysUser currentUser = SecurityUtils.getLoginUser().getUser();
        if (idArr == null || idArr.isEmpty()) {
            return toAjax(0);
        }

        String allowAssignRedisKey = CacheConstants.ALLOW_ASSIGN_KEY + targetUserId;

        Integer allowAssign = redisCache.getCacheObject(allowAssignRedisKey);

        if (allowAssign != null && allowAssign == 0) {
            return AjaxResult.error("该用户被禁止分配");
        }


        String dailyCanAssignMaxNumRedisKey = CacheConstants.DAILY_CAN_ASSIGN_MAX_NUM + targetUserId;
        Integer dailyCanAssignMaxNum = redisCache.getCacheObject(dailyCanAssignMaxNumRedisKey);

        if (dailyCanAssignMaxNum != null && dailyCanAssignMaxNum < idArr.size()) {
            return AjaxResult.error("分配新客户数量超过限制");
        }


        String dailyHaveDoneNumRedisKey = CacheConstants.DAILY_HAVE_ASSIGN_DONE_NUM + targetUserId + "_" + DateUtils.getDate(DateUtils.YYYYMMDD);
        Integer dailyHaveDoneNum = redisCache.getCacheObject(dailyHaveDoneNumRedisKey);

        if (dailyHaveDoneNum != null && dailyCanAssignMaxNum != null && dailyHaveDoneNum + idArr.size() > dailyCanAssignMaxNum) {
            return AjaxResult.error("分配新客户数量超过限制");
        }


        St02CrmTeamManage teamManage = st02CrmTeamManageService.selectSt02CrmTeamManageByUserId(new Long(targetUserId));

        if (teamManage == null) {
            return AjaxResult.error("用户不存在");
        }

        if (teamManage.getReceiveNewData() == 0) {
            return AjaxResult.error("用户不允许接收新客户");
        }

        if (idArr.size() > teamManage.getPlanDataNum()) {
            return AjaxResult.error("分配新客户数量超过限制");
        }

        CustomerReqVo queryCustomerByUserId = new CustomerReqVo();
        queryCustomerByUserId.setUserId(targetUser.getUserId().toString());

//        int hasCustomerSize = st02CrmCustomerService.selectCrmCustomerList(queryCustomerByUserId).size();
//        if (hasCustomerSize + idArr.size() > maxReceive) {
//            return AjaxResult.error("分配客户数量超过限制");
//        }

        if (allowAssign == null) {
            redisCache.setCacheObject(allowAssignRedisKey, Math.toIntExact(teamManage.getReceiveNewData()));
        }




        try {
            List<St02CrmCustomerNew> customerNews = st02CrmCustomerNewService.selectSt02CrmCustomerNewByIds(idArr.toArray(new Long[0]));
            for (St02CrmCustomerNew customerNew : customerNews) {
                St02CrmCustomer customer = new St02CrmCustomer();
                BeanUtils.copyProperties(customerNew, customer);
                customer.setId(null);

                customer.setUserId(targetUserId);
                st02CrmCustomerService.insertSt02CrmCustomer(customer);

                St02CrmCustomerFlowInfo customerFlowInfo = new St02CrmCustomerFlowInfo();
                customerFlowInfo.setCustomerId(customer.getId().toString());
                // todo 暂时定义user_id为分配的目标用户的user_id, 后续改成操作者的user_id
                customerFlowInfo.setUserId(targetUser.getUserId());
                customerFlowInfo.setUserName(currentUser.getUserName());
                customerFlowInfo.setTargetPlat(targetUser.getUserName());
                customerFlowInfo.setSourcePlat("API");
                st02CrmCustomerFlowInfoService.insertSt02CrmCustomerFlowInfo(customerFlowInfo);

            }
            st02CrmCustomerNewService.deleteSt02CrmCustomerNewByIds(idArr.toArray(new Long[0]));

            if (dailyHaveDoneNum == null) {
                redisCache.setCacheObject(dailyHaveDoneNumRedisKey, idArr.size(), 7, TimeUnit.DAYS);

            } else {
                redisCache.setCacheObject(dailyHaveDoneNumRedisKey, dailyHaveDoneNum + idArr.size());
            }

            WebSocketServer.sendInfo(currentUser.getUserName() + "给你分配了" + String.valueOf(idArr.size()) + "个新客户", targetUser.getUserId().toString());

        }catch (Exception e) {
            logger.error("分配客户失败", e);
        }

        return toAjax(idArr.size());

    }



}
