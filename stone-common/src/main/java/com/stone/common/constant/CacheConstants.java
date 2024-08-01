package com.stone.common.constant;

/**
 * 缓存的key 常量
 * 
 * @author ruoyi
 */
public class CacheConstants
{
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";


    // team-manage

    public static final String DAILY_HAVE_ASSIGN_DONE_NUM = "crm:team_manage:daily_have_assign_done_num:";   //用户每天已经分配的数量， 保留7天


    public static final String DAILY_CAN_ASSIGN_MAX_NUM = "crm:team_manage:daily_can_assign_max_num:";   //用户每天能分配新客户的最大数量

    public static final String ALLOW_ASSIGN_KEY = "crm:team_manage:allow_assign:";  //用户能接收数量的标志

    public static final String ALLOW_SYSTEM_ASSIGN_NEW_CUSTOMER_SWITCH_KEY = "crm:team_manage:allow_system_assign_new_customer_switch"; //是否可以继续分配， 防止定时任务给系统造成很大压力
}
