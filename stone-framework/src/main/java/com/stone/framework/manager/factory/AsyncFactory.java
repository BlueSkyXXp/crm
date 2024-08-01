package com.stone.framework.manager.factory;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.stone.common.utils.*;
import com.stone.system.domain.SysFormLog;
import com.stone.system.service.ISysFormLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stone.common.constant.Constants;
import com.stone.common.utils.ip.AddressUtils;
import com.stone.common.utils.ip.IpUtils;
import com.stone.common.utils.spring.SpringUtils;
import com.stone.system.domain.SysLogininfor;
import com.stone.system.domain.SysOperLog;
import com.stone.system.service.ISysLogininforService;
import com.stone.system.service.ISysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 异步工厂（产生任务用）
 * 
 * @author ruoyi
 */
public class AsyncFactory
{
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
            final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr();
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
                //只需要记录业务类型为1=新增,2=修改,3=删除的记录，并且该操作状态status=0为正常
                if(
                        StringUtils.equalsAny(String.valueOf(operLog.getBusinessType()),"1","2","3")
                        && StringUtils.equals(String.valueOf(operLog.getStatus()),"0")
                ){
                    SysFormLog sysFormLog=JSON.parseObject(operLog.getOperParam(), SysFormLog.class);
                    //同时需要记录存放参数的Javabean的类型
                    String method = operLog.getMethod();
                    List<String> packages = new ArrayList<>();
                    packages.addAll(Arrays.asList(StringUtils.split(method,".")));
                    packages.set(3,"domain");
                    packages.set(4,StringUtils.substring(packages.get(4),0,-10));
                    packages.remove(packages.size()-1);
                    String domainClass = packages.stream().collect(Collectors.joining("."));
                    sysFormLog.setBeanClass(domainClass);
                    sysFormLog.setBusinessType(String.valueOf(operLog.getBusinessType()));
                    sysFormLog.setCreatedTime(DateUtils.getTime());
                    sysFormLog.setCreatedBy(operLog.getOperName());
                    sysFormLog.setOperateParams(operLog.getOperParam());
                    SpringUtils.getBean(ISysFormLogService.class).insertSysFormLog(sysFormLog);
                }
            }
        };
    }
}
