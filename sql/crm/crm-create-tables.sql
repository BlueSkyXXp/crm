create table if not exists gen_table
(
    table_id          bigint auto_increment comment '编号'
    primary key,
    table_name        varchar(200) default ''     null comment '表名称',
    table_comment     varchar(500) default ''     null comment '表描述',
    sub_table_name    varchar(64)                 null comment '关联子表的表名',
    sub_table_fk_name varchar(64)                 null comment '子表关联的外键名',
    class_name        varchar(100) default ''     null comment '实体类名称',
    tpl_category      varchar(200) default 'crud' null comment '使用的模板（crud单表操作 tree树表操作）',
    tpl_web_type      varchar(30)  default ''     null comment '前端模板类型（element-ui模版 element-plus模版）',
    package_name      varchar(100)                null comment '生成包路径',
    module_name       varchar(30)                 null comment '生成模块名',
    business_name     varchar(30)                 null comment '生成业务名',
    function_name     varchar(50)                 null comment '生成功能名',
    function_author   varchar(50)                 null comment '生成功能作者',
    gen_type          char         default '0'    null comment '生成代码方式（0zip压缩包 1自定义路径）',
    gen_path          varchar(200) default '/'    null comment '生成路径（不填默认项目路径）',
    options           varchar(1000)               null comment '其它生成选项',
    create_by         varchar(64)  default ''     null comment '创建者',
    create_time       datetime                    null comment '创建时间',
    update_by         varchar(64)  default ''     null comment '更新者',
    update_time       datetime                    null comment '更新时间',
    remark            varchar(500)                null comment '备注'
    )
    comment '代码生成业务表';

create table if not exists gen_table_column
(
    column_id      bigint auto_increment comment '编号'
    primary key,
    table_id       bigint                    null comment '归属表编号',
    column_name    varchar(200)              null comment '列名称',
    column_comment varchar(500)              null comment '列描述',
    column_type    varchar(100)              null comment '列类型',
    java_type      varchar(500)              null comment 'JAVA类型',
    java_field     varchar(200)              null comment 'JAVA字段名',
    is_pk          char                      null comment '是否主键（1是）',
    is_increment   char                      null comment '是否自增（1是）',
    is_required    char                      null comment '是否必填（1是）',
    is_insert      char                      null comment '是否为插入字段（1是）',
    is_edit        char                      null comment '是否编辑字段（1是）',
    is_list        char                      null comment '是否列表字段（1是）',
    is_query       char                      null comment '是否查询字段（1是）',
    query_type     varchar(200) default 'EQ' null comment '查询方式（等于、不等于、大于、小于、范围）',
    html_type      varchar(200)              null comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    dict_type      varchar(200) default ''   null comment '字典类型',
    sort           int                       null comment '排序',
    create_by      varchar(64)  default ''   null comment '创建者',
    create_time    datetime                  null comment '创建时间',
    update_by      varchar(64)  default ''   null comment '更新者',
    update_time    datetime                  null comment '更新时间'
    )
    comment '代码生成业务表字段';

create table if not exists qrtz_calendars
(
    sched_name    varchar(120) not null comment '调度名称',
    calendar_name varchar(200) not null comment '日历名称',
    calendar      blob         not null comment '存放持久化calendar对象',
    primary key (sched_name, calendar_name)
    )
    comment '日历信息表';

create table if not exists qrtz_fired_triggers
(
    sched_name        varchar(120) not null comment '调度名称',
    entry_id          varchar(95)  not null comment '调度器实例id',
    trigger_name      varchar(200) not null comment 'qrtz_triggers表trigger_name的外键',
    trigger_group     varchar(200) not null comment 'qrtz_triggers表trigger_group的外键',
    instance_name     varchar(200) not null comment '调度器实例名',
    fired_time        bigint       not null comment '触发的时间',
    sched_time        bigint       not null comment '定时器制定的时间',
    priority          int          not null comment '优先级',
    state             varchar(16)  not null comment '状态',
    job_name          varchar(200) null comment '任务名称',
    job_group         varchar(200) null comment '任务组名',
    is_nonconcurrent  varchar(1)   null comment '是否并发',
    requests_recovery varchar(1)   null comment '是否接受恢复执行',
    primary key (sched_name, entry_id)
    )
    comment '已触发的触发器表';

create table if not exists qrtz_job_details
(
    sched_name        varchar(120) not null comment '调度名称',
    job_name          varchar(200) not null comment '任务名称',
    job_group         varchar(200) not null comment '任务组名',
    description       varchar(250) null comment '相关介绍',
    job_class_name    varchar(250) not null comment '执行任务类名称',
    is_durable        varchar(1)   not null comment '是否持久化',
    is_nonconcurrent  varchar(1)   not null comment '是否并发',
    is_update_data    varchar(1)   not null comment '是否更新数据',
    requests_recovery varchar(1)   not null comment '是否接受恢复执行',
    job_data          blob         null comment '存放持久化job对象',
    primary key (sched_name, job_name, job_group)
    )
    comment '任务详细信息表';

create table if not exists qrtz_locks
(
    sched_name varchar(120) not null comment '调度名称',
    lock_name  varchar(40)  not null comment '悲观锁名称',
    primary key (sched_name, lock_name)
    )
    comment '存储的悲观锁信息表';

create table if not exists qrtz_paused_trigger_grps
(
    sched_name    varchar(120) not null comment '调度名称',
    trigger_group varchar(200) not null comment 'qrtz_triggers表trigger_group的外键',
    primary key (sched_name, trigger_group)
    )
    comment '暂停的触发器表';

create table if not exists qrtz_scheduler_state
(
    sched_name        varchar(120) not null comment '调度名称',
    instance_name     varchar(200) not null comment '实例名称',
    last_checkin_time bigint       not null comment '上次检查时间',
    checkin_interval  bigint       not null comment '检查间隔时间',
    primary key (sched_name, instance_name)
    )
    comment '调度器状态表';

create table if not exists qrtz_triggers
(
    sched_name     varchar(120) not null comment '调度名称',
    trigger_name   varchar(200) not null comment '触发器的名字',
    trigger_group  varchar(200) not null comment '触发器所属组的名字',
    job_name       varchar(200) not null comment 'qrtz_job_details表job_name的外键',
    job_group      varchar(200) not null comment 'qrtz_job_details表job_group的外键',
    description    varchar(250) null comment '相关介绍',
    next_fire_time bigint       null comment '上一次触发时间（毫秒）',
    prev_fire_time bigint       null comment '下一次触发时间（默认为-1表示不触发）',
    priority       int          null comment '优先级',
    trigger_state  varchar(16)  not null comment '触发器状态',
    trigger_type   varchar(8)   not null comment '触发器的类型',
    start_time     bigint       not null comment '开始时间',
    end_time       bigint       null comment '结束时间',
    calendar_name  varchar(200) null comment '日程表名称',
    misfire_instr  smallint     null comment '补偿执行的策略',
    job_data       blob         null comment '存放持久化job对象',
    primary key (sched_name, trigger_name, trigger_group),
    constraint QRTZ_TRIGGERS_ibfk_1
    foreign key (sched_name, job_name, job_group) references qrtz_job_details (sched_name, job_name, job_group)
    )
    comment '触发器详细信息表';

create table if not exists qrtz_blob_triggers
(
    sched_name    varchar(120) not null comment '调度名称',
    trigger_name  varchar(200) not null comment 'qrtz_triggers表trigger_name的外键',
    trigger_group varchar(200) not null comment 'qrtz_triggers表trigger_group的外键',
    blob_data     blob         null comment '存放持久化Trigger对象',
    primary key (sched_name, trigger_name, trigger_group),
    constraint QRTZ_BLOB_TRIGGERS_ibfk_1
    foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers (sched_name, trigger_name, trigger_group)
    )
    comment 'Blob类型的触发器表';

create table if not exists qrtz_cron_triggers
(
    sched_name      varchar(120) not null comment '调度名称',
    trigger_name    varchar(200) not null comment 'qrtz_triggers表trigger_name的外键',
    trigger_group   varchar(200) not null comment 'qrtz_triggers表trigger_group的外键',
    cron_expression varchar(200) not null comment 'cron表达式',
    time_zone_id    varchar(80)  null comment '时区',
    primary key (sched_name, trigger_name, trigger_group),
    constraint QRTZ_CRON_TRIGGERS_ibfk_1
    foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers (sched_name, trigger_name, trigger_group)
    )
    comment 'Cron类型的触发器表';

create table if not exists qrtz_simple_triggers
(
    sched_name      varchar(120) not null comment '调度名称',
    trigger_name    varchar(200) not null comment 'qrtz_triggers表trigger_name的外键',
    trigger_group   varchar(200) not null comment 'qrtz_triggers表trigger_group的外键',
    repeat_count    bigint       not null comment '重复的次数统计',
    repeat_interval bigint       not null comment '重复的间隔时间',
    times_triggered bigint       not null comment '已经触发的次数',
    primary key (sched_name, trigger_name, trigger_group),
    constraint QRTZ_SIMPLE_TRIGGERS_ibfk_1
    foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers (sched_name, trigger_name, trigger_group)
    )
    comment '简单触发器的信息表';

create table if not exists qrtz_simprop_triggers
(
    sched_name    varchar(120)   not null comment '调度名称',
    trigger_name  varchar(200)   not null comment 'qrtz_triggers表trigger_name的外键',
    trigger_group varchar(200)   not null comment 'qrtz_triggers表trigger_group的外键',
    str_prop_1    varchar(512)   null comment 'String类型的trigger的第一个参数',
    str_prop_2    varchar(512)   null comment 'String类型的trigger的第二个参数',
    str_prop_3    varchar(512)   null comment 'String类型的trigger的第三个参数',
    int_prop_1    int            null comment 'int类型的trigger的第一个参数',
    int_prop_2    int            null comment 'int类型的trigger的第二个参数',
    long_prop_1   bigint         null comment 'long类型的trigger的第一个参数',
    long_prop_2   bigint         null comment 'long类型的trigger的第二个参数',
    dec_prop_1    decimal(13, 4) null comment 'decimal类型的trigger的第一个参数',
    dec_prop_2    decimal(13, 4) null comment 'decimal类型的trigger的第二个参数',
    bool_prop_1   varchar(1)     null comment 'Boolean类型的trigger的第一个参数',
    bool_prop_2   varchar(1)     null comment 'Boolean类型的trigger的第二个参数',
    primary key (sched_name, trigger_name, trigger_group),
    constraint QRTZ_SIMPROP_TRIGGERS_ibfk_1
    foreign key (sched_name, trigger_name, trigger_group) references qrtz_triggers (sched_name, trigger_name, trigger_group)
    )
    comment '同步机制的行锁表';

create index sched_name
    on qrtz_triggers (sched_name, job_name, job_group);

create table if not exists st01_crm_area
(
    code_id   int          null,
    parent_id int          null,
    city_name varchar(255) null
    )
    comment '地名表' charset = utf8mb4
    row_format = DYNAMIC;

create table if not exists st01_crm_business
(
    business_id   varchar(128)            not null comment '商机编号'
    primary key,
    business_name varchar(50)             null comment '商机名称',
    money         decimal(18, 2)          null comment '商机金额',
    status_id     varchar(10)             null comment '商机阶段;1：初步沟通2：需求确定3：方案报价4：谈判协商5：',
    next_time     varchar(19)             null comment '下次联系时间',
    customer_id   varchar(128)            null comment '客户ID',
    deal_date     varchar(19)             null comment '预计成交日期',
    discount_rate decimal(10, 2)          null comment '整单折扣',
    total_price   decimal(10, 2)          null comment '产品总金额',
    source        varchar(10)             null comment '商机来源;1：独立开发2：来电咨询3：广告宣传4：搜索引擎5：客户介绍6：展会7：招标公告8：其他',
    remark        varchar(500)            null comment '备注',
    ro_user_id    varchar(10)             null comment '只读权限',
    rw_user_id    varchar(10)             null comment '读写权限',
    is_end        varchar(10) default '0' null comment '1赢单2输单3无效',
    created_by    varchar(128)            null comment '创建人',
    owner_user_id varchar(128)            null comment '负责人ID',
    created_time  varchar(19)             null comment '创建时间',
    updated_by    varchar(128)            null comment '更新人',
    updated_time  varchar(19)             null comment '更新时间'
    )
    comment '商机表';

create table if not exists st01_crm_business_change
(
    change_id    varchar(128) not null comment '变化ID'
    primary key,
    business_id  varchar(128) null comment '商机ID',
    status_id    varchar(10)  null comment '阶段ID',
    created_time varchar(19)  null comment '创建时间',
    created_by   varchar(128) null comment '创建人'
    )
    comment '商机阶段变化表';

create table if not exists st01_crm_business_product
(
    r_id        varchar(128)   not null comment 'id'
    primary key,
    business_id varchar(128)   null comment '商机ID',
    product_id  varchar(128)   null comment '产品ID',
    price       decimal(18, 2) null comment '产品单价',
    sales_price decimal(18, 2) null comment '销售价格',
    num         varchar(10)    null comment '数量',
    discount    decimal(10, 2) null comment '折扣',
    subtotal    decimal(18, 2) null comment '小计（折扣后价格）',
    unit        varchar(10)    null comment '单位'
    )
    comment '商机产品关系表';

create table if not exists st01_crm_contacts
(
    contacts_id       varchar(128) not null comment '联系人编号'
    primary key,
    contacts_name     varchar(256) null comment '姓名',
    mobile            varchar(128) null comment '手机',
    telephone         varchar(128) null comment '电话',
    department        varchar(128) null comment '部门',
    duties            varchar(128) null comment '职务',
    sex               varchar(10)  null comment '性别;0 未知 1 男 2 女',
    birthday          varchar(128) null comment '生日',
    mailbox           varchar(128) null comment '邮箱',
    weChat            varchar(56)  null comment '微信号',
    qq                varchar(56)  null comment 'QQ号',
    address           varchar(256) null comment '详细地址',
    intimacy          varchar(10)  null comment '亲密度',
    important         varchar(10)  null comment '重要程度',
    next_contact_time varchar(19)  null comment '下次联系时间',
    dc                varchar(10)  null comment '是否为默认联系人;0是1否',
    dm                varchar(10)  null comment '是否为关键决策人;0是1否',
    customer_id       varchar(128) null comment '客户ID',
    deltag            varchar(10)  null comment '删除标记;0未删除1已删除',
    remark            varchar(256) null comment '备注',
    owner_user_id     varchar(128) null comment '负责人',
    created_by        varchar(32)  null comment '创建人',
    created_time      varchar(19)  null comment '创建时间',
    updated_by        varchar(32)  null comment '更新人',
    updated_time      varchar(19)  null comment '更新时间'
    )
    comment '联系人';

create table if not exists st01_crm_contacts_business
(
    id          varchar(128) not null
    primary key,
    business_id varchar(128) null,
    contacts_id varchar(128) null
    )
    comment '商机联系人关联表';

create table if not exists st01_crm_contract
(
    contract_id       varchar(128)            not null comment '合同编号'
    primary key,
    contract_name     varchar(256)            null comment '合同名称',
    money             decimal(18, 2)          null comment '合同金额',
    customer_id       varchar(128)            null comment '客户ID',
    customer_name     varchar(256)            null comment '客户名称',
    contacts_id       varchar(128)            null comment '联系人ID',
    contacts_name     varchar(128)            null comment '联系人名称',
    business_id       varchar(128)            null comment '商机ID',
    business_name     varchar(128)            null comment '商机名称',
    types             varchar(10)             null comment '合同类型;1：新合同2：续约合同',
    order_date        varchar(19)             null comment '下单日期',
    start_time        varchar(19)             null comment '开始时间',
    end_time          varchar(19)             null comment '结束时间',
    check_status      varchar(10) default '0' null comment '状态;0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回 5草稿 6 作废',
    examine_record_id varchar(128)            null comment '审核记录ID',
    discount_rate     decimal(10, 2)          null comment '整单折扣',
    total_price       decimal(10, 2)          null comment '产品总金额',
    payment_type      varchar(10)             null comment '付款方式',
    ro_user_id        varchar(10)             null comment '只读权限',
    rw_user_id        varchar(10)             null comment '读写权限',
    remark            varchar(512)            null comment '备注',
    updated_by        varchar(128)            null comment '更新人',
    created_by        varchar(128)            null comment '创建人',
    owner_user_id     varchar(19)             null comment '负责人',
    created_time      varchar(19)             null comment '创建时间',
    updated_time      varchar(128)            null comment '更新时间'
    )
    comment '合同表';

create table if not exists st01_crm_contract_product
(
    r_id          varchar(128)   not null
    primary key,
    contract_name varchar(256)   null comment '合同名称',
    contract_id   varchar(128)   null comment '合同ID',
    product_name  varchar(256)   null comment '产品名称',
    product_id    varchar(128)   null comment '产品ID',
    price         decimal(18, 2) null comment '产品单价',
    sales_price   decimal(18, 2) null comment '销售价格',
    num           varchar(10)    null comment '数量',
    discount      decimal(10, 2) null comment '折扣',
    subtotal      decimal(18, 2) null comment '小计（折扣后价格）',
    unit          varchar(50)    null comment '单位'
    )
    comment '合同产品关系表';

create table if not exists st01_crm_customer
(
    customer_id       varchar(128) not null comment '客户号'
    primary key,
    customer_name     varchar(256) null comment '客户名称',
    nature            varchar(10)  null comment '客户性质;1：企业客户
2：个人客户
3：政府单位
4：事业单位',
    source            varchar(10)  null comment '客户来源;1：电话咨询
2：自主开拓
3：公司资源
4：网络推广
5：渠道代理
6：其他方式',
    industry          varchar(10)  null comment '客户行业;1：金融
2：电信
3：教育
4：高科技
5：政府
6：制造业
7：服务业
8：能源
9：零售
10：媒体
11：娱乐
12：咨询
13：非盈利事业
14：公用事业
15：其他',
    grade             varchar(10)  null comment '客户分级;1：大型
2：中型
3：小型
4：微型',
    stage             varchar(10)  null comment '客户阶段;1：初步接洽
2：确认需求
3：解决方案
4：商务谈判
5：成交
6：流失',
    website           varchar(256) null comment '客户官网',
    type              varchar(10)  null comment '客户类型;1：最终客户
2：渠道客户
3：竞争对手',
    introduction      varchar(512) null comment '客户简介',
    importance        varchar(10)  null comment '重要程度',
    lock_status       varchar(10)  null comment '锁定状态;0：解锁1：锁定',
    address           varchar(512) null comment '详细地址',
    province_id       varchar(128) null comment '省',
    city_id           varchar(128) null comment '市',
    area_id           varchar(128) null comment '区',
    owner_user_id     varchar(128) null comment '销售负责人',
    sa_user_id        varchar(128) null comment '售后负责人',
    deltag            varchar(10)  null comment '删除标记;0：未删除1：已删除',
    remark            varchar(512) null comment '备注',
    created_by        varchar(32)  null comment '创建人',
    created_time      varchar(19)  null comment '创建时间',
    updated_by        varchar(32)  null comment '更新人',
    updated_time      varchar(19)  null comment '更新时间',
    next_contact_time varchar(19)  null
    )
    comment '客户表';

create table if not exists st01_crm_leads
(
    leads_id      varchar(128)            not null
    primary key,
    customer      varchar(256)            null comment '客户名称',
    nature        varchar(10)             null comment '客户性质;1：企业客户
2：个人客户
3：政府单位
4：事业单位',
    source        varchar(10)             null comment '客户来源;1：电话咨询
2：自主开拓
3：公司资源
4：网络推广
5：渠道代理
6：其他方式',
    industry      varchar(10)             null comment '客户行业;1：金融
2：电信
3：教育
4：高科技
5：政府
6：制造业
7：服务业
8：能源
9：零售
10：媒体
11：娱乐
12：咨询
13：非盈利事业
14：公用事业
15：其他',
    grade         varchar(10)             null comment '客户分级;1：大型
2：中型
3：小型
4：微型',
    website       varchar(256)            null comment '客户官网',
    introduction  varchar(512)            null comment '客户简介',
    province_id   varchar(128)            null comment '省',
    city_id       varchar(128)            null comment '市',
    area_id       varchar(128)            null comment '区',
    contacts      varchar(256)            null comment '联系人',
    sex           varchar(10)             null comment '性别;0 未知 1 男 2 女',
    mailbox       varchar(128)            null comment '邮箱',
    is_transform  varchar(10) default '0' null comment '是否转为客户;1已转化 0 未转化',
    followup      varchar(10)             null comment '跟进状态;0未跟进1已跟进',
    next_time     varchar(19)             null comment '下次联系时间',
    telephone     varchar(20)             null comment '电话',
    address       varchar(500)            null comment '地址',
    remark        varchar(50)             null comment '备注',
    created_by    varchar(128)            null comment '创建人',
    owner_user_id varchar(128)            null comment '负责人',
    created_time  varchar(19)             null comment '创建时间',
    updated_by    varchar(128)            null comment '更新人',
    updated_time  varchar(19)             null comment '更新时间'
    )
    comment '线索表';

create table if not exists st01_crm_owner_record
(
    record_id          varchar(128) not null
    primary key,
    type_id            varchar(128) null comment '对象id',
    type               varchar(10)  null comment '对象类型',
    pre_owner_user_id  varchar(128) null comment '上一负责人',
    post_owner_user_id varchar(128) null comment '接手负责人',
    created_time       varchar(19)  null comment '创建时间'
    )
    comment '负责人变更记录表';

create table if not exists st01_crm_product
(
    product_id   varchar(128)            not null
    primary key,
    product_name varchar(256)            null comment '产品名称',
    num          varchar(128)            null comment '产品编码',
    unit         varchar(10)             null comment '单位',
    price        decimal(18, 2)          null comment '价格',
    status       varchar(10) default '0' null comment '状态;1 上架 0 下架 3 删除',
    category_id  varchar(128)            null comment '产品分类ID',
    description  varchar(512)            null comment '产品描述',
    created_by   varchar(128)            null comment '创建人',
    updated_by   varchar(128)            null comment '更新人',
    create_time  varchar(19)             null comment '创建时间',
    update_time  varchar(19)             null comment '更新时间'
    )
    comment '产品表';

create table if not exists st01_crm_product_category
(
    category_id   varchar(128)            not null
    primary key,
    category_name varchar(200)            null,
    sort          varchar(10) default '0' null,
    created_by    varchar(32)             null comment '创建人',
    created_time  varchar(19)             null comment '创建时间',
    updated_by    varchar(32)             null comment '更新人',
    updated_time  varchar(19)             null comment '更新时间'
    )
    comment '产品分类表';

create table if not exists st01_crm_receivables
(
    receivables_id    varchar(128)   not null comment '回款ID'
    primary key,
    number            varchar(128)   null comment '回款编号',
    plan_id           varchar(128)   null comment '回款计划ID',
    customer_id       varchar(128)   null comment '客户ID',
    contract_id       varchar(128)   null comment '合同ID',
    return_time       varchar(19)    null comment '回款日期',
    return_type       varchar(10)    null comment '回款方式',
    money             decimal(18, 2) null comment '回款金额',
    remark            varchar(500)   null comment '备注',
    check_status      varchar(10)    null comment '状态;0 未审核 1 审核通过 2 审核拒绝 3 审核中 4 已撤回',
    examine_record_id varchar(128)   null comment '审核记录ID',
    created_by        varchar(128)   null comment '创建人',
    owner_user_id     varchar(128)   null comment '负责人',
    created_time      varchar(19)    null comment '创建时间',
    updated_by        varchar(128)   null comment '更新人',
    updated_time      varchar(19)    null comment '更新时间'
    )
    comment '回款表';

create table if not exists st01_crm_receivables_plan
(
    plan_id        varchar(128)   not null comment '回款计划ID'
    primary key,
    num            varchar(10)    null comment '期数',
    receivables_id varchar(128)   null comment '回款ID',
    contract_id    varchar(128)   null comment '合同ID',
    customer_id    varchar(128)   null comment '客户ID',
    status         varchar(10)    null comment '状态;1完成 0 未完成',
    money          decimal(18, 2) null comment '计划回款金额',
    return_date    varchar(10)    null comment '计划回款日期',
    return_type    varchar(256)   null comment '计划回款方式;1：支付宝2：微信3：银行卡转账4：对公转账5：支票6：现金7：其他',
    remind         varchar(10)    null comment '提前几天提醒;1：提前一天2：提前两天3：提前五天4：提前七天',
    remind_date    varchar(19)    null comment '提醒日期',
    remark         varchar(500)   null comment '备注',
    created_by     varchar(128)   null comment '创建人',
    owner_user_id  varchar(128)   null comment '负责人',
    created_time   varchar(19)    null comment '创建时间',
    updated_by     varchar(128)   null comment '更新人',
    updated_time   varchar(19)    null comment '更新时间'
    )
    comment '回款计划表';

create table if not exists st01_crm_record
(
    record_id    varchar(128)  not null
    primary key,
    types        varchar(10)   null comment '关联类型;1:商机2：合同3：回款计划4：汇款单',
    types_id     varchar(10)   null comment '类型ID',
    content      varchar(1000) null comment '跟进内容',
    category     varchar(10)   null comment '跟进类型;1：外出拜访2：客户面谈3：客户来电4：客户来访5：在线交流6：发送资料7：售后回访8：方案整理9：其他方面',
    business_ids varchar(128)  null comment '商机ID',
    contacts_ids varchar(128)  null comment '联系人ID',
    customer_id  varchar(255)  null comment '客户ID',
    next_time    varchar(19)   null comment '下次联系时间',
    record_time  varchar(19)   null comment '本次跟进时间',
    created_time varchar(19)   null comment '创建时间',
    updated_time varchar(19)   null comment '更新时间',
    updated_by   varchar(128)  null comment '更新人',
    created_by   varchar(128)  null comment '创建人'
    )
    comment '跟进记录';

create table if not exists st02_crm_approval_info
(
    id              int auto_increment comment '审件ID'
    primary key,
    recdate         varchar(10)                             null comment '进件日期',
    type            tinyint                                 null comment '单件类型: 信用贷...',
    user_id         int                                     null comment '跟进人(用户ID)',
    customer_id     int                                     null comment '客户id',
    customer_name   varchar(50)   default ''                null,
    status          tinyint       default 1                 null comment '审件状态: 审核,完成',
    pay_type        varchar(50)                             null comment '付款方式',
    approval_bank   varchar(200)                            null comment '进件银行',
    bank_manager    varchar(50)                             null comment '银行经理',
    rates           varchar(10)                             null comment '费率',
    money           varchar(10)                             null comment '进件额度',
    appro_money     int                                     null comment '批款额度',
    collect_money   double(10, 2) default 0.00              null comment '收款额度',
    collect_date    varchar(10)                             null comment '收款日期',
    cost_money      double(10, 2) default 0.00              null comment '成本费用',
    sincerity_money int           default 0                 null comment '诚意金',
    profit          double(10, 2) default 0.00              null comment '利润',
    collect_bank    varchar(200)                            null comment '收款银行',
    remark          text                                    null comment '备注信息',
    operator_name   varchar(50)                             null comment '审批人(用户ID)',
    create_time     timestamp     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '创建时间',
    update_time     timestamp     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
    )
    comment '审件表' charset = utf8mb4;

create table if not exists st02_crm_customer
(
    id                      int auto_increment comment '客户ID'
    primary key,
    source                  varchar(50)                         null comment '客户来源',
    name                    varchar(50)                         null comment '客户姓名',
    phone                   varchar(11)                         null comment '电话',
    type                    tinyint                             null comment '客户类型',
    age                     varchar(20)                         null comment '年龄',
    sex                     tinyint                             null comment '性别',
    region                  varchar(200)                        null comment '户籍',
    city                    varchar(200)                        null comment '城市',
    marital_status          tinyint                             null comment '婚姻状态',
    other_know              tinyint                             null comment '爱人是否知晓',
    sesame_credit           int                                 null comment '芝麻信用分',
    micro_loans             varchar(50)                         null comment '微粒贷',
    status                  tinyint   default 1                 null comment '客户状态',
    level                   tinyint   default 0                 null comment '客户星级',
    legal_person            tinyint                             null comment '是否法人',
    appli_year              int                                 null comment '年份',
    social_security         tinyint                             null comment '是否有社保',
    social_security_year    int                                 null comment '社保年份',
    social_security_money   int                                 null comment '社保金额',
    acc_fund                tinyint                             null comment '是否有公积金',
    acc_fund_year           int                                 null comment '公积金年份',
    acc_fund_money          int                                 null comment '公积金金额',
    professional            tinyint                             null comment '是否有职业身份',
    professional_type       tinyint                             null comment '单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业',
    pay_money               int                                 null comment '工资',
    pay_type                tinyint                             null comment '发薪方式:1:代发,2:转账,3:现金',
    property_info           tinyint   default (-(1))            null comment '房产信息:0:无, 1: 有',
    property_status         tinyint                             null comment '房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封',
    area                    int                                 null comment '面积',
    worth                   int                                 null comment '价值',
    owner_type              tinyint                             null comment '房屋拥有: 1: 共有 2:单独',
    house_city              varchar(200)                        null comment '城市',
    mortgage_time           int                                 null comment '按揭多久(多少个月)',
    mortgage_money          int                                 null comment '月供',
    mortgage_bank           varchar(200)                        null comment '按揭银行',
    car_info                tinyint   default (-(1))            null comment '车产信息: 0: 无, 1: 有',
    car_status              tinyint                             null comment '车辆状态: 1:全款, 2:按揭, 3:抵押',
    car_term_year           int                                 null comment '年限',
    car_price               varchar(20)                         null comment '裸车价',
    car_city                varchar(200)                        null comment '汽车城市',
    car_brand               varchar(200)                        null comment '汽车品牌',
    car_mortgage_time       int                                 null comment '按揭多久(多少个月)',
    car_mortgage_money      int                                 null comment '月供',
    car_mortgage_bank       varchar(200)                        null comment '按揭银行',
    policy_info             tinyint   default (-(1))            null comment '保单信息: 0: 无, 1:有',
    insurance_company       varchar(200)                        null comment '投保公司',
    insurance_pay_type      tinyint                             null comment '支付方式: 1: 年缴, 2:月缴',
    insurance_pay_times     varchar(200)                        null comment '缴了几年几次',
    credit_card_info        tinyint                             null comment '是否有信用卡',
    credit_card_money       int                                 null comment '信用卡金额',
    credit_card_late_status tinyint                             null comment '信用卡有无逾期',
    loan_money              int                                 null comment '贷款总额',
    late_status             tinyint                             null comment '有无逾期',
    min_demand_money        int                                 null comment '最小资金需求',
    max_demand_money        int                                 null comment '最大资金需求',
    loan_type               tinyint                             null comment '贷款类型',
    user_id                 int                                 null comment '跟进人(用户ID)',
    follow_times            int       default 0                 null comment '跟进次数',
    first_assign_time       timestamp                           null comment '原始分配时间',
    last_assign_time        timestamp                           null comment '最近分配时间',
    last_remark_time        timestamp                           null comment '最近备注时间',
    create_time             timestamp default CURRENT_TIMESTAMP null comment '用户申请时间',
    update_time             timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户处理时间'
    )
    comment '客户表' charset = utf8mb4;

create table if not exists st02_crm_customer_flow_info
(
    id          int auto_increment comment '流转记录ID'
    primary key,
    customer_id varchar(50)                           null comment '客户姓名',
    source_plat varchar(100)                          null comment '客户来源',
    target_plat varchar(30)                           null comment '客户去向',
    user_id     int                                   null comment '操作者(用户ID)',
    user_name   varchar(50) default ''                null comment '用户姓名',
    create_time timestamp   default CURRENT_TIMESTAMP null comment '用户创建时间'
    )
    comment '客户流转记录表' charset = utf8mb4;

create table if not exists st02_crm_customer_follow_info
(
    id                       int auto_increment comment '跟踪记录ID'
    primary key,
    customer_id              int                                   null comment '客户姓名',
    customer_level           int                                   null comment '客户等级',
    customer_status          int                                   null comment '客户状态:待跟进,已上门,已签约....',
    approval_money           int                                   null comment '预审批额度',
    approval_channel         varchar(50)                           null comment '预审批渠道',
    remark                   varchar(500)                          null comment '备注',
    comments                 varchar(500)                          null comment '点评',
    intent_perception_remark varchar(500)                          null comment '意向感知度备注',
    operator                 varchar(64) default ''                null comment '更新者',
    create_time              timestamp   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户创建时间',
    update_time              timestamp   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户更新时间'
    )
    comment '客户跟踪表' charset = utf8mb4;

create table if not exists st02_crm_customer_new
(
    id                      int auto_increment comment '客户ID'
    primary key,
    source                  varchar(50)                         null comment '客户来源',
    name                    varchar(50)                         null comment '客户姓名',
    phone                   varchar(11)                         null comment '电话',
    type                    tinyint                             null comment '客户类型',
    age                     varchar(20)                         null comment '年龄',
    sex                     tinyint                             null comment '性别',
    region                  varchar(200)                        null comment '户籍',
    city                    varchar(200)                        null comment '城市',
    marital_status          tinyint                             null comment '婚姻状态',
    other_know              tinyint                             null comment '爱人是否知晓',
    sesame_credit           int                                 null comment '芝麻信用分',
    micro_loans             varchar(50)                         null comment '微粒贷',
    status                  tinyint                             null comment '客户状态',
    level                   tinyint                             null comment '客户星级',
    legal_person            tinyint                             null comment '是否法人',
    appli_year              int                                 null comment '年份',
    social_security         tinyint                             null comment '是否有社保',
    social_security_year    int                                 null comment '社保年份',
    social_security_money   int                                 null comment '社保金额',
    acc_fund                tinyint                             null comment '是否有公积金',
    acc_fund_year           int                                 null comment '公积金年份',
    acc_fund_money          int                                 null comment '公积金金额',
    professional            tinyint                             null comment '是否有职业身份',
    professional_type       tinyint                             null comment '单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业',
    pay_money               int                                 null comment '工资',
    pay_type                tinyint                             null comment '发薪方式:1:代发,2:转账,3:现金',
    property_info           tinyint                             null comment '房产信息:0:无, 1: 有',
    property_status         tinyint                             null comment '房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封',
    area                    int                                 null comment '面积',
    worth                   int                                 null comment '价值',
    owner_type              tinyint                             null comment '房屋拥有: 1: 共有 2:单独',
    house_city              varchar(200)                        null comment '城市',
    mortgage_time           int                                 null comment '按揭多久(多少个月)',
    mortgage_money          int                                 null comment '月供',
    mortgage_bank           varchar(200)                        null comment '按揭银行',
    car_info                tinyint                             null comment '车产信息: 0: 无, 1: 有',
    car_status              tinyint                             null comment '车辆状态: 1:全款, 2:按揭, 3:抵押',
    car_term_year           int                                 null comment '年限',
    car_price               varchar(20)                         null comment '裸车价',
    car_city                varchar(200)                        null comment '汽车城市',
    car_brand               varchar(200)                        null comment '汽车品牌',
    car_mortgage_time       int                                 null comment '按揭多久(多少个月)',
    car_mortgage_money      int                                 null comment '月供',
    car_mortgage_bank       varchar(200)                        null comment '按揭银行',
    policy_info             tinyint                             null comment '保单信息: 0: 无, 1:有',
    insurance_company       varchar(200)                        null comment '投保公司',
    insurance_pay_type      tinyint                             null comment '支付方式: 1: 年缴, 2:月缴',
    insurance_pay_times     varchar(200)                        null comment '缴了几年几次',
    credit_card_info        tinyint                             null comment '是否有信用卡',
    credit_card_money       int                                 null comment '信用卡金额',
    credit_card_late_status tinyint                             null comment '信用卡有无逾期',
    loan_money              int                                 null comment '贷款总额',
    late_status             tinyint                             null comment '有无逾期',
    min_demand_money        int                                 null comment '最小资金需求',
    max_demand_money        int                                 null comment '最大资金需求',
    loan_type               tinyint                             null comment '贷款类型',
    user_id                 int                                 null comment '跟进人(用户ID)',
    follow_times            int                                 null comment '跟进次数',
    first_assign_time       timestamp                           null comment '原始分配时间',
    last_assign_time        timestamp                           null comment '最近分配时间',
    last_remark_time        timestamp                           null comment '最近备注时间',
    create_time             timestamp default CURRENT_TIMESTAMP null comment '用户申请时间',
    update_time             timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户处理时间'
    )
    comment '待分配客户表' charset = utf8mb4;

create table if not exists st02_crm_customer_receive_test
(
    id                      int auto_increment comment '客户ID'
    primary key,
    source                  varchar(50)                         null comment '客户来源',
    name                    varchar(50)                         null comment '客户姓名',
    phone                   varchar(11)                         null comment '电话',
    type                    tinyint                             null comment '客户类型',
    age                     varchar(20)                         null comment '年龄',
    sex                     tinyint                             null comment '性别',
    region                  varchar(200)                        null comment '户籍',
    city                    varchar(200)                        null comment '城市',
    marital_status          tinyint                             null comment '婚姻状态',
    other_know              tinyint                             null comment '爱人是否知晓',
    sesame_credit           int                                 null comment '芝麻信用分',
    micro_loans             varchar(50)                         null comment '微粒贷',
    status                  tinyint                             null comment '客户状态',
    level                   tinyint                             null comment '客户星级',
    legal_person            tinyint                             null comment '是否法人',
    appli_year              int                                 null comment '年份',
    social_security         tinyint                             null comment '是否有社保',
    social_security_year    int                                 null comment '社保年份',
    social_security_money   int                                 null comment '社保金额',
    acc_fund                tinyint                             null comment '是否有公积金',
    acc_fund_year           int                                 null comment '公积金年份',
    acc_fund_money          int                                 null comment '公积金金额',
    professional            tinyint                             null comment '是否有职业身份',
    professional_type       tinyint                             null comment '单位性质: 1:国企,2:公务员,3:上市公司,4:私营企业',
    pay_money               int                                 null comment '工资',
    pay_type                tinyint                             null comment '发薪方式:1:代发,2:转账,3:现金',
    property_info           tinyint                             null comment '房产信息:0:无, 1: 有',
    property_status         tinyint                             null comment '房产状态: 1:全款, 2:按揭, 3:抵押, 4:查封',
    area                    int                                 null comment '面积',
    worth                   int                                 null comment '价值',
    owner_type              tinyint                             null comment '房屋拥有: 1: 共有 2:单独',
    house_city              varchar(200)                        null comment '城市',
    mortgage_time           int                                 null comment '按揭多久(多少个月)',
    mortgage_money          int                                 null comment '月供',
    mortgage_bank           varchar(200)                        null comment '按揭银行',
    car_info                tinyint                             null comment '车产信息: 0: 无, 1: 有',
    car_status              tinyint                             null comment '车辆状态: 1:全款, 2:按揭, 3:抵押',
    car_term_year           int                                 null comment '年限',
    car_price               varchar(20)                         null comment '裸车价',
    car_city                varchar(200)                        null comment '汽车城市',
    car_brand               varchar(200)                        null comment '汽车品牌',
    car_mortgage_time       int                                 null comment '按揭多久(多少个月)',
    car_mortgage_money      int                                 null comment '月供',
    car_mortgage_bank       varchar(200)                        null comment '按揭银行',
    policy_info             tinyint                             null comment '保单信息: 0: 无, 1:有',
    insurance_company       varchar(200)                        null comment '投保公司',
    insurance_pay_type      tinyint                             null comment '支付方式: 1: 年缴, 2:月缴',
    insurance_pay_times     varchar(200)                        null comment '缴了几年几次',
    credit_card_info        tinyint                             null comment '是否有信用卡',
    credit_card_money       int                                 null comment '信用卡金额',
    credit_card_late_status tinyint                             null comment '信用卡有无逾期',
    loan_money              int                                 null comment '贷款总额',
    late_status             tinyint                             null comment '有无逾期',
    min_demand_money        int                                 null comment '最小资金需求',
    max_demand_money        int                                 null comment '最大资金需求',
    loan_type               tinyint                             null comment '贷款类型',
    user_id                 int                                 null comment '跟进人(用户ID)',
    follow_times            int                                 null comment '跟进次数',
    first_assign_time       timestamp                           null comment '原始分配时间',
    last_assign_time        timestamp                           null comment '最近分配时间',
    last_remark_time        timestamp                           null comment '最近备注时间',
    create_time             timestamp default CURRENT_TIMESTAMP null comment '用户申请时间',
    update_time             timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户处理时间'
    )
    comment '待分配客户测试表' charset = utf8mb4;

create table if not exists st02_crm_team_manage
(
    id                  int auto_increment comment '自增ID'
    primary key,
    user_id             varchar(50)                         null comment '跟进人(用户ID)',
    plan_data_num       int       default 100               null comment '日分配数据数',
    receive_data_num    int       default 0                 null comment '实际日分配数据数',
    receive_new_data    tinyint   default 1                 null comment '是否接收新数据: 接收,不接收',
    receive_public_data tinyint   default 1                 null comment '是否接收公共池数据: 接收,不接收',
    login               tinyint   default 1                 null comment '是否允许登录: 1, 0',
    remark              text                                null comment '备注信息',
    operator_id         int                                 null comment '操作者(用户ID)',
    create_time         timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '创建时间',
    update_time         timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
    )
    comment '团队管理表' charset = utf8mb4;

create table if not exists st02_crm_visit_info
(
    id          int auto_increment comment '来访记录ID'
    primary key,
    customer_id int                                 null comment '客户id',
    remark      text                                null comment '备注信息',
    user_id     int                                 null comment '跟进人(用户ID)',
    create_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '用户创建时间'
)
    comment '客户来访表' charset = utf8mb4;

create table if not exists st02_rong_360_order
(
    id                int auto_increment comment 'ID'
    primary key,
    order_id          bigint         null comment '系统订单id',
    city              varchar(16)    null comment '城市',
    user_name         varchar(128)   null comment '姓名',
    user_mobile       varchar(16)    null comment '电话',
    product           varchar(128)   null comment '产品名称',
    application_term  int            null comment '贷款期数(月)',
    banker_id         int            null comment '信贷员id',
    create_time       varchar(32)    null comment '下单时间',
    status            int            null comment '系统内部反馈状态',
    apply_from        varchar(128)   null comment '订单来源: 系统推单, 手推单',
    qsearch           json           null comment '用户资质',
    sharinglist_url   varchar(255)   null comment '个人共享清单',
    delete_at         datetime(6)    null comment '删除时间',
    application_limit decimal(10, 2) null comment '贷款金额(万元)',
    cost              decimal(10, 2) null comment '订单花费',
    authorization_url varchar(255)   null comment '授权文件(链接有效期5分钟)',
    constraint user_mobile
    unique (user_mobile)
    )
    comment '融360订单数据' charset = utf8mb4;

create table if not exists sys_area
(
    id       int          not null comment '区域主键'
    primary key,
    name     varchar(40)  null comment '区域名称',
    pid      int          null comment '区域上级标识',
    sname    varchar(40)  null comment '地名简称',
    level    int          null comment '区域等级',
    citycode varchar(20)  null comment '区域编码',
    yzcode   varchar(20)  null comment '邮政编码',
    Lng      float        null comment '经度',
    Lat      float        null comment '维度',
    pinyin   varchar(100) null comment '拼音',
    mername  varchar(100) null comment '组合名称'
    )
    comment '中国标准行政区表' charset = utf8mb4;

create index AREA_PID_INDEX
    on sys_area (pid);

create index sdsf
    on sys_area (mername);

create table if not exists sys_config
(
    config_id    int auto_increment comment '参数主键'
    primary key,
    config_name  varchar(100) default ''  null comment '参数名称',
    config_key   varchar(100) default ''  null comment '参数键名',
    config_value varchar(500) default ''  null comment '参数键值',
    config_type  char         default 'N' null comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default ''  null comment '创建者',
    create_time  datetime                 null comment '创建时间',
    update_by    varchar(64)  default ''  null comment '更新者',
    update_time  datetime                 null comment '更新时间',
    remark       varchar(500)             null comment '备注'
    )
    comment '参数配置表';

create table if not exists sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
    primary key,
    parent_id   bigint      default 0   null comment '父部门id',
    ancestors   varchar(50) default ''  null comment '祖级列表',
    dept_name   varchar(30) default ''  null comment '部门名称',
    order_num   int         default 0   null comment '显示顺序',
    leader      varchar(20)             null comment '负责人',
    phone       varchar(11)             null comment '联系电话',
    email       varchar(50)             null comment '邮箱',
    status      char        default '0' null comment '部门状态（0正常 1停用）',
    del_flag    char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime                null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime                null comment '更新时间'
    )
    comment '部门表';

create table if not exists sys_dict_data
(
    dict_code   bigint auto_increment comment '字典编码'
    primary key,
    dict_sort   int          default 0   null comment '字典排序',
    dict_label  varchar(100) default ''  null comment '字典标签',
    dict_value  varchar(100) default ''  null comment '字典键值',
    dict_type   varchar(100) default ''  null comment '字典类型',
    css_class   varchar(100)             null comment '样式属性（其他样式扩展）',
    list_class  varchar(100)             null comment '表格回显样式',
    is_default  char         default 'N' null comment '是否默认（Y是 N否）',
    status      char         default '0' null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间',
    remark      varchar(500)             null comment '备注'
    )
    comment '字典数据表';

create table if not exists sys_dict_type
(
    dict_id     bigint auto_increment comment '字典主键'
    primary key,
    dict_name   varchar(100) default ''  null comment '字典名称',
    dict_type   varchar(100) default ''  null comment '字典类型',
    status      char         default '0' null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间',
    remark      varchar(500)             null comment '备注',
    constraint dict_type
    unique (dict_type)
    )
    comment '字典类型表';

create table if not exists sys_form_log
(
    id                  int auto_increment comment '客户ID'
    primary key,
    customer_id         int                     null,
    business_id         int                     null,
    contacts_id         int                     null,
    contract_id         int                     null,
    receivables_id      int                     null,
    receivables_plan_id int                     null,
    record_id           varchar(100) default '' null,
    operate_params      text                    null comment '更新者',
    business_type       varchar(64)  default '' null comment '更新者',
    bean_class          varchar(64)  default '' null comment '更新者',
    created_by          varchar(64)  default '' null comment '更新者',
    created_time        datetime                null comment '创建时间',
    update_by           varchar(64)  default '' null comment '更新者',
    update_time         datetime                null comment '更新时间'
    )
    comment '操作表';

create table if not exists sys_job
(
    job_id          bigint auto_increment comment '任务ID',
    job_name        varchar(64)  default ''        not null comment '任务名称',
    job_group       varchar(64)  default 'DEFAULT' not null comment '任务组名',
    invoke_target   varchar(500)                   not null comment '调用目标字符串',
    cron_expression varchar(255) default ''        null comment 'cron执行表达式',
    misfire_policy  varchar(20)  default '3'       null comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent      char         default '1'       null comment '是否并发执行（0允许 1禁止）',
    status          char         default '0'       null comment '状态（0正常 1暂停）',
    create_by       varchar(64)  default ''        null comment '创建者',
    create_time     datetime                       null comment '创建时间',
    update_by       varchar(64)  default ''        null comment '更新者',
    update_time     datetime                       null comment '更新时间',
    remark          varchar(500) default ''        null comment '备注信息',
    primary key (job_id, job_name, job_group)
    )
    comment '定时任务调度表';

create table if not exists sys_job_log
(
    job_log_id     bigint auto_increment comment '任务日志ID'
    primary key,
    job_name       varchar(64)               not null comment '任务名称',
    job_group      varchar(64)               not null comment '任务组名',
    invoke_target  varchar(500)              not null comment '调用目标字符串',
    job_message    varchar(500)              null comment '日志信息',
    status         char          default '0' null comment '执行状态（0正常 1失败）',
    exception_info varchar(2000) default ''  null comment '异常信息',
    create_time    datetime                  null comment '创建时间'
    )
    comment '定时任务调度日志表';

create table if not exists sys_logininfor
(
    info_id        bigint auto_increment comment '访问ID'
    primary key,
    user_name      varchar(50)  default ''  null comment '用户账号',
    ipaddr         varchar(128) default ''  null comment '登录IP地址',
    login_location varchar(255) default ''  null comment '登录地点',
    browser        varchar(50)  default ''  null comment '浏览器类型',
    os             varchar(50)  default ''  null comment '操作系统',
    status         char         default '0' null comment '登录状态（0成功 1失败）',
    msg            varchar(255) default ''  null comment '提示消息',
    login_time     datetime                 null comment '访问时间'
    )
    comment '系统访问记录';

create index idx_sys_logininfor_lt
    on sys_logininfor (login_time);

create index idx_sys_logininfor_s
    on sys_logininfor (status);

create table if not exists sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
    primary key,
    menu_name   varchar(50)              not null comment '菜单名称',
    parent_id   bigint       default 0   null comment '父菜单ID',
    order_num   int          default 0   null comment '显示顺序',
    path        varchar(200) default ''  null comment '路由地址',
    component   varchar(255)             null comment '组件路径',
    query       varchar(255)             null comment '路由参数',
    is_frame    int          default 1   null comment '是否为外链（0是 1否）',
    is_cache    int          default 0   null comment '是否缓存（0缓存 1不缓存）',
    menu_type   char         default ''  null comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
    status      char         default '0' null comment '菜单状态（0正常 1停用）',
    perms       varchar(100)             null comment '权限标识',
    icon        varchar(100) default '#' null comment '菜单图标',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间',
    remark      varchar(500) default ''  null comment '备注'
    )
    comment '菜单权限表';

create table if not exists sys_notice
(
    notice_id      int auto_increment comment '公告ID'
    primary key,
    notice_title   varchar(50)             not null comment '公告标题',
    notice_type    char                    not null comment '公告类型（1通知 2公告）',
    notice_content longblob                null comment '公告内容',
    status         char        default '0' null comment '公告状态（0正常 1关闭）',
    create_by      varchar(64) default ''  null comment '创建者',
    create_time    datetime                null comment '创建时间',
    update_by      varchar(64) default ''  null comment '更新者',
    update_time    datetime                null comment '更新时间',
    remark         varchar(255)            null comment '备注'
    )
    comment '通知公告表';

create table if not exists sys_oper_log
(
    oper_id        bigint auto_increment comment '日志主键'
    primary key,
    title          varchar(50)   default '' null comment '模块标题',
    business_type  int           default 0  null comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)  default '' null comment '方法名称',
    request_method varchar(10)   default '' null comment '请求方式',
    operator_type  int           default 0  null comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' null comment '操作人员',
    dept_name      varchar(50)   default '' null comment '部门名称',
    oper_url       varchar(255)  default '' null comment '请求URL',
    oper_ip        varchar(128)  default '' null comment '主机地址',
    oper_location  varchar(255)  default '' null comment '操作地点',
    oper_param     varchar(2000) default '' null comment '请求参数',
    json_result    varchar(2000) default '' null comment '返回参数',
    status         int           default 0  null comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' null comment '错误消息',
    oper_time      datetime                 null comment '操作时间',
    cost_time      bigint        default 0  null comment '消耗时间'
    )
    comment '操作日志记录';

create index idx_sys_oper_log_bt
    on sys_oper_log (business_type);

create index idx_sys_oper_log_ot
    on sys_oper_log (oper_time);

create index idx_sys_oper_log_s
    on sys_oper_log (status);

create table if not exists sys_post
(
    post_id     bigint auto_increment comment '岗位ID'
    primary key,
    post_code   varchar(64)            not null comment '岗位编码',
    post_name   varchar(50)            not null comment '岗位名称',
    post_sort   int                    not null comment '显示顺序',
    status      char                   not null comment '状态（0正常 1停用）',
    create_by   varchar(64) default '' null comment '创建者',
    create_time datetime               null comment '创建时间',
    update_by   varchar(64) default '' null comment '更新者',
    update_time datetime               null comment '更新时间',
    remark      varchar(500)           null comment '备注'
    )
    comment '岗位信息表';

create table if not exists sys_role
(
    role_id             bigint auto_increment comment '角色ID'
    primary key,
    role_name           varchar(30)             not null comment '角色名称',
    role_key            varchar(100)            not null comment '角色权限字符串',
    role_sort           int                     not null comment '显示顺序',
    data_scope          char        default '1' null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly tinyint(1)  default 1   null comment '菜单树选择项是否关联显示',
    dept_check_strictly tinyint(1)  default 1   null comment '部门树选择项是否关联显示',
    status              char                    not null comment '角色状态（0正常 1停用）',
    del_flag            char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by           varchar(64) default ''  null comment '创建者',
    create_time         datetime                null comment '创建时间',
    update_by           varchar(64) default ''  null comment '更新者',
    update_time         datetime                null comment '更新时间',
    remark              varchar(500)            null comment '备注'
    )
    comment '角色信息表';

create table if not exists sys_role_dept
(
    role_id bigint not null comment '角色ID',
    dept_id bigint not null comment '部门ID',
    primary key (role_id, dept_id)
    )
    comment '角色和部门关联表';

create table if not exists sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
    )
    comment '角色和菜单关联表';

create table if not exists sys_user
(
    user_id     int auto_increment comment '用户ID'
    primary key,
    dept_id     bigint                    null comment '部门ID',
    user_name   varchar(30)               not null comment '用户账号',
    nick_name   varchar(30)               not null comment '用户昵称',
    user_type   varchar(2)   default '00' null comment '用户类型（00系统用户）',
    email       varchar(50)  default ''   null comment '用户邮箱',
    phonenumber varchar(11)  default ''   null comment '手机号码',
    sex         char         default '0'  null comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default ''   null comment '头像地址',
    password    varchar(100) default ''   null comment '密码',
    status      char         default '0'  null comment '帐号状态（0正常 1停用）',
    del_flag    char         default '0'  null comment '删除标志（0代表存在 2代表删除）',
    login_ip    varchar(128) default ''   null comment '最后登录IP',
    login_date  datetime                  null comment '最后登录时间',
    create_by   varchar(64)  default ''   null comment '创建者',
    create_time datetime                  null comment '创建时间',
    update_by   varchar(64)  default ''   null comment '更新者',
    update_time datetime                  null comment '更新时间',
    remark      varchar(500)              null comment '备注'
    )
    comment '用户信息表';

create table if not exists sys_user_post
(
    user_id bigint not null comment '用户ID',
    post_id bigint not null comment '岗位ID',
    primary key (user_id, post_id)
    )
    comment '用户与岗位关联表';

create table if not exists sys_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
    )
    comment '用户和角色关联表';

