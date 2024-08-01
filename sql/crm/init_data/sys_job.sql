INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2024-04-14 20:13:15', '', null, '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2024-04-14 20:13:15', '', null, '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2024-04-14 20:13:16', '', null, '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('获取融360的数据', 'DEFAULT', 'ryTask.ryFetchRong360Data', '0/10 * * * * ?', '1', '1', '1', 'admin', '2024-06-27 14:46:02', 'admin', '2024-07-24 11:07:30', '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('将融360数据移入到待分配', 'DEFAULT', 'ryTask.ryAssignRong360DataToCustomer', '0/10 * * * * ?', '1', '1', '1', 'admin', '2024-06-30 17:41:24', 'admin', '2024-07-24 11:07:28', '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('轮训随机分配', 'DEFAULT', 'ryTask.ryRotationAssignNewCustomerToUsers', '0/15 * * * * ?', '1', '1', '1', 'admin', '2024-07-05 12:29:10', 'admin', '2024-07-24 11:07:26', '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('通知', 'DEFAULT', 'ryTask.rySendNotice("这是一条测试语音播报的信息", "10")', '0/10 * * * * ?', '1', '1', '1', 'admin', '2024-07-06 21:57:28', 'admin', '2024-07-21 02:27:23', '');
INSERT INTO stone.sys_job (job_name, job_group, invoke_target, cron_expression, misfire_policy, concurrent, status, create_by, create_time, update_by, update_time, remark) VALUES ('客户自动掉库', 'DEFAULT', 'ryTask.ryAutoRemoveCustomer2Public(15)', '0 0 3 1/1 1/1 ?', '1', '1', '1', 'admin', '2024-07-23 23:14:22', '', null, '');
