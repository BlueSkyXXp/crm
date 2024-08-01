package com.stone.quartz.task;

import com.alibaba.fastjson.JSONObject;
import com.stone.common.constant.CacheConstants;
import com.stone.common.core.domain.entity.SysRole;
import com.stone.common.core.domain.entity.SysUser;
import com.stone.common.core.redis.RedisCache;
import com.stone.common.utils.DateUtil;
import com.stone.common.utils.DateUtils;
import com.stone.crm.domain.*;
import com.stone.crm.domain.vo.CustomerReqVo;
import com.stone.crm.service.*;


import com.stone.framework.websocket.WebSocketServer;
import com.stone.quartz.util.AbstractQuartzJob;
import com.stone.quartz.util.BdApiCommonOrderGetorderlistResponse;
import com.stone.quartz.util.Rong360Response;
import com.stone.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.stereotype.Component;
import com.stone.common.utils.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.stone.quartz.service.Rong360Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    @Autowired
    private ISt02Rong360OrderService st02Rong360OrderService;

    @Autowired
    private ISt02CrmCustomerNewService st02CrmCustomerNewService;

    @Autowired
    private ISt02CrmCustomerService st02CrmCustomerService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISt02CrmTeamManageService st02CrmTeamManageService;

    @Autowired
    private ISt02CrmCustomerFlowInfoService st02CrmCustomerFlowInfoService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WebSocketServer webSocketServer;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() throws IOException {

        System.out.println("执行无参方法");
    }

    public void rySendNotice(String message, String uid) throws IOException {
        WebSocketServer.sendInfo(message, uid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void ryRotationAssignNewCustomerToUsers() {

        // 减小数据库压力
        Integer systemAssignSwitch = redisCache.getCacheObject(CacheConstants.ALLOW_SYSTEM_ASSIGN_NEW_CUSTOMER_SWITCH_KEY);
        if (systemAssignSwitch != null && systemAssignSwitch == 0) {
            return;
        }

        SysUser userQuery = new SysUser();

        // status 0 表示没禁止登陆， 1表示禁止登陆
        // del_flag 2表示已经删除, 0表示未删除
        userQuery.setStatus("0");
        userQuery.setDelFlag("0");

        List<SysUser> userList = sysUserService.selectUserListByRyTask(userQuery);

        Map<Long, SysUser> userIdMap = new HashMap<>();

        Map<Long, Integer> haveDoneAssignNumMap = new HashMap<>();
        Map<Long, Integer> dailyCanAssignMaxNumMap = new HashMap<>();


        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 获取今天的24点（即第二天的0点）
        LocalDateTime tomorrowMidnight = now.toLocalDate().plusDays(1).atStartOfDay();

        // 计算时间差（以小时为单位）
        long hoursUntilMidnight = ChronoUnit.HOURS.between(now, tomorrowMidnight);

        // 计算时间差（以分钟为单位）
        long minutesUntilMidnight = ChronoUnit.MINUTES.between(now, tomorrowMidnight);

        // 如果分钟数超过或等于60，则向上取整小时
        long roundedHours = (minutesUntilMidnight + 59) / 60; // 向上取整的一种简单方法

        // 但由于我们已经有了小时数，只需检查分钟数是否大于0即可决定是否向上取整
        if (minutesUntilMidnight > 0) {
            hoursUntilMidnight++; // 向上取整小时
        }

        int assignUsersNum = 0;

        for (SysUser user : userList) {

            userIdMap.put(user.getUserId(), user);

            String allowAssignRedisKey = CacheConstants.ALLOW_ASSIGN_KEY + user.getUserId();

            Integer allowAssign = redisCache.getCacheObject(allowAssignRedisKey);

            if (allowAssign != null && allowAssign == 0) {
                continue;
            }


            St02CrmTeamManage team = st02CrmTeamManageService.selectSt02CrmTeamManageByUserId(user.getUserId());
            if (team == null || team.getReceiveNewData() != 1) {
                if (allowAssign == null && team != null) {
                    redisCache.setCacheObject(allowAssignRedisKey, Math.toIntExact(team.getReceiveNewData()));
                }
                continue;
            }


            String dailyCanReceiveMaxNumRedisKey = CacheConstants.DAILY_CAN_ASSIGN_MAX_NUM + user.getUserId();

            Integer dailyCanReceiveMaxNum = redisCache.getCacheObject(dailyCanReceiveMaxNumRedisKey);

            if (dailyCanReceiveMaxNum == null && team.getPlanDataNum() != null){
                dailyCanReceiveMaxNum = Math.toIntExact(team.getPlanDataNum());

                redisCache.setCacheObject(dailyCanReceiveMaxNumRedisKey, Math.toIntExact(team.getPlanDataNum()));
            } else if (dailyCanReceiveMaxNum == null){
                continue;
            }

            String haveDoneAssignNumRedisKey = CacheConstants.DAILY_HAVE_ASSIGN_DONE_NUM + user.getUserId() + "_" + DateUtils.getDate(DateUtils.YYYYMMDD);

            Integer haveDoneAssignNum = redisCache.getCacheObject(haveDoneAssignNumRedisKey);
            if (haveDoneAssignNum == null) {
                haveDoneAssignNum = 0;
            }

            if (dailyCanReceiveMaxNum > haveDoneAssignNum) {
                assignUsersNum = assignUsersNum + dailyCanReceiveMaxNum - haveDoneAssignNum;
                dailyCanAssignMaxNumMap.put(user.getUserId(), dailyCanReceiveMaxNum);
                haveDoneAssignNumMap.put(user.getUserId(), haveDoneAssignNum);
            }
        }

        if (assignUsersNum == 0) {
            // 第二天0点过期
            redisCache.setCacheObject(CacheConstants.ALLOW_SYSTEM_ASSIGN_NEW_CUSTOMER_SWITCH_KEY, 0, (int)hoursUntilMidnight, TimeUnit.HOURS);
            return;
        }

        List<St02CrmCustomerNew> rawCustomerList = st02CrmCustomerNewService.selectCrmCustomerNewList(new CustomerReqVo());


        List<St02CrmCustomerNew>  updateCustomers = new ArrayList<>();



        for (St02CrmCustomerNew rawCustomer: rawCustomerList) {
            if (assignUsersNum == 0) {
                break;
            }


            // 找到map中 value最小的，分配给销售
            if (!haveDoneAssignNumMap.isEmpty()) {
                Map.Entry<Long, Integer> minEntry = null;
                for (Map.Entry<Long, Integer> entry : haveDoneAssignNumMap.entrySet()) {
                    Integer dailyCanMaxNum = dailyCanAssignMaxNumMap.get(entry.getKey());
                    if (dailyCanMaxNum == null || entry.getValue() >= dailyCanMaxNum) {
                        continue;
                    }

                    if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) < 0) {
                        minEntry = entry;
                    }
                }

                // 分配给最少客户的销售

               if (minEntry != null && minEntry.getValue() < dailyCanAssignMaxNumMap.get(minEntry.getKey())) {
                   rawCustomer.setUserId(minEntry.getKey());
                   updateCustomers.add(rawCustomer);
               } else {
                   return;
               }

                haveDoneAssignNumMap.put(minEntry.getKey(), minEntry.getValue() + 1);
                assignUsersNum--;
            } else {
                return;
            }
        }



            for(St02CrmCustomerNew customerNew: updateCustomers) {

                try {
                    st02CrmCustomerNewService.deleteSt02CrmCustomerNewById(customerNew.getId());


                    St02CrmCustomer customer = new St02CrmCustomer();
                    BeanUtils.copyProperties(customerNew, customer);
                    customer.setId(null);
                    st02CrmCustomerService.insertSt02CrmCustomer(customer);

                    if (customer.getId() != null) {

                        St02CrmCustomerFlowInfo customerFlowInfo = new St02CrmCustomerFlowInfo();
                        customerFlowInfo.setCustomerId(customer.getId().toString());
                        // todo 暂时定义user_id为分配的目标用户的user_id, 后续改成操作者的user_id
                        customerFlowInfo.setUserId(customer.getUserId());
                        customerFlowInfo.setUserName("SYSTEM");

                        customerFlowInfo.setTargetPlat(userIdMap.get(customer.getUserId()).getUserName());
                        customerFlowInfo.setSourcePlat("API");

                        st02CrmCustomerFlowInfoService.insertSt02CrmCustomerFlowInfo(customerFlowInfo);
                    }
                    WebSocketServer.sendInfo("系统给你分配了1个新客户", customer.getUserId().toString());
                } catch (Exception e) {
                    log.error("ryAssignRawCustomerToUsers failed", e);
                }
            }

            // 回写redis已经分配的数量

            for (Map.Entry<Long, Integer> entry : haveDoneAssignNumMap.entrySet()) {
                String haveDoneAssignNumRedisKey = CacheConstants.DAILY_HAVE_ASSIGN_DONE_NUM + String.valueOf(entry.getKey()) + "_" + DateUtils.getDate(DateUtils.YYYYMMDD);
                redisCache.setCacheObject(haveDoneAssignNumRedisKey, entry.getValue(), 7, TimeUnit.DAYS);
            }

    }

}
