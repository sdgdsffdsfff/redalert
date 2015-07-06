#主机初始化

insert into b_host_config 
    (host_id,
     host_name, 
     nick_name, 
     idc_id, 
     ip_addr, 
     is_host_collect, 
     user_account, 
     user_passwd, 
     os_info, 
     cpu_info, 
     memory_info, 
     disck_info, 
     status, 
     global_schedule_conf, 
     server_id, 
     gmt_created, 
     gmt_modifed, 
     last_monitor_status, 
     last_heartbeat_date, 
     root_account, 
     root_passwd, 
     warn_count, 
     critical_count
    ) values(
    '10',                           -- id  使用数字                 
    'syjz-db-slave1.189read.com',   -- hostname 机器的名字，唯一
    'MySQL商用集中库_备库',            -- 机器的别名
    '1',                            -- idc，默认为1
    '192.168.10.92',                -- ip地址
    'Y',                            -- 默认为Y
    'monitor',                      -- 采集的账号
    'pass',                     -- 采集的密码
    NULL,
    NULL,
    NULL,
    NULL,
    '0',                            -- 状态默认为0
    NULL,
    'srv01',                        -- 由哪个机器来采集，对应mail.properties 中的 com.tyyd.ywpt.server.id=srv01 
    NULL,
    NULL,
    NULL,                            
    NULL,
    NULL,
    NULL,
    NULL,
    NULL);



#数据库初始化
insert into b_db_config 
    (db_id, 
     host_id, 
     nick_name, 
     db_type, 
     db_username, 
     db_passwd, 
     sid, 
     db_name, 
     port, 
     status, 
     server_id, 
     gmt_created, 
     gmt_modifed, 
     last_monitor_status, 
     last_heartbeat_date, 
     global_schedule_conf, 
     slow_log_user, 
     slow_log_pass, 
     is_collect_slowlog, 
     warn_count, 
     critical_count, 
     database_id, 
     instance_id
    ) values(
    '11',                   -- id
    '10',                   -- 对应的主机ID
    'mysql商用集中（备库）',    -- 别名
    '3',                       -- 数据库类型，2：oracle,3:mysql 
    'rnd',                     -- 只读账号  
    'pass',                -- 只读账号的密码
    NULL,
    NULL,
    '3306',                 -- 设置端口
    '0',                     -- 状态，默认为0
    'srv01',                -- 由哪个机器来采集，对应mail.properties 中的 com.tyyd.ywpt.server.id=srv01 
    NULL,
    NULL,
    NULL,                   
    NULL,
    NULL,
    'myuser',               -- slowlog的采集用户  mysql用户
    'Slowlog',            -- slowlog 的采集密码
    '1',
    NULL,
    NULL,
    NULL,
    NULL
);


#日志监控配置
insert into b_host_dir_alert_config 
    (id, 
    monitor_id, 
    config_type, 
    log_type, 
    host_dir, 
    keyword, 
    server_id, 
    gmt_created, 
    gmt_modifed
   ) values(
   '1',                             -- id
   '11',                            -- 监控机器id
   '3',                             -- 监控机 oracle 2，mysql 3 ，keepalived 4
   '2',                             -- 日志类型 1: oracle alert 2:MYSQL ALERT 3:keepAlived Alert 
   '/data/mysql/log/alert.log',     -- 日志的具体存放路径
   'error|ERROR|fail|Errcode',      -- 监控项的关键字 ,oracle推荐 ORA-|error|ERROR|fail|Deadlock , mysql推荐error|ERROR|fail|Errcode
   'srv01',                         -- 由哪个机器来采集,最好跟监控机器的采集机对应
   '2015-07-10 14:38:00',
   '2015-07-10 14:38:00'
);


#用户配置
insert into t_user 
    (id, 
    user_name, 
    login_name, 
    email, 
    mobile_mail, 
    phone, 
    status, 
    passwd, 
    gmt_created, 
    gmt_modifed
   ) values(
   '1',               -- id
   '登录名',          -- 用户名 loginname
   '名字',            -- 昵称
   '邮箱',            -- 账号
   NULL,
   'phone' ,          -- 手机号码
   '0',               -- 状态 0
   NULL,
   NULL,
   NULL
);


#任务配置
    
  #主机的任务配置
    SET @p_in='监控机id';   CALL p_init_server_quota(@p_in);
    SET @p1_in='监控机id'; SET @p2_in='1'; CALL p_init_monitor_deamon_schedule_config(@p1_in,@p2_in);   
       
       
  #Oracle的任务配置
  SET @p_in='监控机id';   CALL p_init_oracle_quota(@p_in);    
  SET @p1_in='监控机id'; SET @p2_in='2'; CALL p_init_monitor_deamon_schedule_config(@p1_in,@p2_in);   
     
  
  #MySQL的任务配置
   SET @p_in='监控机id';   CALL p_init_mysql_quota(@p_in);
   SET @p1_in='监控机id'; SET @p2_in='3'; CALL p_init_monitor_deamon_schedule_config(@p1_in,@p2_in);   
     



#预警接收人  
    
#初始化主机层的接收人
INSERT INTO `b_db_alert_notify_config`(monitor_id,config_type,user_id,STATUS,gmt_created,gmt_modifed)
SELECT host_id monitor_id,1 config_type,u.id,0,NOW(),NOW() FROM `b_host_config` g,t_user u 
    WHERE u.id IN ('用户ID') AND host_id IN ('监控的ID');


#初始化数据库层的接收人
INSERT INTO `b_db_alert_notify_config`(monitor_id,config_type,user_id,STATUS,gmt_created,gmt_modifed)
SELECT db_id monitor_id,db_type config_type,u.id,0,NOW(),NOW() FROM `b_db_config` g,t_user u 
WHERE u.id IN ('用户ID') AND db_id IN ('监控的ID')
;


#预警阈值设置

#主机层的预警设置
SET @p_in='监控的ID';   CALL p_server_quota_threshold_init(@p_in);

#mysql层的预警设置
SET @p_in='监控的ID';   call p_mysql_quota_threshold_init(@p_in);

#oracle层的预警设置
SET @p_in='监控的ID';   call p_oracle_quota_threshold_init(@p_in);    



#基线配置
#主机基线配置，全配置
INSERT INTO b_base_line_cfg(
	monitor_id,
	config_type,
	quota_id,
	gmt_created,
	gmt_modifed 
) 
SELECT host_id,1,'1',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'10',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'100',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'101',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'11',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'12',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'13',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'14',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'15',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'16',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'17',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'2',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'3',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'4',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'5',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'7',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 UNION ALL
SELECT host_id,1,'8',NOW(),NOW() FROM `b_host_config` WHERE STATUS = 0 ;


#Oracle基线

INSERT INTO b_base_line_cfg(
	monitor_id,
	config_type,
	quota_id,
	gmt_created,
	gmt_modifed 
) 
SELECT host_id,2,'2000',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2003',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2004',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2006',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2016',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2018',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2030',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2022',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL
SELECT host_id,2,'2024',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL
SELECT host_id,2,'2046',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL
SELECT host_id,2,'2034',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2057',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2058',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2079',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2092',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2100',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2103',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2106',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2110',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2112',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2114',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2115',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2118',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2119',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'2121',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 UNION ALL 
SELECT host_id,2,'77777781',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=2 ; 


#Mysql基线

INSERT INTO b_base_line_cfg(
	monitor_id,
	config_type,
	quota_id,
	gmt_created,
	gmt_modifed 
) 
SELECT host_id,3,'18',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'19',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'20',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'30',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'31',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'32',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'33',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'40',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'42',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'43',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'44',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'45',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'46',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'47',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'48',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'53',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'60',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'61',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'62',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'63',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'64',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 UNION ALL 
SELECT host_id,3,'77777779',NOW(),NOW() FROM `b_db_config` WHERE STATUS = 0 AND db_type=3 ; 



#任务修复地址配置

#初始化采集服务地址
insert into b_server_reg (
    id, 
    server_name, 
    ip, 
    port, 
    gmt_created, 
    gmt_modifed) 
values(
    '1',            -- id
    'srv01',        -- 定义采集的唯一性ID
    '192.168.10.93', -- 采集服务器的IP地址
    '9090',         -- jetty对应的端口
    NULL,
    NULL
);


#初始化授权调用地址，如果没有配置这个IP，则无法进行自动修复僵死任务
insert into b_monitor_regedit_privs_ip (
    id, 
    ipaddr, 
    gmt_created, 
    gmt_modifed
)  values(
    '1',                 -- id
    '10.1.10.29',       -- 授权可以自动修复的IP，其实就是jetty对应的那台服务器
    now(),
    now()
);


