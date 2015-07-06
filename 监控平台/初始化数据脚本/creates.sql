/* grant all on ywpt.* to ywpt_user@'%' identified by 'ywpt';   */
/* grant ALTER ROUTINE,CREATE ROUTINE,EXECUTE on ywpt.* to ywpt_user@'%' identified by 'ywpt'; */
/*
SQLyog v10.2 
MySQL - 5.6.12-log : Database - ywpt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ywpt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ywpt`;

/*Table structure for table `b_alert_notify_record` */

DROP TABLE IF EXISTS `b_alert_notify_record`;

CREATE TABLE `b_alert_notify_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alert_record_id` varchar(40) DEFAULT NULL,
  `recevice_mails` varchar(500) DEFAULT NULL COMMENT '收件人,分号分割',
  `mobile_mails` varchar(500) DEFAULT NULL COMMENT '手机收件人,分号分割',
  `is_complete` char(1) DEFAULT NULL COMMENT '是否处理,0:否，1：是',
  `notice_type` smallint(6) DEFAULT NULL COMMENT '预警类型 0:email 1:短信+mail',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户ID',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='告警通知记录';

/*Table structure for table `b_alert_record` */

DROP TABLE IF EXISTS `b_alert_record`;

CREATE TABLE `b_alert_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(40) NOT NULL COMMENT '数据库ID/主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` float DEFAULT NULL COMMENT '采集值',
  `quota_unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `sys_type` smallint(6) DEFAULT NULL COMMENT '系统类型 见字典（sys_type）',
  `title` varchar(300) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `is_complete` char(1) DEFAULT NULL COMMENT '是否处理,0:否，1：是',
  `notice_type` smallint(6) DEFAULT NULL COMMENT '预警类型 0:email 1:短信+mail',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `notice_level` smallint(6) DEFAULT NULL COMMENT '预警级别，1：warn、2：critical',
  `monitor_type` smallint(6) DEFAULT NULL COMMENT '分类. 1：心跳检查、2：指标、3：磁盘、4：表空间、5：告警日志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='告警记录';

/*Table structure for table `b_base_line_cfg` */

DROP TABLE IF EXISTS `b_base_line_cfg`;

CREATE TABLE `b_base_line_cfg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(40) NOT NULL COMMENT '主机ID',
  `config_type` smallint(2) DEFAULT NULL COMMENT '监控机类型',
  `quota_id` varchar(40) NOT NULL COMMENT '指标ID',
  `gmt_created` datetime NOT NULL,
  `gmt_modifed` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机和数据指基线指标配置表';

/*Table structure for table `b_base_line_record` */

DROP TABLE IF EXISTS `b_base_line_record`;

CREATE TABLE `b_base_line_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `minitor_id` varchar(40) NOT NULL COMMENT '主机ID',
  `config_type` smallint(2) DEFAULT NULL COMMENT '类型',
  `quota_id` varchar(40) NOT NULL COMMENT '指标ID',
  `base_time` varchar(10) NOT NULL COMMENT '基线时间(时分)',
  `max_val` double NOT NULL COMMENT '三天内最大值',
  `min_val` double NOT NULL COMMENT '三天内最小值',
  `avg_val` double NOT NULL COMMENT '三天内平均值',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modifed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_base_monitor` (`minitor_id`,`config_type`,`quota_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机，数据库基线数据\r\n（3天内)';

/*Table structure for table `b_biz_snap` */

DROP TABLE IF EXISTS `b_biz_snap`;

CREATE TABLE `b_biz_snap` (
  `snap_id` int(11) NOT NULL,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `biz_type` smallint(6) DEFAULT NULL COMMENT '1:disk 2;tablespace',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`snap_id`),
  KEY `idx_biz_monitor` (`monitor_id`,`config_type`,`biz_type`,`gmt_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='磁盘表空间SNAP';

/*Table structure for table `b_daemon_schedule_config` */

DROP TABLE IF EXISTS `b_daemon_schedule_config`;

CREATE TABLE `b_daemon_schedule_config` (
  `id` varchar(40) NOT NULL,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `job_id` int(11) DEFAULT NULL COMMENT '指标ID',
  `job_name` varchar(100) DEFAULT NULL COMMENT '进程名',
  `job_group` varchar(100) DEFAULT NULL COMMENT '任务所属组',
  `quartz_conf` varchar(40) DEFAULT NULL COMMENT 'quartz配置',
  `poll_unit` int(11) DEFAULT NULL COMMENT '1:分钟,2:小时',
  `poll_times` smallint(6) DEFAULT NULL COMMENT '时间(比如多少分钟，多少小时)',
  `ext_col` varchar(200) DEFAULT NULL COMMENT '特殊指标配置,如不包含的目录，数据文件等',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `bean_id` varchar(100) DEFAULT NULL COMMENT '对应的BeanId,JOB中定义的BeanID，按一个规则生成，自动产生',
  `schedule_class` varchar(200) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL COMMENT '0:正常,1:停用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='守护进程定时轮询配置';

/*Table structure for table `b_db_alert_notify_config` */

DROP TABLE IF EXISTS `b_db_alert_notify_config`;

CREATE TABLE `b_db_alert_notify_config` (
  `monitor_id` varchar(40) NOT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) NOT NULL COMMENT '预警类型 见字典（sys_type）',
  `user_id` varchar(40) NOT NULL COMMENT '用户ID',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`monitor_id`,`config_type`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警人配置';

/*Table structure for table `b_db_config` */

DROP TABLE IF EXISTS `b_db_config`;

CREATE TABLE `b_db_config` (
  `db_id` varchar(40) NOT NULL,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '别名',
  `db_type` smallint(6) DEFAULT NULL COMMENT 'db类型 见字典（sys_type）',
  `db_username` varchar(20) DEFAULT NULL COMMENT '数据库用户名',
  `db_passwd` varchar(300) DEFAULT NULL COMMENT '数据库密码',
  `sid` varchar(20) DEFAULT NULL COMMENT '实例名',
  `db_name` varchar(20) DEFAULT NULL COMMENT '数据库名',
  `port` varchar(10) DEFAULT NULL COMMENT '端口',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `server_id` varchar(40) DEFAULT NULL COMMENT '采集的服务器,指定由哪个采集服务器来采集数据，达到分压的效果',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_monitor_status` smallint(6) DEFAULT NULL COMMENT '上一次心跳监测的状态0:成功，1：不成功',
  `last_heartbeat_date` datetime DEFAULT NULL COMMENT '上一次心跳监测的时间',
  `global_schedule_conf` varchar(30) DEFAULT NULL COMMENT '全局定时配置 ,quartz的定义模式，用作心跳监测',
  `slow_log_user` varchar(50) DEFAULT NULL COMMENT 'slow log 用户',
  `slow_log_pass` varchar(200) DEFAULT NULL COMMENT 'slow log 密码',
  `is_collect_slowlog` smallint(6) DEFAULT NULL COMMENT '是否开启slowlog的采集,1:开启，0：关闭',
  `warn_count` int(11) DEFAULT NULL COMMENT '告警数',
  `critical_count` int(11) DEFAULT NULL COMMENT '紧急数',
  `database_id` bigint(20) DEFAULT NULL COMMENT 'ORACLE特有，DBID',
  `instance_id` bigint(20) DEFAULT NULL COMMENT 'ORACLE特有，实例ID',
  PRIMARY KEY (`db_id`),
  KEY `idx_host` (`host_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库配置';

/*Table structure for table `b_db_relation` */

DROP TABLE IF EXISTS `b_db_relation`;

CREATE TABLE `b_db_relation` (
  `id` varchar(40) NOT NULL,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `relation_type` smallint(6) DEFAULT NULL COMMENT '关系大类型,1:主从，2：集群 ,见字典db_relation_type',
  `is_readonly` char(1) DEFAULT NULL COMMENT '是否只读,0:是,1:否',
  `is_master` char(1) DEFAULT NULL COMMENT '是否主机,0:是,1:否',
  `batch_id` varchar(40) DEFAULT NULL COMMENT '归类ID',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库关系配置';

/*Table structure for table `b_db_table_sub_classify` */

DROP TABLE IF EXISTS `b_db_table_sub_classify`;

CREATE TABLE `b_db_table_sub_classify` (
  `db_table_sub_classify_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `total_db_size` int(11) DEFAULT NULL COMMENT '总的数据库数',
  `total_db_table_size` int(11) DEFAULT NULL COMMENT '总的表数',
  `total_db_instance_size` int(11) DEFAULT NULL COMMENT '总的实例数',
  `every_db_instance_size` int(11) DEFAULT NULL COMMENT '每个实例下的数据库数',
  `every_db_table_size` int(11) DEFAULT NULL COMMENT '每个数据库下的表数',
  `db_start_index` int(11) DEFAULT NULL COMMENT '数据库从几开始',
  `table_start_index` int(11) DEFAULT NULL COMMENT '表从几开始',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0:正常,1:停用',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`db_table_sub_classify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分库分表归类定义(库级别，非实例)';

/*Table structure for table `b_db_table_sub_log` */

DROP TABLE IF EXISTS `b_db_table_sub_log`;

CREATE TABLE `b_db_table_sub_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `db_id` varchar(40) DEFAULT NULL COMMENT '同instanceid',
  `db_name` varchar(40) DEFAULT NULL COMMENT '库名',
  `table_name` varchar(40) DEFAULT NULL COMMENT '表名',
  `exec_type` tinyint(4) DEFAULT NULL COMMENT '执行类型 1:脚本 2:sql',
  `exec_script` varchar(500) DEFAULT NULL COMMENT '执行的具体脚本',
  `job_log` text COMMENT '执行日志',
  `job_flag` tinyint(4) DEFAULT NULL COMMENT '执行标示 0成功 1失败',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='执行日志';

/*Table structure for table `b_db_table_sub_task` */

DROP TABLE IF EXISTS `b_db_table_sub_task`;

CREATE TABLE `b_db_table_sub_task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `db_table_sub_classify_id` int(11) DEFAULT NULL,
  `job_name` varchar(200) DEFAULT NULL,
  `job_remark` varchar(200) DEFAULT NULL,
  `job_cron_tab` varchar(50) DEFAULT NULL COMMENT 'contab格式',
  `job_time` datetime DEFAULT NULL COMMENT '时间描述 yyyy-mm-dd hh: mm:ss',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0初始，1接受任务，2运行完毕',
  `job_type` tinyint(4) DEFAULT NULL COMMENT '任务类型 \n            1:新增字段\n            2:修改字段\n            ',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `table_name` varchar(40) DEFAULT NULL COMMENT '表名',
  `exec_script` varchar(200) DEFAULT NULL COMMENT '脚本',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务列表';

/*Table structure for table `b_db_table_subinstance_title` */

DROP TABLE IF EXISTS `b_db_table_subinstance_title`;

CREATE TABLE `b_db_table_subinstance_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_id` varchar(40) DEFAULT NULL,
  `db_table_sub_classify_id` int(11) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `passwd` varchar(200) DEFAULT NULL COMMENT '密码',
  `sort_value` tinyint(4) DEFAULT NULL COMMENT '库排序',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实例信息摘要定义';

/*Table structure for table `b_db_tablespace` */

DROP TABLE IF EXISTS `b_db_tablespace`;

CREATE TABLE `b_db_tablespace` (
  `db_id` varchar(40) NOT NULL COMMENT '数据库ID',
  `tbs_name` varchar(50) NOT NULL COMMENT '表空间名',
  `used_tbs` float DEFAULT NULL COMMENT '已用,M为单位',
  `max_tbs` float DEFAULT NULL COMMENT '表空间上限,M为单位',
  `use_percent` float DEFAULT NULL COMMENT '表空间利用率',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `threshold_type` smallint(6) DEFAULT NULL COMMENT '预警类型.0:关闭预警 1:百分比预警，2：剩余容量预警',
  `critical_quota_value` float DEFAULT NULL COMMENT '致命指标的阈值',
  `warn_quota_value` float DEFAULT NULL COMMENT '预警指标的阈值',
  `threshold_percent` float DEFAULT NULL COMMENT '预警阈值,达到一定的比例，要预警的配置',
  `threshold_minsize` float DEFAULT NULL COMMENT '大小阈值,最小的可用大小,单位是G',
  PRIMARY KEY (`db_id`,`tbs_name`),
  KEY `threshold_type` (`threshold_type`,`tbs_name`,`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表空间';

/*Table structure for table `b_db_tablespace_collect_day` */

DROP TABLE IF EXISTS `b_db_tablespace_collect_day`;

CREATE TABLE `b_db_tablespace_collect_day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `tbs_name` varchar(50) DEFAULT NULL COMMENT '表空间名',
  `used_tbs` float DEFAULT NULL COMMENT '已用,M为单位',
  `lastday_used` float DEFAULT NULL COMMENT '昨日已用,M为单位',
  `max_tbs` float DEFAULT NULL COMMENT '表空间上限,M为单位',
  `use_percent` float DEFAULT NULL COMMENT '表空间利用率',
  `gmt_created` varchar(8) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `is_completed` (`db_id`,`tbs_name`,`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='表空间历史';

/*Table structure for table `b_db_tablespace_his` */

DROP TABLE IF EXISTS `b_db_tablespace_his`;

CREATE TABLE `b_db_tablespace_his` (
  `id` varchar(40) NOT NULL,
  `snap_id` varchar(40) DEFAULT NULL,
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `tbs_name` varchar(50) DEFAULT NULL COMMENT '表空间名',
  `used_tbs` float DEFAULT NULL COMMENT '已用,M为单位',
  `max_tbs` float DEFAULT NULL COMMENT '表空间上限,M为单位',
  `use_percent` float DEFAULT NULL COMMENT '表空间利用率',
  `is_completed` char(1) DEFAULT NULL COMMENT '是否处理',
  `gmt_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`,`gmt_created`),
  KEY `is_completed` (`is_completed`,`gmt_created`,`db_id`,`tbs_name`),
  KEY `idx_snap_tbs` (`snap_id`,`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表空间历史'
/*!50100 PARTITION BY RANGE (TO_DAYS(gmt_created))
(PARTITION P201504 VALUES LESS THAN (736084) ENGINE = InnoDB,
 PARTITION P201505 VALUES LESS THAN (736115) ENGINE = InnoDB,
 PARTITION P201506 VALUES LESS THAN (736145) ENGINE = InnoDB,
 PARTITION P201507 VALUES LESS THAN (736176) ENGINE = InnoDB,
 PARTITION P201508 VALUES LESS THAN (736207) ENGINE = InnoDB,
 PARTITION P201509 VALUES LESS THAN (736237) ENGINE = InnoDB,
 PARTITION P201510 VALUES LESS THAN (736268) ENGINE = InnoDB,
 PARTITION P201511 VALUES LESS THAN (736298) ENGINE = InnoDB,
 PARTITION P201512 VALUES LESS THAN (736329) ENGINE = InnoDB,
 PARTITION P201601 VALUES LESS THAN (736360) ENGINE = InnoDB,
 PARTITION P201602 VALUES LESS THAN (736389) ENGINE = InnoDB,
 PARTITION P201603 VALUES LESS THAN (736420) ENGINE = InnoDB,
 PARTITION P201604 VALUES LESS THAN (736450) ENGINE = InnoDB,
 PARTITION P201605 VALUES LESS THAN (736481) ENGINE = InnoDB,
 PARTITION P201606 VALUES LESS THAN (736511) ENGINE = InnoDB,
 PARTITION P201607 VALUES LESS THAN (736542) ENGINE = InnoDB,
 PARTITION P201608 VALUES LESS THAN (736573) ENGINE = InnoDB,
 PARTITION P201609 VALUES LESS THAN (736603) ENGINE = InnoDB,
 PARTITION P201610 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION P201611 VALUES LESS THAN (736664) ENGINE = InnoDB,
 PARTITION P201612 VALUES LESS THAN (736695) ENGINE = InnoDB,
 PARTITION PMAX VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `b_dict_def` */

DROP TABLE IF EXISTS `b_dict_def`;

CREATE TABLE `b_dict_def` (
  `id` varchar(40) NOT NULL,
  `dict_name` varchar(20) DEFAULT NULL COMMENT '字典名',
  `dict_value` int(11) DEFAULT NULL COMMENT '枚举值',
  `remark` varchar(50) DEFAULT NULL COMMENT '描述',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典定义';

/*Table structure for table `b_dict_list` */

DROP TABLE IF EXISTS `b_dict_list`;

CREATE TABLE `b_dict_list` (
  `b_dict_id` varchar(40) NOT NULL COMMENT '字典ID',
  `dict_name` varchar(20) DEFAULT NULL COMMENT '字典名',
  `dict_remark` varchar(500) DEFAULT NULL COMMENT '字典描述',
  `status` char(1) DEFAULT NULL COMMENT '状态, 0:正常,1:删除',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`b_dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Table structure for table `b_disk_monitor` */

DROP TABLE IF EXISTS `b_disk_monitor`;

CREATE TABLE `b_disk_monitor` (
  `host_id` varchar(40) NOT NULL COMMENT '主机ID',
  `disk_name` varchar(100) NOT NULL COMMENT '磁盘名',
  `used` float DEFAULT NULL COMMENT '已用,单位是M',
  `remain` float DEFAULT NULL COMMENT '剩余,单位是M',
  `used_percent` float DEFAULT NULL COMMENT '利用率',
  `threshold_percent` float DEFAULT NULL COMMENT '预警阈值,达到一定的比例，要预警的配置',
  `threshold_minsize` float DEFAULT NULL COMMENT '大小阈值,最小的可用大小,单位是M',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `threshold_type` smallint(6) DEFAULT NULL COMMENT '预警类型.0:关闭预警 1:百分比预警，2：剩余容量预警',
  `critical_quota_value` float DEFAULT NULL COMMENT '致命指标的阈值',
  `warn_quota_value` float DEFAULT NULL COMMENT '预警指标的阈值',
  PRIMARY KEY (`host_id`,`disk_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='磁盘文件监控';

/*Table structure for table `b_disk_monitor_collect_day` */

DROP TABLE IF EXISTS `b_disk_monitor_collect_day`;

CREATE TABLE `b_disk_monitor_collect_day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `disk_name` varchar(100) DEFAULT NULL COMMENT '磁盘名',
  `used` float DEFAULT NULL COMMENT '已用,单位是M',
  `lastday_used` float DEFAULT NULL COMMENT '昨日已用,单位是M',
  `remain` float DEFAULT NULL COMMENT '剩余,单位是M',
  `used_percent` float DEFAULT NULL COMMENT '利用率',
  `gmt_created` varchar(8) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_host_name_comp_gmt` (`host_id`,`disk_name`,`gmt_created`),
  KEY `is_completed` (`gmt_created`,`id`),
  KEY `idx_snap` (`host_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='磁盘文件监控历史';

/*Table structure for table `b_disk_monitor_his` */

DROP TABLE IF EXISTS `b_disk_monitor_his`;

CREATE TABLE `b_disk_monitor_his` (
  `id` varchar(40) NOT NULL,
  `snap_id` varchar(40) DEFAULT NULL,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `disk_name` varchar(100) DEFAULT NULL COMMENT '磁盘名',
  `used` float DEFAULT NULL COMMENT '已用,单位是M',
  `remain` float DEFAULT NULL COMMENT '剩余,单位是M',
  `used_percent` float DEFAULT NULL COMMENT '利用率',
  `is_completed` char(1) DEFAULT NULL COMMENT '是否处理',
  `gmt_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`,`gmt_created`),
  KEY `idx_host_name_comp_gmt` (`host_id`,`disk_name`,`is_completed`,`gmt_created`),
  KEY `is_completed` (`is_completed`,`gmt_created`,`id`),
  KEY `idx_snap` (`snap_id`,`host_id`),
  KEY `idx_disk_day` (`gmt_created`,`host_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='磁盘文件监控历史'
/*!50100 PARTITION BY RANGE (TO_DAYS(gmt_created))
(PARTITION P201504 VALUES LESS THAN (736084) ENGINE = InnoDB,
 PARTITION P201505 VALUES LESS THAN (736115) ENGINE = InnoDB,
 PARTITION P201506 VALUES LESS THAN (736145) ENGINE = InnoDB,
 PARTITION P201507 VALUES LESS THAN (736176) ENGINE = InnoDB,
 PARTITION P201508 VALUES LESS THAN (736207) ENGINE = InnoDB,
 PARTITION P201509 VALUES LESS THAN (736237) ENGINE = InnoDB,
 PARTITION P201510 VALUES LESS THAN (736268) ENGINE = InnoDB,
 PARTITION P201511 VALUES LESS THAN (736298) ENGINE = InnoDB,
 PARTITION P201512 VALUES LESS THAN (736329) ENGINE = InnoDB,
 PARTITION P201601 VALUES LESS THAN (736360) ENGINE = InnoDB,
 PARTITION P201602 VALUES LESS THAN (736389) ENGINE = InnoDB,
 PARTITION P201603 VALUES LESS THAN (736420) ENGINE = InnoDB,
 PARTITION P201604 VALUES LESS THAN (736450) ENGINE = InnoDB,
 PARTITION P201605 VALUES LESS THAN (736481) ENGINE = InnoDB,
 PARTITION P201606 VALUES LESS THAN (736511) ENGINE = InnoDB,
 PARTITION P201607 VALUES LESS THAN (736542) ENGINE = InnoDB,
 PARTITION P201608 VALUES LESS THAN (736573) ENGINE = InnoDB,
 PARTITION P201609 VALUES LESS THAN (736603) ENGINE = InnoDB,
 PARTITION P201610 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION P201611 VALUES LESS THAN (736664) ENGINE = InnoDB,
 PARTITION P201612 VALUES LESS THAN (736695) ENGINE = InnoDB,
 PARTITION PMAX VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `b_disk_ssd_info` */

DROP TABLE IF EXISTS `b_disk_ssd_info`;

CREATE TABLE `b_disk_ssd_info` (
  `id` varchar(25) NOT NULL,
  `host_id` varchar(40) NOT NULL,
  `disk_path` varchar(100) NOT NULL,
  `device_id` int(11) NOT NULL,
  `device_type` varchar(100) NOT NULL,
  `disk_stat` varchar(1000) NOT NULL,
  `is_completed` char(1) NOT NULL,
  `gmt_created` datetime NOT NULL,
  `gmt_modifed` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `host_id` (`host_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `b_error_msg` */

DROP TABLE IF EXISTS `b_error_msg`;

CREATE TABLE `b_error_msg` (
  `id` varchar(255) NOT NULL,
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `alert_msg` text COMMENT '报错信息',
  `is_completed` char(1) DEFAULT NULL COMMENT '是否处理完成 N:未处理 Y;已完成',
  `msg_md5` varchar(40) DEFAULT NULL COMMENT '错误信息的md5值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '数据入库时间',
  `sent_phone` smallint(1) DEFAULT '0' COMMENT '是否短信发送 0发送，1不发送',
  `alert_type` smallint(2) DEFAULT NULL COMMENT '告警类型 1:数据库告警 2:mysql stat 3:oracle long time sql 4：oracle long time trans 5：oracle high level index,6:mysql slave stop, 7:oracle more trans ,8:oracle trans undo 10:mysql long sql 11:mysql result data sql 12:mysql examined sql 13:oracle awr nodata 20:keepalived进程 21:keepalived日志',
  PRIMARY KEY (`id`),
  KEY `idx_error_list` (`db_id`,`is_completed`,`gmt_created`),
  KEY `msg_md5` (`msg_md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库日志报错记录';

/*Table structure for table `b_heartbeat_monitor` */

DROP TABLE IF EXISTS `b_heartbeat_monitor`;

CREATE TABLE `b_heartbeat_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '配置类型   ,见字典（sys_type）',
  `monitor_status` smallint(6) DEFAULT NULL COMMENT '监测状态,见数据字典heartbeat_monitor_status\n            1:失败，0：成功',
  `is_completed` varchar(1) DEFAULT NULL COMMENT '是否处理完成 0:未处理，1：已处理',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_c_s` (`is_completed`,`monitor_status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='心跳监测';

/*Table structure for table `b_host_config` */

DROP TABLE IF EXISTS `b_host_config`;

CREATE TABLE `b_host_config` (
  `host_id` varchar(40) NOT NULL,
  `host_name` varchar(100) DEFAULT NULL COMMENT '主机名',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '主机别名',
  `idc_id` int(11) DEFAULT NULL COMMENT '所属机房 ,见数据字典 IDC_LIST',
  `ip_addr` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `is_host_collect` char(1) DEFAULT NULL COMMENT '是否主机采集 (Y是，N否)',
  `user_account` varchar(30) DEFAULT NULL COMMENT '系统账号',
  `user_passwd` varchar(200) DEFAULT NULL COMMENT '系统密码 DES/AES加密',
  `os_info` varchar(50) DEFAULT NULL COMMENT '内核',
  `cpu_info` varchar(50) DEFAULT NULL COMMENT 'cpu信息',
  `memory_info` varchar(50) DEFAULT NULL COMMENT '内存信息',
  `disck_info` varchar(50) DEFAULT NULL COMMENT '硬盘信息',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `global_schedule_conf` varchar(30) DEFAULT NULL COMMENT '全局定时配置 ,quartz的定义模式',
  `server_id` varchar(40) DEFAULT NULL COMMENT '采集的服务器,指定由哪个采集服务器来采集数据，达到分压的效果',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_monitor_status` smallint(6) DEFAULT NULL COMMENT '上一次心跳监测的状态',
  `last_heartbeat_date` datetime DEFAULT NULL COMMENT '上一次心跳监测的时间',
  `root_account` varchar(30) DEFAULT NULL COMMENT '操作系统root账号',
  `root_passwd` varchar(200) DEFAULT NULL COMMENT '系统密码 DES/AES加密',
  `warn_count` int(11) DEFAULT NULL COMMENT '告警数',
  `critical_count` int(11) DEFAULT NULL COMMENT '紧急数'
  PRIMARY KEY (`host_id`),
  UNIQUE KEY `ip_addr` (`ip_addr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机配置';

/*Table structure for table `b_host_dir_alert_config` */

DROP TABLE IF EXISTS `b_host_dir_alert_config`;

CREATE TABLE `b_host_dir_alert_config` (
  `id` varchar(40) NOT NULL,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `log_type` smallint(6) DEFAULT NULL COMMENT '日志类型 1: oracle alert 2:MYSQL ALERT 3:keepAlived Alert',
  `host_dir` varchar(300) DEFAULT NULL COMMENT '监控目录',
  `keyword` varchar(200) DEFAULT NULL COMMENT '关键字报警',
  `server_id` varchar(50) DEFAULT NULL COMMENT '采集服务ID',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机目录告警采集';

/*Table structure for table `b_host_quota_collect_day` */

DROP TABLE IF EXISTS `b_host_quota_collect_day`;

CREATE TABLE `b_host_quota_collect_day` (
  `id` varchar(25) NOT NULL COMMENT 'ID规则：采集服务器名(2位)+自增ID(9位)+时间搓(14位)',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `is_completed` varchar(1) DEFAULT NULL COMMENT '是否处理完成',
  `gmt_created` datetime DEFAULT NULL COMMENT '本次数据采集时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '入库时间',
  `last_quota_time` datetime DEFAULT NULL COMMENT '上次数据采集时间',
  PRIMARY KEY (`id`),
  KEY `is_completed` (`is_completed`,`last_quota_value`),
  KEY `quota_id` (`quota_id`,`gmt_created`),
  KEY `gmt_created` (`host_id`,`quota_id`,`gmt_created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机指标每日采集(最多保留2天)';

/*Table structure for table `b_host_quota_day_his` */

DROP TABLE IF EXISTS `b_host_quota_day_his`;

CREATE TABLE `b_host_quota_day_his` (
  `host_quota_his_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_collect_id` varchar(40) DEFAULT NULL COMMENT '每日采集ID',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  `gmt_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`host_quota_his_id`,`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机每日采集历史表'
/*!50100 PARTITION BY RANGE (TO_DAYS(gmt_created))
(PARTITION P201406 VALUES LESS THAN (735780) ENGINE = InnoDB,
 PARTITION P201407 VALUES LESS THAN (735811) ENGINE = InnoDB,
 PARTITION P201408 VALUES LESS THAN (735842) ENGINE = InnoDB,
 PARTITION P201409 VALUES LESS THAN (735872) ENGINE = InnoDB,
 PARTITION P201410 VALUES LESS THAN (735903) ENGINE = InnoDB,
 PARTITION P201411 VALUES LESS THAN (735933) ENGINE = InnoDB,
 PARTITION P201412 VALUES LESS THAN (735964) ENGINE = InnoDB,
 PARTITION P201501 VALUES LESS THAN (735995) ENGINE = InnoDB,
 PARTITION P201502 VALUES LESS THAN (736023) ENGINE = InnoDB,
 PARTITION P201503 VALUES LESS THAN (736054) ENGINE = InnoDB,
 PARTITION P201504 VALUES LESS THAN (736084) ENGINE = InnoDB,
 PARTITION P201505 VALUES LESS THAN (736115) ENGINE = InnoDB,
 PARTITION P201506 VALUES LESS THAN (736145) ENGINE = InnoDB,
 PARTITION P201507 VALUES LESS THAN (736176) ENGINE = InnoDB,
 PARTITION P201508 VALUES LESS THAN (736207) ENGINE = InnoDB,
 PARTITION P201509 VALUES LESS THAN (736237) ENGINE = InnoDB,
 PARTITION P201510 VALUES LESS THAN (736268) ENGINE = InnoDB,
 PARTITION P201511 VALUES LESS THAN (736298) ENGINE = InnoDB,
 PARTITION P201512 VALUES LESS THAN (736329) ENGINE = InnoDB,
 PARTITION P201601 VALUES LESS THAN (736360) ENGINE = InnoDB,
 PARTITION P201602 VALUES LESS THAN (736389) ENGINE = InnoDB,
 PARTITION P201603 VALUES LESS THAN (736420) ENGINE = InnoDB,
 PARTITION P201604 VALUES LESS THAN (736450) ENGINE = InnoDB,
 PARTITION P201605 VALUES LESS THAN (736481) ENGINE = InnoDB,
 PARTITION P201606 VALUES LESS THAN (736511) ENGINE = InnoDB,
 PARTITION P201607 VALUES LESS THAN (736542) ENGINE = InnoDB,
 PARTITION P201608 VALUES LESS THAN (736573) ENGINE = InnoDB,
 PARTITION P201609 VALUES LESS THAN (736603) ENGINE = InnoDB,
 PARTITION P201610 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION P201611 VALUES LESS THAN (736664) ENGINE = InnoDB,
 PARTITION P201612 VALUES LESS THAN (736695) ENGINE = InnoDB,
 PARTITION PMAX VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `b_host_quota_together_day` */

DROP TABLE IF EXISTS `b_host_quota_together_day`;

CREATE TABLE `b_host_quota_together_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机1天采集汇总';

/*Table structure for table `b_host_quota_together_hours` */

DROP TABLE IF EXISTS `b_host_quota_together_hours`;

CREATE TABLE `b_host_quota_together_hours` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机4个小时采集汇总';

/*Table structure for table `b_host_quota_together_month` */

DROP TABLE IF EXISTS `b_host_quota_together_month`;

CREATE TABLE `b_host_quota_together_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机1个月采集汇总';

/*Table structure for table `b_host_quota_together_week` */

DROP TABLE IF EXISTS `b_host_quota_together_week`;

CREATE TABLE `b_host_quota_together_week` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机1周采集汇总';

/*Table structure for table `b_identify_gen` */

DROP TABLE IF EXISTS `b_identify_gen`;

CREATE TABLE `b_identify_gen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) DEFAULT NULL COMMENT '表名',
  `current_value` int(11) DEFAULT NULL COMMENT '当前值',
  `max_len` int(11) DEFAULT NULL COMMENT '最大长度',
  `prefix` varchar(10) DEFAULT NULL COMMENT '前缀',
  `rule` varchar(200) DEFAULT NULL COMMENT '生成规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='ID生成记录表';

/*Table structure for table `b_java_job_def` */

DROP TABLE IF EXISTS `b_java_job_def`;

CREATE TABLE `b_java_job_def` (
  `id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL COMMENT '名称',
  `pak_class` varchar(200) DEFAULT NULL COMMENT '包体',
  `remark` varchar(400) DEFAULT NULL COMMENT '描述',
  `sys_type` int(11) DEFAULT NULL COMMENT '类型',
  `bean_prefix` varchar(40) DEFAULT NULL COMMENT 'bean前缀',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Java的JOB定义';

/*Table structure for table `b_lost_task_job` */

DROP TABLE IF EXISTS `b_lost_task_job`;

CREATE TABLE `b_lost_task_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deamon_id` varchar(40) DEFAULT NULL,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) NOT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `job_id` int(11) DEFAULT NULL COMMENT '指标ID',
  `job_name` varchar(100) DEFAULT NULL COMMENT '进程名',
  `job_group` varchar(100) DEFAULT NULL COMMENT '任务所属组',
  `quartz_conf` varchar(40) DEFAULT NULL COMMENT 'quartz配置',
  `poll_unit` int(11) DEFAULT NULL COMMENT '轮询单位,见数据字典poll_unit',
  `poll_times` smallint(6) DEFAULT NULL COMMENT '轮训间隔,分钟为单位',
  `ext_col` varchar(200) DEFAULT NULL COMMENT '特殊指标配置,如不包含的目录，数据文件等',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `bean_id` varchar(100) DEFAULT NULL COMMENT '对应的BeanId,JOB中定义的BeanID，按一个规则生成，自动产生',
  `schedule_class` varchar(200) DEFAULT NULL,
  `is_regedit_task` smallint(6) DEFAULT NULL COMMENT '是否重新注册任务 0:未重新注册，1：已经完成注册,2:重新注册失败',
  `is_warn_completed` smallint(6) DEFAULT NULL COMMENT '是否发送预警,0：未发送，1：已发送',
  `last_time` datetime DEFAULT NULL COMMENT '上次运行时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='失败任务监测';

/*Table structure for table `b_mail_server_config` */

DROP TABLE IF EXISTS `b_mail_server_config`;

CREATE TABLE `b_mail_server_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mailserver` varchar(100) DEFAULT NULL COMMENT '邮件服务地址',
  `mailsender` varchar(100) DEFAULT NULL COMMENT '发件人',
  `mailpasswod` varchar(100) DEFAULT NULL COMMENT '发件人密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='邮件服务器配置';

/*Table structure for table `b_monitor_alert_record` */

DROP TABLE IF EXISTS `b_monitor_alert_record`;

CREATE TABLE `b_monitor_alert_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '主机ID/数据库ID',
  `sys_type` smallint(6) DEFAULT NULL COMMENT '系统类型 见字典（sys_type）',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(200) DEFAULT NULL COMMENT '指标名',
  `notice_level` smallint(6) DEFAULT NULL COMMENT '预警级别，1：warn、2：critical',
  `monitor_type` smallint(6) DEFAULT NULL COMMENT '分类. 1：心跳检查、2：指标、3：磁盘、4：表空间、5：告警日志',
  `content` text COMMENT '内容',
  `signmd5` varchar(200) DEFAULT NULL COMMENT '签名md5',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_mss` (`monitor_id`,`sys_type`,`signmd5`),
  KEY `idx_gmt_type` (`gmt_modifed`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='告警归类';

/*Table structure for table `b_monitor_changes` */

DROP TABLE IF EXISTS `b_monitor_changes`;

CREATE TABLE `b_monitor_changes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '监控ID',
  `sys_type` smallint(6) DEFAULT NULL COMMENT '系统类型,见数据字典sys_type',
  `change_from` smallint(6) DEFAULT NULL COMMENT '异动来源 1:告警 2:基线异常 3:跟前面时间段比异常',
  `changes_type` smallint(6) DEFAULT NULL COMMENT '异动类型 1：心跳检查、2：指标、3：磁盘、4：表空间、5：告警日志',
  `notice_level` smallint(6) DEFAULT NULL COMMENT '预警级别，1：warn、2：critical',
  `content` varchar(400) DEFAULT NULL COMMENT '描述',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `b_monitor_changes_snap` */

DROP TABLE IF EXISTS `b_monitor_changes_snap`;

CREATE TABLE `b_monitor_changes_snap` (
  `analyse_type` smallint(6) NOT NULL COMMENT '业务类型',
  `monitor_id` varchar(40) NOT NULL COMMENT '监控ID',
  `monitor_type` smallint(6) NOT NULL COMMENT '监控类型',
  `last_analyse_created` datetime DEFAULT NULL COMMENT '上一次分析时间',
  PRIMARY KEY (`analyse_type`,`monitor_id`,`monitor_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `b_monitor_regedit_privs_ip` */

DROP TABLE IF EXISTS `b_monitor_regedit_privs_ip`;

CREATE TABLE `b_monitor_regedit_privs_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ipaddr` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `b_mysql_quota_collect_day` */

DROP TABLE IF EXISTS `b_mysql_quota_collect_day`;

CREATE TABLE `b_mysql_quota_collect_day` (
  `id` varchar(25) NOT NULL COMMENT 'ID规则：采集服务器名(2位)+自增ID(9位)+时间搓(14位)',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `is_completed` varchar(1) DEFAULT NULL COMMENT '是否处理完成',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '数据入库时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`),
  KEY `idx_db_quota_gmt` (`db_id`),
  KEY `host_id` (`host_id`,`db_id`,`quota_id`),
  KEY `idx_quota` (`quota_id`,`db_id`,`is_completed`,`last_quota_value`),
  KEY `idx_gmt_created` (`gmt_created`),
  KEY `idx_complete_gmt` (`db_id`,`is_completed`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MYSQL指标每日采集(最多保留2天)';

/*Table structure for table `b_mysql_quota_day_his` */

DROP TABLE IF EXISTS `b_mysql_quota_day_his`;

CREATE TABLE `b_mysql_quota_day_his` (
  `mysql_quota_his_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_collect_id` varchar(40) DEFAULT NULL COMMENT '每日采集ID',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` float DEFAULT NULL COMMENT '采集值',
  `last_quota_value` float DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`mysql_quota_his_id`,`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='MySQL主机每日采集历史表'
/*!50100 PARTITION BY RANGE (TO_DAYS(gmt_created))
(PARTITION P201406 VALUES LESS THAN (735780) ENGINE = InnoDB,
 PARTITION P201407 VALUES LESS THAN (735811) ENGINE = InnoDB,
 PARTITION P201408 VALUES LESS THAN (735842) ENGINE = InnoDB,
 PARTITION P201409 VALUES LESS THAN (735872) ENGINE = InnoDB,
 PARTITION P201410 VALUES LESS THAN (735903) ENGINE = InnoDB,
 PARTITION P201411 VALUES LESS THAN (735933) ENGINE = InnoDB,
 PARTITION P201412 VALUES LESS THAN (735964) ENGINE = InnoDB,
 PARTITION P201501 VALUES LESS THAN (735995) ENGINE = InnoDB,
 PARTITION P201502 VALUES LESS THAN (736023) ENGINE = InnoDB,
 PARTITION P201503 VALUES LESS THAN (736054) ENGINE = InnoDB,
 PARTITION P201504 VALUES LESS THAN (736084) ENGINE = InnoDB,
 PARTITION P201505 VALUES LESS THAN (736115) ENGINE = InnoDB,
 PARTITION P201506 VALUES LESS THAN (736145) ENGINE = InnoDB,
 PARTITION P201507 VALUES LESS THAN (736176) ENGINE = InnoDB,
 PARTITION P201508 VALUES LESS THAN (736207) ENGINE = InnoDB,
 PARTITION P201509 VALUES LESS THAN (736237) ENGINE = InnoDB,
 PARTITION P201510 VALUES LESS THAN (736268) ENGINE = InnoDB,
 PARTITION P201511 VALUES LESS THAN (736298) ENGINE = InnoDB,
 PARTITION P201512 VALUES LESS THAN (736329) ENGINE = InnoDB,
 PARTITION P201601 VALUES LESS THAN (736360) ENGINE = InnoDB,
 PARTITION P201602 VALUES LESS THAN (736389) ENGINE = InnoDB,
 PARTITION P201603 VALUES LESS THAN (736420) ENGINE = InnoDB,
 PARTITION P201604 VALUES LESS THAN (736450) ENGINE = InnoDB,
 PARTITION P201605 VALUES LESS THAN (736481) ENGINE = InnoDB,
 PARTITION P201606 VALUES LESS THAN (736511) ENGINE = InnoDB,
 PARTITION P201607 VALUES LESS THAN (736542) ENGINE = InnoDB,
 PARTITION P201608 VALUES LESS THAN (736573) ENGINE = InnoDB,
 PARTITION P201609 VALUES LESS THAN (736603) ENGINE = InnoDB,
 PARTITION P201610 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION P201611 VALUES LESS THAN (736664) ENGINE = InnoDB,
 PARTITION P201612 VALUES LESS THAN (736695) ENGINE = InnoDB,
 PARTITION PMAX VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `b_mysql_quota_together_day` */

DROP TABLE IF EXISTS `b_mysql_quota_together_day`;

CREATE TABLE `b_mysql_quota_together_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='MySQL1天采集汇总';

/*Table structure for table `b_mysql_quota_together_hours` */

DROP TABLE IF EXISTS `b_mysql_quota_together_hours`;

CREATE TABLE `b_mysql_quota_together_hours` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='MySQL4个小时采集汇总';

/*Table structure for table `b_mysql_quota_together_month` */

DROP TABLE IF EXISTS `b_mysql_quota_together_month`;

CREATE TABLE `b_mysql_quota_together_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='MySQL1个月采集汇总';

/*Table structure for table `b_mysql_quota_together_week` */

DROP TABLE IF EXISTS `b_mysql_quota_together_week`;

CREATE TABLE `b_mysql_quota_together_week` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='MySQL1周采集汇总';

/*Table structure for table `b_mysql_replication` */

DROP TABLE IF EXISTS `b_mysql_replication`;

CREATE TABLE `b_mysql_replication` (
  `db_id` varchar(40) NOT NULL,
  `db_ip` varchar(20) DEFAULT NULL,
  `startup_time` timestamp NULL DEFAULT NULL COMMENT '数据库启动时间',
  `run_time` int(11) DEFAULT NULL COMMENT '数据库运行时间（单位:days）',
  `version` varchar(20) DEFAULT NULL COMMENT '数据库版本号',
  `is_master` tinyint(2) DEFAULT '0' COMMENT '是否充当主库角色（1:是,0:否）',
  `is_slave` tinyint(2) unsigned DEFAULT '0' COMMENT '是否充当备库角色（1:是,0:否）',
  `read_only` varchar(10) DEFAULT NULL COMMENT '是否开启只读（ON代表主读，OFF代表主写）',
  `master_server` varchar(30) DEFAULT NULL COMMENT '主库IP',
  `master_port` varchar(20) DEFAULT NULL COMMENT '主库端口',
  `slave_io_run` varchar(20) DEFAULT NULL COMMENT 'IO复制线程是否启动（YES/NO）',
  `slave_sql_run` varchar(20) DEFAULT NULL COMMENT 'SQL复制线程是否启动（YES/NO）',
  `delay` varchar(20) DEFAULT NULL COMMENT '是否存在复制延迟',
  `current_binlog_file` varchar(30) DEFAULT NULL COMMENT 'SQL复制线程正在执行的主库二进制日志文件的名称',
  `current_binlog_pos` varchar(30) DEFAULT NULL COMMENT 'SQL复制线程执行的主库二进制日志文件上一个时间的位置',
  `master_binlog_file` varchar(30) DEFAULT NULL COMMENT 'I/O复制线程当前正在读取的主库二进制日志文件的名称',
  `master_binlog_pos` varchar(30) DEFAULT NULL COMMENT 'I/O复制线程已经读取当前二进制日志的位置',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维护数据库主从关系表';

/*Table structure for table `b_mysql_stat_extends` */

DROP TABLE IF EXISTS `b_mysql_stat_extends`;

CREATE TABLE `b_mysql_stat_extends` (
  `db_id` varchar(40) NOT NULL,
  `threads_connects` mediumint(9) DEFAULT NULL COMMENT '已建立的连接数 THREADS_CONNECTED 18',
  `active_connects` mediumint(9) DEFAULT NULL COMMENT '活动的连接数  THREADS_RUNNING 19',
  `qps` float DEFAULT NULL COMMENT 'qps : 61',
  `tps` float DEFAULT NULL COMMENT 'tps Com_insert 60,Com_update 62,Com_delete 63,Com_replace 64',
  `bytes_received` float DEFAULT NULL COMMENT '流量接收',
  `bytes_sent` float DEFAULT NULL COMMENT '流量发送',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='MySQL扩展';

/*Table structure for table `b_net_sense_monitor` */

DROP TABLE IF EXISTS `b_net_sense_monitor`;

CREATE TABLE `b_net_sense_monitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `error_name` varchar(200) DEFAULT NULL COMMENT '错误内容',
  `domain_name` varchar(200) DEFAULT NULL COMMENT '登陆来源',
  `connetct_time` varchar(200) DEFAULT NULL COMMENT '连接时间（ms）',
  `delay` varchar(200) DEFAULT NULL COMMENT '请求延时（ms）',
  `file_size` varchar(200) DEFAULT NULL COMMENT '文件大小（KB）',
  `return_code` varchar(50) DEFAULT NULL COMMENT '返回码',
  `status` varchar(50) DEFAULT NULL COMMENT '返回错误状态',
  `network` varchar(50) DEFAULT NULL COMMENT '网络类型',
  `time` varchar(50) DEFAULT NULL COMMENT '时间',
  `dns` varchar(100) DEFAULT NULL COMMENT 'dns信息',
  `dev_name` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `dev_area` varchar(100) DEFAULT NULL COMMENT '设备地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `b_net_sense_monitor_lastid` */

DROP TABLE IF EXISTS `b_net_sense_monitor_lastid`;

CREATE TABLE `b_net_sense_monitor_lastid` (
  `lastid` bigint(20) NOT NULL,
  PRIMARY KEY (`lastid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `b_oracle_quota_collect_day` */

DROP TABLE IF EXISTS `b_oracle_quota_collect_day`;

CREATE TABLE `b_oracle_quota_collect_day` (
  `id` varchar(25) NOT NULL COMMENT 'ID规则：采集服务器名(2位)+自增ID(9位)+时间搓(14位)',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `snap_id` bigint(20) DEFAULT NULL COMMENT '快照ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `is_completed` varchar(1) DEFAULT NULL COMMENT '是否处理完成',
  `last_quota_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `snap_id` (`snap_id`,`db_id`),
  KEY `host_id` (`quota_id`,`host_id`,`db_id`,`snap_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ORACLE指标每日采集(最多保留2天)';

/*Table structure for table `b_oracle_quota_day_his` */

DROP TABLE IF EXISTS `b_oracle_quota_day_his`;

CREATE TABLE `b_oracle_quota_day_his` (
  `oracle_quota_his_id` int(11) NOT NULL AUTO_INCREMENT,
  `quota_collect_id` varchar(40) DEFAULT NULL COMMENT '每日采集ID',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`oracle_quota_his_id`,`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Oracle每日采集历史表'
/*!50100 PARTITION BY RANGE (TO_DAYS(gmt_created))
(PARTITION P201406 VALUES LESS THAN (735780) ENGINE = InnoDB,
 PARTITION P201407 VALUES LESS THAN (735811) ENGINE = InnoDB,
 PARTITION P201408 VALUES LESS THAN (735842) ENGINE = InnoDB,
 PARTITION P201409 VALUES LESS THAN (735872) ENGINE = InnoDB,
 PARTITION P201410 VALUES LESS THAN (735903) ENGINE = InnoDB,
 PARTITION P201411 VALUES LESS THAN (735933) ENGINE = InnoDB,
 PARTITION P201412 VALUES LESS THAN (735964) ENGINE = InnoDB,
 PARTITION P201501 VALUES LESS THAN (735995) ENGINE = InnoDB,
 PARTITION P201502 VALUES LESS THAN (736023) ENGINE = InnoDB,
 PARTITION P201503 VALUES LESS THAN (736054) ENGINE = InnoDB,
 PARTITION P201504 VALUES LESS THAN (736084) ENGINE = InnoDB,
 PARTITION P201505 VALUES LESS THAN (736115) ENGINE = InnoDB,
 PARTITION P201506 VALUES LESS THAN (736145) ENGINE = InnoDB,
 PARTITION P201507 VALUES LESS THAN (736176) ENGINE = InnoDB,
 PARTITION P201508 VALUES LESS THAN (736207) ENGINE = InnoDB,
 PARTITION P201509 VALUES LESS THAN (736237) ENGINE = InnoDB,
 PARTITION P201510 VALUES LESS THAN (736268) ENGINE = InnoDB,
 PARTITION P201511 VALUES LESS THAN (736298) ENGINE = InnoDB,
 PARTITION P201512 VALUES LESS THAN (736329) ENGINE = InnoDB,
 PARTITION P201601 VALUES LESS THAN (736360) ENGINE = InnoDB,
 PARTITION P201602 VALUES LESS THAN (736389) ENGINE = InnoDB,
 PARTITION P201603 VALUES LESS THAN (736420) ENGINE = InnoDB,
 PARTITION P201604 VALUES LESS THAN (736450) ENGINE = InnoDB,
 PARTITION P201605 VALUES LESS THAN (736481) ENGINE = InnoDB,
 PARTITION P201606 VALUES LESS THAN (736511) ENGINE = InnoDB,
 PARTITION P201607 VALUES LESS THAN (736542) ENGINE = InnoDB,
 PARTITION P201608 VALUES LESS THAN (736573) ENGINE = InnoDB,
 PARTITION P201609 VALUES LESS THAN (736603) ENGINE = InnoDB,
 PARTITION P201610 VALUES LESS THAN (736634) ENGINE = InnoDB,
 PARTITION P201611 VALUES LESS THAN (736664) ENGINE = InnoDB,
 PARTITION P201612 VALUES LESS THAN (736695) ENGINE = InnoDB,
 PARTITION PMAX VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `b_oracle_quota_together_day` */

DROP TABLE IF EXISTS `b_oracle_quota_together_day`;

CREATE TABLE `b_oracle_quota_together_day` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Oracle1天采集汇总';

/*Table structure for table `b_oracle_quota_together_hours` */

DROP TABLE IF EXISTS `b_oracle_quota_together_hours`;

CREATE TABLE `b_oracle_quota_together_hours` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Oracle4个小时采集汇总';

/*Table structure for table `b_oracle_quota_together_month` */

DROP TABLE IF EXISTS `b_oracle_quota_together_month`;

CREATE TABLE `b_oracle_quota_together_month` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Oracle1个月采集汇总';

/*Table structure for table `b_oracle_quota_together_week` */

DROP TABLE IF EXISTS `b_oracle_quota_together_week`;

CREATE TABLE `b_oracle_quota_together_week` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `quota_value` double DEFAULT NULL COMMENT '采集值',
  `last_quota_value` double DEFAULT NULL COMMENT '上一次采集值',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间,采样时间点',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_quota_date` datetime DEFAULT NULL COMMENT '上次采样时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Oracle1周采集汇总';

/*Table structure for table `b_quota_daemon_relation` */

DROP TABLE IF EXISTS `b_quota_daemon_relation`;

CREATE TABLE `b_quota_daemon_relation` (
  `quota_catagory_id` varchar(40) NOT NULL COMMENT '指标ID,指标大类',
  `job_id` int(11) NOT NULL COMMENT 'Job名称',
  `catagory_class` varchar(40) NOT NULL COMMENT '大指标',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`quota_catagory_id`,`job_id`,`catagory_class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指标与守护进程的关系';

/*Table structure for table `b_quota_model` */

DROP TABLE IF EXISTS `b_quota_model`;

CREATE TABLE `b_quota_model` (
  `id` varchar(40) NOT NULL,
  `quota_name` varchar(50) DEFAULT NULL COMMENT '指标名',
  `remark` varchar(500) DEFAULT NULL COMMENT '指标描述',
  `quota_metric` varchar(20) DEFAULT NULL COMMENT '指标单位',
  `sys_category` smallint(6) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `quota_category` smallint(6) DEFAULT NULL COMMENT '指标归类 见字典（quota_catagory）',
  `quota_owner` varchar(40) DEFAULT NULL COMMENT '指标从属',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `is_phone_sent` smallint(1) DEFAULT NULL COMMENT '是否短信通知.0:是，1：否',
  `cal_type` smallint(6) DEFAULT NULL COMMENT '计算类型，0：无需出图 1:直接比大小，大的预警 2:计算上一次的差值，3：直接比大小，小的预警',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='指标汇总';

/*Table structure for table `b_quota_schedule_config` */

DROP TABLE IF EXISTS `b_quota_schedule_config`;

CREATE TABLE `b_quota_schedule_config` (
  `id` varchar(40) NOT NULL,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '数据库ID/主机ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `catagory_quota_id` int(11) DEFAULT NULL COMMENT '指标ID，存放的是指标的大类，并非指标ID',
  `quota_owner` varchar(40) DEFAULT NULL COMMENT '指标从属',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='监控配置的指标';

/*Table structure for table `b_server_reg` */

DROP TABLE IF EXISTS `b_server_reg`;

CREATE TABLE `b_server_reg` (
  `id` varchar(40) NOT NULL,
  `server_name` varchar(50) DEFAULT NULL COMMENT '注册名',
  `ip` varchar(20) DEFAULT NULL COMMENT '采集服务器IP',
  `port` int(11) DEFAULT NULL COMMENT '发布的端口',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`server_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采集服务器注册列表';

/*Table structure for table `b_statistics_record_log` */

DROP TABLE IF EXISTS `b_statistics_record_log`;

CREATE TABLE `b_statistics_record_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编码',
  `sys_type` smallint(6) DEFAULT NULL COMMENT '系统类型',
  `stat_type` smallint(6) DEFAULT NULL COMMENT '统计类型,0: 历史迁移,1: 4小时任务,2:日任务,3:周任务,4:月任务',
  `work_time` datetime DEFAULT NULL COMMENT '处理时间',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='统计记录日志';

/*Table structure for table `b_task_job_log` */

DROP TABLE IF EXISTS `b_task_job_log`;

CREATE TABLE `b_task_job_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_type` int(11) DEFAULT NULL COMMENT '系统类型,见数据字典sys_type',
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '监控ID',
  `process_id` varchar(50) DEFAULT NULL COMMENT '进程ID',
  `daemon_type` varchar(50) DEFAULT NULL COMMENT '进程类型,见数据字典 daemon_type',
  `server_id` varchar(40) DEFAULT NULL COMMENT '采集服务编码',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_monitor` (`monitor_id`,`daemon_type`),
  KEY `idx_job_srv_date` (`server_id`,`daemon_type`,`gmt_created`),
  KEY `idx_process_time` (`daemon_type`,`gmt_created`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='任务日志';

/*Table structure for table `b_task_process` */

DROP TABLE IF EXISTS `b_task_process`;

CREATE TABLE `b_task_process` (
  `id` varchar(40) NOT NULL,
  `sys_type` int(11) DEFAULT NULL COMMENT '系统类型,见数据字典sys_type',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `process_id` varchar(50) DEFAULT NULL COMMENT '进程ID',
  `daemon_type` int(11) DEFAULT NULL COMMENT '进程类型,见数据字典 daemon_type',
  `is_heartbeat` tinyint(4) DEFAULT NULL COMMENT '是否心跳,0是，1否',
  `server_id` varchar(40) DEFAULT NULL COMMENT '采集服务编码',
  `is_complete` char(1) DEFAULT NULL COMMENT '是否完成 ，0:未完成，1:完成',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务注册';

/*Table structure for table `b_threshold` */

DROP TABLE IF EXISTS `b_threshold`;

CREATE TABLE `b_threshold` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_id` varchar(40) DEFAULT NULL COMMENT '主机ID/数据库ID',
  `quota_id` varchar(40) DEFAULT NULL COMMENT '指标ID',
  `config_type` smallint(6) DEFAULT NULL COMMENT '配置类型   ,见字典（sys_type）',
  `quota_value` float DEFAULT NULL COMMENT '指标值',
  `quota_metric` varchar(20) DEFAULT NULL COMMENT '指标单位',
  `last_quota_value` float DEFAULT NULL COMMENT '上次的设置值',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `threshold_type` smallint(6) DEFAULT NULL COMMENT '1:直接比大小，大的预警  2:计算上一次的差值，3：直接比大小，小的预警',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `warn_quota_value` float DEFAULT NULL COMMENT '预警指标的阈值',
  `critical_quota_value` float DEFAULT NULL COMMENT '致命指标的阈值',
  `last_warn_quota_value` float DEFAULT NULL COMMENT '上次的预警设置的阈值',
  `last_critical_quota_value` float DEFAULT NULL COMMENT '上次的致命设置的阈值',
  PRIMARY KEY (`id`),
  KEY `idx_monitor_quota` (`monitor_id`,`quota_id`,`config_type`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='预警阀值配置，为空是默认的配置';

/*Table structure for table `b_top_sql_everyday` */

DROP TABLE IF EXISTS `b_top_sql_everyday`;

CREATE TABLE `b_top_sql_everyday` (
  `id` varchar(40) NOT NULL COMMENT 'topSql 采集服务器名(2位)+自增ID(9位)+时间搓(14位)',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `sqltext` text,
  `exam` text COMMENT '范本',
  `sys_type` int(11) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日topsql,(最多保留两日)';

/*Table structure for table `b_top_sql_his` */

DROP TABLE IF EXISTS `b_top_sql_his`;

CREATE TABLE `b_top_sql_his` (
  `id` varchar(40) NOT NULL COMMENT 'topSql 采集服务器名(2位)+自增ID(9位)+时间搓(14位)',
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `sqltext` text,
  `sys_type` int(11) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `exam` text COMMENT '范本',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日topsql历史';

/*Table structure for table `b_top_sql_review` */

DROP TABLE IF EXISTS `b_top_sql_review`;

CREATE TABLE `b_top_sql_review` (
  `id` varchar(40) NOT NULL,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `db_id` varchar(40) DEFAULT NULL COMMENT '数据库ID',
  `sqltext` text,
  `sys_type` int(11) DEFAULT NULL COMMENT '系统类型 ,见字典（sys_type）',
  `execute_counts` int(11) DEFAULT NULL COMMENT '执行次数',
  `max_used_times` float DEFAULT NULL COMMENT '最高执行时间 ，毫秒为单位',
  `min_used_times` float DEFAULT NULL COMMENT '最小执行时间 ，毫秒为单位',
  `avg_times` float DEFAULT NULL COMMENT '平均执行时间 ，毫秒为单位',
  `exam` text COMMENT '范本',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='topsql 汇总,相同的SQL 会归类,1个小时一次';

/*Table structure for table `crh_lq` */

DROP TABLE IF EXISTS `crh_lq`;

CREATE TABLE `crh_lq` (
  `ts_min` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最早执行时间',
  `ts_max` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最晚执行时间',
  `CHECKSUM` bigint(20) unsigned NOT NULL COMMENT '当前SQL汇总去重校验值',
  `ts_cnt` float DEFAULT NULL COMMENT '总执行次数',
  `query_time_pct_95` float DEFAULT NULL COMMENT 'SQL执行消耗平均时间',
  `Rows_sent_pct_95` float DEFAULT NULL,
  `Rows_examined_pct_95` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `crh_mysql` */

DROP TABLE IF EXISTS `crh_mysql`;

CREATE TABLE `crh_mysql` (
  `dbid_max` varchar(11) DEFAULT NULL COMMENT '数据库ID，同表b_db_config的db_id列',
  `user_max` varchar(20) DEFAULT NULL COMMENT '应用程序携带过来的用户名',
  `ipaddr_max` varchar(20) DEFAULT NULL COMMENT '应用程序携带过来的IP地址',
  `db_max` varchar(64) DEFAULT NULL COMMENT '所访问的数据库名称',
  `checksum` bigint(20) unsigned NOT NULL COMMENT '当前SQL汇总去重校验值',
  `sample` text NOT NULL COMMENT '当前运行的SQL语句',
  `ts_min` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最早执行时间',
  `ts_max` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最晚执行时间',
  `ts_cnt` float DEFAULT NULL COMMENT '总执行次数',
  `Query_time_sum` float DEFAULT NULL COMMENT 'SQL执行消耗总时间',
  `Query_time_min` float DEFAULT NULL COMMENT 'SQL执行消耗最小时间',
  `Query_time_max` float DEFAULT NULL COMMENT 'SQL执行消耗最大时间',
  `Query_time_pct_95` float DEFAULT NULL COMMENT 'SQL执行消耗平均时间',
  `Query_time_stddev` float DEFAULT NULL,
  `Query_time_median` float DEFAULT NULL,
  `Lock_time_sum` float DEFAULT NULL,
  `Lock_time_min` float DEFAULT NULL,
  `Lock_time_max` float DEFAULT NULL,
  `Lock_time_pct_95` float DEFAULT NULL,
  `Lock_time_stddev` float DEFAULT NULL,
  `Lock_time_median` float DEFAULT NULL,
  `Rows_sent_sum` float DEFAULT NULL,
  `Rows_sent_min` float DEFAULT NULL,
  `Rows_sent_max` float DEFAULT NULL,
  `Rows_sent_pct_95` float DEFAULT NULL,
  `Rows_sent_stddev` float DEFAULT NULL,
  `Rows_sent_median` float DEFAULT NULL,
  `Rows_examined_sum` float DEFAULT NULL,
  `Rows_examined_min` float DEFAULT NULL,
  `Rows_examined_max` float DEFAULT NULL,
  `Rows_examined_pct_95` float DEFAULT NULL,
  `Rows_examined_stddev` float DEFAULT NULL,
  `Rows_examined_median` float DEFAULT NULL,
  `Rows_affected_sum` float DEFAULT NULL,
  `Rows_affected_min` float DEFAULT NULL,
  `Rows_affected_max` float DEFAULT NULL,
  `Rows_affected_pct_95` float DEFAULT NULL,
  `Rows_affected_stddev` float DEFAULT NULL,
  `Rows_affected_median` float DEFAULT NULL,
  `Rows_read_sum` float DEFAULT NULL,
  `Rows_read_min` float DEFAULT NULL,
  `Rows_read_max` float DEFAULT NULL,
  `Rows_read_pct_95` float DEFAULT NULL,
  `Rows_read_stddev` float DEFAULT NULL,
  `Rows_read_median` float DEFAULT NULL,
  `Merge_passes_sum` float DEFAULT NULL,
  `Merge_passes_min` float DEFAULT NULL,
  `Merge_passes_max` float DEFAULT NULL,
  `Merge_passes_pct_95` float DEFAULT NULL,
  `Merge_passes_stddev` float DEFAULT NULL,
  `Merge_passes_median` float DEFAULT NULL,
  `InnoDB_IO_r_ops_min` float DEFAULT NULL,
  `InnoDB_IO_r_ops_max` float DEFAULT NULL,
  `InnoDB_IO_r_ops_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_ops_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_ops_median` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_min` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_max` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_median` float DEFAULT NULL,
  `InnoDB_IO_r_wait_min` float DEFAULT NULL,
  `InnoDB_IO_r_wait_max` float DEFAULT NULL,
  `InnoDB_IO_r_wait_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_wait_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_wait_median` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_min` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_max` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_pct_95` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_stddev` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_median` float DEFAULT NULL,
  `InnoDB_queue_wait_min` float DEFAULT NULL,
  `InnoDB_queue_wait_max` float DEFAULT NULL,
  `InnoDB_queue_wait_pct_95` float DEFAULT NULL,
  `InnoDB_queue_wait_stddev` float DEFAULT NULL,
  `InnoDB_queue_wait_median` float DEFAULT NULL,
  `InnoDB_pages_distinct_min` float DEFAULT NULL,
  `InnoDB_pages_distinct_max` float DEFAULT NULL,
  `InnoDB_pages_distinct_pct_95` float DEFAULT NULL,
  `InnoDB_pages_distinct_stddev` float DEFAULT NULL,
  `InnoDB_pages_distinct_median` float DEFAULT NULL,
  `QC_Hit_cnt` float DEFAULT NULL,
  `QC_Hit_sum` float DEFAULT NULL,
  `Full_scan_cnt` float DEFAULT NULL,
  `Full_scan_sum` float DEFAULT NULL,
  `Full_join_cnt` float DEFAULT NULL,
  `Full_join_sum` float DEFAULT NULL,
  `Tmp_table_cnt` float DEFAULT NULL,
  `Tmp_table_sum` float DEFAULT NULL,
  `Tmp_table_on_disk_cnt` float DEFAULT NULL,
  `Tmp_table_on_disk_sum` float DEFAULT NULL,
  `Filesort_cnt` float DEFAULT NULL,
  `Filesort_sum` float DEFAULT NULL,
  `Filesort_on_disk_cnt` float DEFAULT NULL,
  `Filesort_on_disk_sum` float DEFAULT NULL,
  KEY `crh_i001` (`checksum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `crh_sqltext` */

DROP TABLE IF EXISTS `crh_sqltext`;

CREATE TABLE `crh_sqltext` (
  `SQL_ID` varchar(13) NOT NULL,
  `SQL_TEXT` longtext,
  FULLTEXT KEY `SQL_TEXT` (`SQL_TEXT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `crh_tab_sql` */

DROP TABLE IF EXISTS `crh_tab_sql`;

CREATE TABLE `crh_tab_sql` (
  `tname` varchar(35) DEFAULT NULL,
  `sql_id` varchar(13) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_active_sess_history` */

DROP TABLE IF EXISTS `dba_hist_active_sess_history`;

CREATE TABLE `dba_hist_active_sess_history` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `SAMPLE_ID` bigint(20) DEFAULT NULL COMMENT '取样ID',
  `SAMPLE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '取样时间',
  `SESSION_ID` bigint(20) NOT NULL COMMENT '会话标识符，对应v$session.sid',
  `SESSION_SERIAL#` bigint(20) NOT NULL COMMENT '会话序列号 (用于唯一标识一个会话的对象); 对应 V$SESSION.SERIAL#',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT 'Oracle用户标识符; 映射到 V$SESSION.USER#',
  `SQL_ID` varchar(13) DEFAULT NULL COMMENT '会话在取样时执行的 SQL 语句的 SQL 标识符',
  `SQL_CHILD_NUMBER` bigint(20) DEFAULT NULL COMMENT '取样时会话执行的sql语句的Child number ',
  `SQL_PLAN_HASH_VALUE` bigint(20) DEFAULT NULL COMMENT 'sql游标计划的数值表示形式。这所有会话样本的信息可能不可用。v$session不包含此信息。',
  `SQL_OPCODE` bigint(20) DEFAULT NULL COMMENT 'sql语句的类型，对应v$session.command。具体类型请查阅V$SESSION',
  `PLSQL_ENTRY_OBJECT_ID` bigint(20) DEFAULT NULL,
  `PLSQL_ENTRY_SUBPROGRAM_ID` bigint(20) DEFAULT NULL,
  `PLSQL_OBJECT_ID` bigint(20) DEFAULT NULL,
  `PLSQL_SUBPROGRAM_ID` bigint(20) DEFAULT NULL,
  `SERVICE_HASH` bigint(20) DEFAULT NULL COMMENT 'service的hash值，对应v$active_services.name_hash',
  `SESSION_TYPE` varchar(10) DEFAULT NULL COMMENT '会话类型：FOREGROUND/BACKGROUND 前台/后台',
  `SESSION_STATE` varchar(7) DEFAULT NULL COMMENT '会话状态：WAITING/ON CPU 等待/正在运行',
  `QC_SESSION_ID` bigint(20) DEFAULT NULL COMMENT '查询协调器的会话ID。只有当会话是并行查询slave进程时，这一列才有用；对于其他会话，该列的值为0.',
  `QC_INSTANCE_ID` bigint(20) DEFAULT NULL COMMENT '查询协调器的实例ID。只有当会话是并行查询slave进程时，这一列才有用；对于其他会话，该列的值为0.',
  `BLOCKING_SESSION` bigint(20) DEFAULT NULL COMMENT '阻塞会话的会话标识符。只有当等待队列或者等待"buffer busy"时，这一列才显示。对应v$session.blocking_session',
  `BLOCKING_SESSION_STATUS` varchar(11) DEFAULT NULL COMMENT '阻塞会话的状态:VALID/NO HOLDER/GLOBAL/NOT IN WAIT/UNKNOWN',
  `BLOCKING_SESSION_SERIAL#` bigint(20) DEFAULT NULL COMMENT '阻塞会话的序列号',
  `EVENT` varchar(64) DEFAULT NULL COMMENT '如果session_state=WAITING，则显示等待事件；如果SESSION_STATE=ON CPU，则显示NULL。',
  `EVENT_ID` bigint(20) DEFAULT NULL COMMENT '等待事件或资源的ID',
  `SEQ#` bigint(20) DEFAULT NULL COMMENT '唯一标识这个等待事件的序列号',
  `P1TEXT` varchar(64) DEFAULT NULL COMMENT '第一个附加参数的含义',
  `P1` bigint(20) DEFAULT NULL COMMENT '第一个附加参数',
  `P2TEXT` varchar(64) DEFAULT NULL COMMENT '第二个附加参数的含义',
  `P2` bigint(20) DEFAULT NULL COMMENT '第二个附加参数',
  `P3TEXT` varchar(64) DEFAULT NULL COMMENT '第三个附加参数的含义',
  `P3` bigint(20) DEFAULT NULL COMMENT '第三个附加参数',
  `WAIT_CLASS` varchar(64) DEFAULT NULL COMMENT '等待类型。对应v$session.wait_class',
  `WAIT_CLASS_ID` bigint(20) DEFAULT NULL COMMENT '等待类型的ID。对应v$session.wait_class_id',
  `WAIT_TIME` bigint(20) DEFAULT NULL COMMENT '取样时会话正在等待，值为0；取样时会话正在执行，显示会话最后等待事件的总等待时间；不论WAIT_TIME是否为0，根据SESSION_STATE判断会话是正在等待',
  `TIME_WAITED` bigint(20) DEFAULT NULL COMMENT '如果SESSION_STATE=WAITING,该值为实际等待时间。如果等待时间超过1秒，真正的等待时间会计算在最后一次取样中。任何时候，都没有最新取样的这个信息',
  `CURRENT_OBJ#` bigint(20) DEFAULT NULL COMMENT '对象ID。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_obj#。',
  `CURRENT_FILE#` bigint(20) DEFAULT NULL COMMENT '块所在的文件的number。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_file#。',
  `CURRENT_BLOCK#` bigint(20) DEFAULT NULL COMMENT '块的ID。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_block#。',
  `PROGRAM` varchar(64) DEFAULT NULL COMMENT '操作系统程序的名称',
  `MODULE` varchar(48) DEFAULT NULL COMMENT '执行的模块名称，由DBMS_APPLICATION_INFO.SET_MODULE设置',
  `CLIENT_ID` varchar(64) DEFAULT NULL COMMENT '客户端ID。对应v$session.client_identifier',
  `FLAGS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`SESSION_ID`,`SESSION_SERIAL#`,`SAMPLE_TIME`),
  KEY `SNAP_ID` (`SNAP_ID`,`DBID`),
  KEY `DBID` (`DBID`),
  KEY `ix001` (`SAMPLE_TIME`,`SESSION_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_active_sess_history_his` */

DROP TABLE IF EXISTS `dba_hist_active_sess_history_his`;

CREATE TABLE `dba_hist_active_sess_history_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `SAMPLE_ID` bigint(20) DEFAULT NULL COMMENT '取样ID',
  `SAMPLE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '取样时间',
  `SESSION_ID` bigint(20) NOT NULL COMMENT '会话标识符，对应v$session.sid',
  `SESSION_SERIAL#` bigint(20) NOT NULL COMMENT '会话序列号 (用于唯一标识一个会话的对象); 对应 V$SESSION.SERIAL#',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT 'Oracle用户标识符; 映射到 V$SESSION.USER#',
  `SQL_ID` varchar(13) DEFAULT NULL COMMENT '会话在取样时执行的 SQL 语句的 SQL 标识符',
  `SQL_CHILD_NUMBER` bigint(20) DEFAULT NULL COMMENT '取样时会话执行的sql语句的Child number ',
  `SQL_PLAN_HASH_VALUE` bigint(20) DEFAULT NULL COMMENT 'sql游标计划的数值表示形式。这所有会话样本的信息可能不可用。v$session不包含此信息。',
  `SQL_OPCODE` bigint(20) DEFAULT NULL COMMENT 'sql语句的类型，对应v$session.command。具体类型请查阅V$SESSION',
  `PLSQL_ENTRY_OBJECT_ID` bigint(20) DEFAULT NULL,
  `PLSQL_ENTRY_SUBPROGRAM_ID` bigint(20) DEFAULT NULL,
  `PLSQL_OBJECT_ID` bigint(20) DEFAULT NULL,
  `PLSQL_SUBPROGRAM_ID` bigint(20) DEFAULT NULL,
  `SERVICE_HASH` bigint(20) DEFAULT NULL COMMENT 'service的hash值，对应v$active_services.name_hash',
  `SESSION_TYPE` varchar(10) DEFAULT NULL COMMENT '会话类型：FOREGROUND/BACKGROUND 前台/后台',
  `SESSION_STATE` varchar(7) DEFAULT NULL COMMENT '会话状态：WAITING/ON CPU 等待/正在运行',
  `QC_SESSION_ID` bigint(20) DEFAULT NULL COMMENT '查询协调器的会话ID。只有当会话是并行查询slave进程时，这一列才有用；对于其他会话，该列的值为0.',
  `QC_INSTANCE_ID` bigint(20) DEFAULT NULL COMMENT '查询协调器的实例ID。只有当会话是并行查询slave进程时，这一列才有用；对于其他会话，该列的值为0.',
  `BLOCKING_SESSION` bigint(20) DEFAULT NULL COMMENT '阻塞会话的会话标识符。只有当等待队列或者等待"buffer busy"时，这一列才显示。对应v$session.blocking_session',
  `BLOCKING_SESSION_STATUS` varchar(11) DEFAULT NULL COMMENT '阻塞会话的状态:VALID/NO HOLDER/GLOBAL/NOT IN WAIT/UNKNOWN',
  `BLOCKING_SESSION_SERIAL#` bigint(20) DEFAULT NULL COMMENT '阻塞会话的序列号',
  `EVENT` varchar(64) DEFAULT NULL COMMENT '如果session_state=WAITING，则显示等待事件；如果SESSION_STATE=ON CPU，则显示NULL。',
  `EVENT_ID` bigint(20) DEFAULT NULL COMMENT '等待事件或资源的ID',
  `SEQ#` bigint(20) DEFAULT NULL COMMENT '唯一标识这个等待事件的序列号',
  `P1TEXT` varchar(64) DEFAULT NULL COMMENT '第一个附加参数的含义',
  `P1` bigint(20) DEFAULT NULL COMMENT '第一个附加参数',
  `P2TEXT` varchar(64) DEFAULT NULL COMMENT '第二个附加参数的含义',
  `P2` bigint(20) DEFAULT NULL COMMENT '第二个附加参数',
  `P3TEXT` varchar(64) DEFAULT NULL COMMENT '第三个附加参数的含义',
  `P3` bigint(20) DEFAULT NULL COMMENT '第三个附加参数',
  `WAIT_CLASS` varchar(64) DEFAULT NULL COMMENT '等待类型。对应v$session.wait_class',
  `WAIT_CLASS_ID` bigint(20) DEFAULT NULL COMMENT '等待类型的ID。对应v$session.wait_class_id',
  `WAIT_TIME` bigint(20) DEFAULT NULL COMMENT '取样时会话正在等待，值为0；取样时会话正在执行，显示会话最后等待事件的总等待时间；不论WAIT_TIME是否为0，根据SESSION_STATE判断会话是正在等待',
  `TIME_WAITED` bigint(20) DEFAULT NULL COMMENT '如果SESSION_STATE=WAITING,该值为实际等待时间。如果等待时间超过1秒，真正的等待时间会计算在最后一次取样中。任何时候，都没有最新取样的这个信息',
  `CURRENT_OBJ#` bigint(20) DEFAULT NULL COMMENT '对象ID。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_obj#。',
  `CURRENT_FILE#` bigint(20) DEFAULT NULL COMMENT '块所在的文件的number。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_file#。',
  `CURRENT_BLOCK#` bigint(20) DEFAULT NULL COMMENT '块的ID。只有当等待事件为“Application, Cluster, Concurrency, and User I/O”时才有效。对应v$session.row_wait_block#。',
  `PROGRAM` varchar(64) DEFAULT NULL COMMENT '操作系统程序的名称',
  `MODULE` varchar(48) DEFAULT NULL COMMENT '执行的模块名称，由DBMS_APPLICATION_INFO.SET_MODULE设置',
  `CLIENT_ID` varchar(64) DEFAULT NULL COMMENT '客户端ID。对应v$session.client_identifier',
  `FLAGS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`SESSION_ID`,`SESSION_SERIAL#`,`SAMPLE_TIME`),
  KEY `SNAP_ID` (`SNAP_ID`,`DBID`),
  KEY `DBID` (`DBID`),
  KEY `ix001` (`SAMPLE_TIME`,`SESSION_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_buffer_pool_stat` */

DROP TABLE IF EXISTS `dba_hist_buffer_pool_stat`;

CREATE TABLE `dba_hist_buffer_pool_stat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `BLOCK_SIZE` bigint(20) DEFAULT NULL,
  `SET_MSIZE` bigint(20) DEFAULT NULL,
  `CNUM_REPL` bigint(20) DEFAULT NULL,
  `CNUM_WRITE` bigint(20) DEFAULT NULL,
  `CNUM_SET` bigint(20) DEFAULT NULL,
  `BUF_GOT` bigint(20) DEFAULT NULL,
  `SUM_WRITE` bigint(20) DEFAULT NULL,
  `SUM_SCAN` bigint(20) DEFAULT NULL,
  `FREE_BUFFER_WAIT` bigint(20) DEFAULT NULL,
  `WRITE_COMPLETE_WAIT` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAIT` bigint(20) DEFAULT NULL,
  `FREE_BUFFER_INSPECTED` bigint(20) DEFAULT NULL,
  `DIRTY_BUFFERS_INSPECTED` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGE` bigint(20) DEFAULT NULL,
  `DB_BLOCK_GETS` bigint(20) DEFAULT NULL,
  `CONSISTENT_GETS` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`ID`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_buffer_pool_stat_his` */

DROP TABLE IF EXISTS `dba_hist_buffer_pool_stat_his`;

CREATE TABLE `dba_hist_buffer_pool_stat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `BLOCK_SIZE` bigint(20) DEFAULT NULL,
  `SET_MSIZE` bigint(20) DEFAULT NULL,
  `CNUM_REPL` bigint(20) DEFAULT NULL,
  `CNUM_WRITE` bigint(20) DEFAULT NULL,
  `CNUM_SET` bigint(20) DEFAULT NULL,
  `BUF_GOT` bigint(20) DEFAULT NULL,
  `SUM_WRITE` bigint(20) DEFAULT NULL,
  `SUM_SCAN` bigint(20) DEFAULT NULL,
  `FREE_BUFFER_WAIT` bigint(20) DEFAULT NULL,
  `WRITE_COMPLETE_WAIT` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAIT` bigint(20) DEFAULT NULL,
  `FREE_BUFFER_INSPECTED` bigint(20) DEFAULT NULL,
  `DIRTY_BUFFERS_INSPECTED` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGE` bigint(20) DEFAULT NULL,
  `DB_BLOCK_GETS` bigint(20) DEFAULT NULL,
  `CONSISTENT_GETS` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`ID`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_database_instance` */

DROP TABLE IF EXISTS `dba_hist_database_instance`;

CREATE TABLE `dba_hist_database_instance` (
  `DBID` bigint(20) NOT NULL COMMENT '数据库ID',
  `INSTANCE_NUMBER` bigint(20) NOT NULL COMMENT '实例ID',
  `STARTUP_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '启动时间',
  `PARALLEL` varchar(3) NOT NULL COMMENT '是否集群',
  `VERSION` varchar(17) NOT NULL COMMENT '版本',
  `DB_NAME` varchar(9) NOT NULL COMMENT '数据库sid',
  `INSTANCE_NAME` varchar(16) NOT NULL COMMENT '实例名',
  `HOST_NAME` varchar(64) NOT NULL COMMENT '主机名',
  `LAST_ASH_SAMPLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`INSTANCE_NUMBER`,`STARTUP_TIME`),
  KEY `DB_NAME` (`DB_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_osstat` */

DROP TABLE IF EXISTS `dba_hist_osstat`;

CREATE TABLE `dba_hist_osstat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `STAT_ID` bigint(20) NOT NULL,
  `STAT_NAME` varchar(64) DEFAULT NULL,
  `VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`STAT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_osstat_his` */

DROP TABLE IF EXISTS `dba_hist_osstat_his`;

CREATE TABLE `dba_hist_osstat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `STAT_ID` bigint(20) NOT NULL,
  `STAT_NAME` varchar(64) DEFAULT NULL,
  `VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`STAT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_seg_stat` */

DROP TABLE IF EXISTS `dba_hist_seg_stat`;

CREATE TABLE `dba_hist_seg_stat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `TS#` bigint(20) DEFAULT NULL,
  `OBJ#` bigint(20) NOT NULL,
  `DATAOBJ#` bigint(20) NOT NULL,
  `LOGICAL_READS_TOTAL` bigint(20) DEFAULT NULL,
  `LOGICAL_READS_DELTA` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGES_TOTAL` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGES_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DIRECT_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DIRECT_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DIRECT_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DIRECT_DELTA` bigint(20) DEFAULT NULL,
  `ITL_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `ITL_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `ROW_LOCK_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `ROW_LOCK_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_SERVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_SERVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_SERVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_SERVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_BUFFER_BUSY_TOTAL` bigint(20) DEFAULT NULL,
  `GC_BUFFER_BUSY_DELTA` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_RECEIVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_RECEIVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_RECEIVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_RECEIVED_DELTA` bigint(20) DEFAULT NULL,
  `SPACE_USED_TOTAL` bigint(20) DEFAULT NULL,
  `SPACE_USED_DELTA` bigint(20) DEFAULT NULL,
  `SPACE_ALLOCATED_TOTAL` bigint(20) DEFAULT NULL,
  `SPACE_ALLOCATED_DELTA` bigint(20) DEFAULT NULL,
  `TABLE_SCANS_TOTAL` bigint(20) DEFAULT NULL,
  `TABLE_SCANS_DELTA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`OBJ#`,`DATAOBJ#`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_seg_stat_his` */

DROP TABLE IF EXISTS `dba_hist_seg_stat_his`;

CREATE TABLE `dba_hist_seg_stat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `TS#` bigint(20) DEFAULT NULL,
  `OBJ#` bigint(20) NOT NULL,
  `DATAOBJ#` bigint(20) NOT NULL,
  `LOGICAL_READS_TOTAL` bigint(20) DEFAULT NULL,
  `LOGICAL_READS_DELTA` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `BUFFER_BUSY_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGES_TOTAL` bigint(20) DEFAULT NULL,
  `DB_BLOCK_CHANGES_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DIRECT_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_READS_DIRECT_DELTA` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DIRECT_TOTAL` bigint(20) DEFAULT NULL,
  `PHYSICAL_WRITES_DIRECT_DELTA` bigint(20) DEFAULT NULL,
  `ITL_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `ITL_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `ROW_LOCK_WAITS_TOTAL` bigint(20) DEFAULT NULL,
  `ROW_LOCK_WAITS_DELTA` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_SERVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_SERVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_SERVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_SERVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_BUFFER_BUSY_TOTAL` bigint(20) DEFAULT NULL,
  `GC_BUFFER_BUSY_DELTA` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_RECEIVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CR_BLOCKS_RECEIVED_DELTA` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_RECEIVED_TOTAL` bigint(20) DEFAULT NULL,
  `GC_CU_BLOCKS_RECEIVED_DELTA` bigint(20) DEFAULT NULL,
  `SPACE_USED_TOTAL` bigint(20) DEFAULT NULL,
  `SPACE_USED_DELTA` bigint(20) DEFAULT NULL,
  `SPACE_ALLOCATED_TOTAL` bigint(20) DEFAULT NULL,
  `SPACE_ALLOCATED_DELTA` bigint(20) DEFAULT NULL,
  `TABLE_SCANS_TOTAL` bigint(20) DEFAULT NULL,
  `TABLE_SCANS_DELTA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`OBJ#`,`DATAOBJ#`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_seg_stat_obj` */

DROP TABLE IF EXISTS `dba_hist_seg_stat_obj`;

CREATE TABLE `dba_hist_seg_stat_obj` (
  `DBID` bigint(20) NOT NULL,
  `TS#` bigint(20) NOT NULL,
  `OBJ#` bigint(20) NOT NULL,
  `DATAOBJ#` bigint(20) NOT NULL,
  `OWNER` varchar(30) DEFAULT NULL,
  `OBJECT_NAME` varchar(30) DEFAULT NULL,
  `SUBOBJECT_NAME` varchar(30) DEFAULT NULL,
  `OBJECT_TYPE` varchar(18) DEFAULT NULL,
  `TABLESPACE_NAME` varchar(30) DEFAULT NULL,
  `PARTITION_TYPE` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`OBJ#`,`DATAOBJ#`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sgastat` */

DROP TABLE IF EXISTS `dba_hist_sgastat`;

CREATE TABLE `dba_hist_sgastat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` int(11) NOT NULL,
  `NAME` varchar(64) NOT NULL COMMENT '区域名称',
  `POOL` varchar(12) DEFAULT NULL COMMENT 'pool名称',
  `BYTES` bigint(20) DEFAULT NULL COMMENT '使用字节数',
  UNIQUE KEY `SNAP_ID` (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`NAME`,`POOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sgastat_his` */

DROP TABLE IF EXISTS `dba_hist_sgastat_his`;

CREATE TABLE `dba_hist_sgastat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` int(11) NOT NULL,
  `NAME` varchar(64) NOT NULL COMMENT '区域名称',
  `POOL` varchar(12) DEFAULT NULL COMMENT 'pool名称',
  `BYTES` bigint(20) DEFAULT NULL COMMENT '使用字节数',
  UNIQUE KEY `SNAP_ID` (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`NAME`,`POOL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_snapshot` */

DROP TABLE IF EXISTS `dba_hist_snapshot`;

CREATE TABLE `dba_hist_snapshot` (
  `SNAP_ID` bigint(20) NOT NULL COMMENT '快照ID',
  `DBID` bigint(20) NOT NULL COMMENT '数据库ID',
  `INSTANCE_NUMBER` bigint(20) NOT NULL COMMENT '实例ID',
  `STARTUP_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `BEGIN_INTERVAL_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `END_INTERVAL_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `FLUSH_ELAPSED` varchar(20) DEFAULT NULL,
  `SNAP_LEVEL` tinyint(4) DEFAULT NULL,
  `ERROR_COUNT` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_snapshot_his` */

DROP TABLE IF EXISTS `dba_hist_snapshot_his`;

CREATE TABLE `dba_hist_snapshot_his` (
  `SNAP_ID` bigint(20) NOT NULL COMMENT '快照ID',
  `DBID` bigint(20) NOT NULL COMMENT '数据库ID',
  `INSTANCE_NUMBER` bigint(20) NOT NULL COMMENT '实例ID',
  `STARTUP_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `BEGIN_INTERVAL_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间',
  `END_INTERVAL_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `FLUSH_ELAPSED` varchar(20) DEFAULT NULL,
  `SNAP_LEVEL` tinyint(4) DEFAULT NULL,
  `ERROR_COUNT` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_snapshot_orastat` */

DROP TABLE IF EXISTS `dba_hist_snapshot_orastat`;

CREATE TABLE `dba_hist_snapshot_orastat` (
  `snapid` bigint(20) NOT NULL,
  `dbid` bigint(20) NOT NULL,
  `instance_number` int(11) NOT NULL,
  PRIMARY KEY (`snapid`,`dbid`,`instance_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sqlstat` */

DROP TABLE IF EXISTS `dba_hist_sqlstat`;

CREATE TABLE `dba_hist_sqlstat` (
  `SNAP_ID` bigint(20) NOT NULL COMMENT '采样ID',
  `DBID` bigint(20) NOT NULL COMMENT '数据库ID',
  `INSTANCE_NUMBER` bigint(20) NOT NULL COMMENT '实例ID',
  `SQL_ID` varchar(13) NOT NULL COMMENT 'sql',
  `PLAN_HASH_VALUE` bigint(20) NOT NULL COMMENT '执行计划值',
  `OPTIMIZER_COST` bigint(20) DEFAULT NULL,
  `OPTIMIZER_MODE` varchar(10) DEFAULT NULL,
  `OPTIMIZER_ENV_HASH_VALUE` bigint(20) DEFAULT NULL,
  `SHARABLE_MEM` bigint(20) DEFAULT NULL,
  `LOADED_VERSIONS` bigint(20) DEFAULT NULL,
  `VERSION_COUNT` bigint(20) DEFAULT NULL,
  `MODULE` varchar(64) DEFAULT NULL COMMENT '客户端信息:模块',
  `SQL_PROFILE` varchar(64) DEFAULT NULL,
  `PARSING_SCHEMA_ID` bigint(20) DEFAULT NULL,
  `PARSING_SCHEMA_NAME` varchar(30) DEFAULT NULL,
  `FETCHES_TOTAL` bigint(20) DEFAULT NULL,
  `FETCHES_DELTA` bigint(20) DEFAULT NULL,
  `END_OF_FETCH_COUNT_TOTAL` bigint(20) DEFAULT NULL,
  `END_OF_FETCH_COUNT_DELTA` bigint(20) DEFAULT NULL,
  `SORTS_TOTAL` bigint(20) DEFAULT NULL,
  `SORTS_DELTA` bigint(20) DEFAULT NULL COMMENT '排序次数',
  `EXECUTIONS_TOTAL` bigint(20) DEFAULT NULL,
  `EXECUTIONS_DELTA` bigint(20) DEFAULT NULL COMMENT '执行次数',
  `PX_SERVERS_EXECS_TOTAL` bigint(20) DEFAULT NULL,
  `PX_SERVERS_EXECS_DELTA` bigint(20) DEFAULT NULL,
  `LOADS_TOTAL` bigint(20) DEFAULT NULL,
  `LOADS_DELTA` bigint(20) DEFAULT NULL,
  `INVALIDATIONS_TOTAL` bigint(20) DEFAULT NULL,
  `INVALIDATIONS_DELTA` bigint(20) DEFAULT NULL,
  `PARSE_CALLS_TOTAL` bigint(20) DEFAULT NULL,
  `PARSE_CALLS_DELTA` bigint(20) DEFAULT NULL COMMENT '解析次数',
  `DISK_READS_TOTAL` bigint(20) DEFAULT NULL,
  `DISK_READS_DELTA` bigint(20) DEFAULT NULL COMMENT '物理读次数',
  `BUFFER_GETS_TOTAL` bigint(20) DEFAULT NULL,
  `BUFFER_GETS_DELTA` bigint(20) DEFAULT NULL COMMENT '逻辑读次数',
  `ROWS_PROCESSED_TOTAL` bigint(20) DEFAULT NULL,
  `ROWS_PROCESSED_DELTA` bigint(20) DEFAULT NULL COMMENT '排序行数',
  `CPU_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `CPU_TIME_DELTA` bigint(20) DEFAULT NULL COMMENT 'cpu使用时间包括parsing/executing/fetching 单位是微秒（百万分之一秒）',
  `ELAPSED_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `ELAPSED_TIME_DELTA` bigint(20) DEFAULT NULL COMMENT '总时间（百万分之一秒）',
  `IOWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `IOWAIT_DELTA` bigint(20) DEFAULT NULL COMMENT ' I/O wait time (in microseconds)',
  `CLWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `CLWAIT_DELTA` bigint(20) DEFAULT NULL COMMENT 'cluster wait time (in microseconds)',
  `APWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `APWAIT_DELTA` bigint(20) DEFAULT NULL COMMENT '应用程序相关等待时间(增量:本快照的累积量)',
  `CCWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `CCWAIT_DELTA` bigint(20) DEFAULT NULL COMMENT '并发等待concurrency wait time (in microseconds)',
  `DIRECT_WRITES_TOTAL` bigint(20) DEFAULT NULL,
  `DIRECT_WRITES_DELTA` bigint(20) DEFAULT NULL,
  `PLSEXEC_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `PLSEXEC_TIME_DELTA` bigint(20) DEFAULT NULL,
  `JAVEXEC_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `JAVEXEC_TIME_DELTA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`SQL_ID`,`PLAN_HASH_VALUE`),
  KEY `DBID` (`DBID`),
  KEY `idx_plan_hash` (`PLAN_HASH_VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sqlstat_his` */

DROP TABLE IF EXISTS `dba_hist_sqlstat_his`;

CREATE TABLE `dba_hist_sqlstat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `SQL_ID` varchar(13) NOT NULL,
  `PLAN_HASH_VALUE` bigint(20) NOT NULL,
  `OPTIMIZER_COST` bigint(20) DEFAULT NULL,
  `OPTIMIZER_MODE` varchar(10) DEFAULT NULL,
  `OPTIMIZER_ENV_HASH_VALUE` bigint(20) DEFAULT NULL,
  `SHARABLE_MEM` bigint(20) DEFAULT NULL,
  `LOADED_VERSIONS` bigint(20) DEFAULT NULL,
  `VERSION_COUNT` bigint(20) DEFAULT NULL,
  `MODULE` varchar(64) DEFAULT NULL,
  `SQL_PROFILE` varchar(64) DEFAULT NULL,
  `PARSING_SCHEMA_ID` bigint(20) DEFAULT NULL,
  `PARSING_SCHEMA_NAME` varchar(30) DEFAULT NULL,
  `FETCHES_TOTAL` bigint(20) DEFAULT NULL,
  `FETCHES_DELTA` bigint(20) DEFAULT NULL,
  `END_OF_FETCH_COUNT_TOTAL` bigint(20) DEFAULT NULL,
  `END_OF_FETCH_COUNT_DELTA` bigint(20) DEFAULT NULL,
  `SORTS_TOTAL` bigint(20) DEFAULT NULL,
  `SORTS_DELTA` bigint(20) DEFAULT NULL,
  `EXECUTIONS_TOTAL` bigint(20) DEFAULT NULL,
  `EXECUTIONS_DELTA` bigint(20) DEFAULT NULL,
  `PX_SERVERS_EXECS_TOTAL` bigint(20) DEFAULT NULL,
  `PX_SERVERS_EXECS_DELTA` bigint(20) DEFAULT NULL,
  `LOADS_TOTAL` bigint(20) DEFAULT NULL,
  `LOADS_DELTA` bigint(20) DEFAULT NULL,
  `INVALIDATIONS_TOTAL` bigint(20) DEFAULT NULL,
  `INVALIDATIONS_DELTA` bigint(20) DEFAULT NULL,
  `PARSE_CALLS_TOTAL` bigint(20) DEFAULT NULL,
  `PARSE_CALLS_DELTA` bigint(20) DEFAULT NULL,
  `DISK_READS_TOTAL` bigint(20) DEFAULT NULL,
  `DISK_READS_DELTA` bigint(20) DEFAULT NULL,
  `BUFFER_GETS_TOTAL` bigint(20) DEFAULT NULL,
  `BUFFER_GETS_DELTA` bigint(20) DEFAULT NULL,
  `ROWS_PROCESSED_TOTAL` bigint(20) DEFAULT NULL,
  `ROWS_PROCESSED_DELTA` bigint(20) DEFAULT NULL,
  `CPU_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `CPU_TIME_DELTA` bigint(20) DEFAULT NULL,
  `ELAPSED_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `ELAPSED_TIME_DELTA` bigint(20) DEFAULT NULL,
  `IOWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `IOWAIT_DELTA` bigint(20) DEFAULT NULL,
  `CLWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `CLWAIT_DELTA` bigint(20) DEFAULT NULL,
  `APWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `APWAIT_DELTA` bigint(20) DEFAULT NULL,
  `CCWAIT_TOTAL` bigint(20) DEFAULT NULL,
  `CCWAIT_DELTA` bigint(20) DEFAULT NULL,
  `DIRECT_WRITES_TOTAL` bigint(20) DEFAULT NULL,
  `DIRECT_WRITES_DELTA` bigint(20) DEFAULT NULL,
  `PLSEXEC_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `PLSEXEC_TIME_DELTA` bigint(20) DEFAULT NULL,
  `JAVEXEC_TIME_TOTAL` bigint(20) DEFAULT NULL,
  `JAVEXEC_TIME_DELTA` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`SQL_ID`,`PLAN_HASH_VALUE`),
  KEY `DBID` (`DBID`),
  KEY `idx_plan_hash` (`PLAN_HASH_VALUE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sqltext` */

DROP TABLE IF EXISTS `dba_hist_sqltext`;

CREATE TABLE `dba_hist_sqltext` (
  `DBID` bigint(20) NOT NULL,
  `SQL_ID` varchar(13) NOT NULL,
  `SQL_TEXT` longtext,
  `COMMAND_TYPE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`SQL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sysmetric_summary` */

DROP TABLE IF EXISTS `dba_hist_sysmetric_summary`;

CREATE TABLE `dba_hist_sysmetric_summary` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `BEGIN_TIME` datetime NOT NULL COMMENT '开始时间',
  `END_TIME` datetime NOT NULL COMMENT '结束时间',
  `INTSIZE` bigint(20) NOT NULL COMMENT '区间的大小(百分之一秒)',
  `GROUP_ID` bigint(20) NOT NULL,
  `METRIC_ID` bigint(20) NOT NULL COMMENT '度量ID',
  `METRIC_NAME` varchar(64) NOT NULL COMMENT '度量名称',
  `METRIC_UNIT` varchar(64) NOT NULL COMMENT '度量单位',
  `NUM_INTERVAL` double NOT NULL,
  `MINVAL` double NOT NULL COMMENT '最小值',
  `MAXVAL` double NOT NULL COMMENT '最大值',
  `AVERAGE` double NOT NULL COMMENT '平均值',
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`METRIC_ID`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统信息表';

/*Table structure for table `dba_hist_sysmetric_summary_his` */

DROP TABLE IF EXISTS `dba_hist_sysmetric_summary_his`;

CREATE TABLE `dba_hist_sysmetric_summary_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `BEGIN_TIME` datetime NOT NULL COMMENT '开始时间',
  `END_TIME` datetime NOT NULL COMMENT '结束时间',
  `INTSIZE` bigint(20) NOT NULL COMMENT '区间的大小(百分之一秒)',
  `GROUP_ID` bigint(20) NOT NULL,
  `METRIC_ID` bigint(20) NOT NULL COMMENT '度量ID',
  `METRIC_NAME` varchar(64) NOT NULL COMMENT '度量名称',
  `METRIC_UNIT` varchar(64) NOT NULL COMMENT '度量单位',
  `NUM_INTERVAL` double NOT NULL,
  `MINVAL` double NOT NULL COMMENT '最小值',
  `MAXVAL` double NOT NULL COMMENT '最大值',
  `AVERAGE` double NOT NULL COMMENT '平均值',
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`METRIC_ID`),
  KEY `DBID` (`DBID`,`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统信息表';

/*Table structure for table `dba_hist_sysstat` */

DROP TABLE IF EXISTS `dba_hist_sysstat`;

CREATE TABLE `dba_hist_sysstat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `STAT_ID` bigint(20) NOT NULL,
  `STAT_NAME` varchar(64) DEFAULT NULL,
  `VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`STAT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_sysstat_his` */

DROP TABLE IF EXISTS `dba_hist_sysstat_his`;

CREATE TABLE `dba_hist_sysstat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `STAT_ID` bigint(20) NOT NULL,
  `STAT_NAME` varchar(64) DEFAULT NULL,
  `VALUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`STAT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_system_event` */

DROP TABLE IF EXISTS `dba_hist_system_event`;

CREATE TABLE `dba_hist_system_event` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `EVENT_ID` bigint(20) NOT NULL,
  `EVENT_NAME` varchar(64) DEFAULT NULL,
  `WAIT_CLASS_ID` bigint(20) DEFAULT NULL,
  `WAIT_CLASS` varchar(64) DEFAULT NULL,
  `TOTAL_WAITS` bigint(20) DEFAULT NULL COMMENT '等待的次数',
  `TOTAL_TIMEOUTS` bigint(20) DEFAULT NULL COMMENT '超时的次数',
  `TIME_WAITED_MICRO` bigint(20) DEFAULT NULL COMMENT '等待的时间,单位是百万分之一秒',
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`EVENT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_system_event_his` */

DROP TABLE IF EXISTS `dba_hist_system_event_his`;

CREATE TABLE `dba_hist_system_event_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `EVENT_ID` bigint(20) NOT NULL,
  `EVENT_NAME` varchar(64) DEFAULT NULL,
  `WAIT_CLASS_ID` bigint(20) DEFAULT NULL,
  `WAIT_CLASS` varchar(64) DEFAULT NULL,
  `TOTAL_WAITS` bigint(20) DEFAULT NULL,
  `TOTAL_TIMEOUTS` bigint(20) DEFAULT NULL,
  `TIME_WAITED_MICRO` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SNAP_ID`,`DBID`,`INSTANCE_NUMBER`,`EVENT_ID`),
  KEY `DBID` (`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_temp_sqltext` */

DROP TABLE IF EXISTS `dba_hist_temp_sqltext`;

CREATE TABLE `dba_hist_temp_sqltext` (
  `dbid` bigint(20) DEFAULT NULL,
  `sql_id` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_undostat` */

DROP TABLE IF EXISTS `dba_hist_undostat`;

CREATE TABLE `dba_hist_undostat` (
  `BEGIN_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `SNAP_ID` bigint(20) NOT NULL,
  `UNDOTSN` bigint(20) DEFAULT NULL,
  `UNDOBLKS` bigint(20) DEFAULT NULL,
  `TXNCOUNT` bigint(20) DEFAULT NULL,
  `MAXQUERYLEN` bigint(20) DEFAULT NULL,
  `MAXQUERYSQLID` varchar(13) DEFAULT NULL,
  `MAXCONCURRENCY` bigint(20) DEFAULT NULL,
  `UNXPSTEALCNT` bigint(20) DEFAULT NULL,
  `UNXPBLKRELCNT` bigint(20) DEFAULT NULL,
  `UNXPBLKREUCNT` bigint(20) DEFAULT NULL,
  `EXPSTEALCNT` bigint(20) DEFAULT NULL,
  `EXPBLKRELCNT` bigint(20) DEFAULT NULL,
  `EXPBLKREUCNT` bigint(20) DEFAULT NULL,
  `SSOLDERRCNT` bigint(20) DEFAULT NULL,
  `NOSPACEERRCNT` bigint(20) DEFAULT NULL,
  `ACTIVEBLKS` bigint(20) DEFAULT NULL,
  `UNEXPIREDBLKS` bigint(20) DEFAULT NULL,
  `EXPIREDBLKS` bigint(20) DEFAULT NULL,
  `TUNED_UNDORETENTION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`SNAP_ID`,`INSTANCE_NUMBER`,`BEGIN_TIME`),
  KEY `SNAP_ID` (`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_undostat_his` */

DROP TABLE IF EXISTS `dba_hist_undostat_his`;

CREATE TABLE `dba_hist_undostat_his` (
  `BEGIN_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `SNAP_ID` bigint(20) NOT NULL,
  `UNDOTSN` bigint(20) DEFAULT NULL,
  `UNDOBLKS` bigint(20) DEFAULT NULL,
  `TXNCOUNT` bigint(20) DEFAULT NULL,
  `MAXQUERYLEN` bigint(20) DEFAULT NULL,
  `MAXQUERYSQLID` varchar(13) DEFAULT NULL,
  `MAXCONCURRENCY` bigint(20) DEFAULT NULL,
  `UNXPSTEALCNT` bigint(20) DEFAULT NULL,
  `UNXPBLKRELCNT` bigint(20) DEFAULT NULL,
  `UNXPBLKREUCNT` bigint(20) DEFAULT NULL,
  `EXPSTEALCNT` bigint(20) DEFAULT NULL,
  `EXPBLKRELCNT` bigint(20) DEFAULT NULL,
  `EXPBLKREUCNT` bigint(20) DEFAULT NULL,
  `SSOLDERRCNT` bigint(20) DEFAULT NULL,
  `NOSPACEERRCNT` bigint(20) DEFAULT NULL,
  `ACTIVEBLKS` bigint(20) DEFAULT NULL,
  `UNEXPIREDBLKS` bigint(20) DEFAULT NULL,
  `EXPIREDBLKS` bigint(20) DEFAULT NULL,
  `TUNED_UNDORETENTION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`SNAP_ID`,`INSTANCE_NUMBER`,`BEGIN_TIME`),
  KEY `SNAP_ID` (`SNAP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_waitstat` */

DROP TABLE IF EXISTS `dba_hist_waitstat`;

CREATE TABLE `dba_hist_waitstat` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `CLASS` varchar(18) NOT NULL,
  `WAIT_COUNT` bigint(20) DEFAULT NULL,
  `TIME` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`SNAP_ID`,`INSTANCE_NUMBER`,`CLASS`),
  KEY `SNAP_ID` (`SNAP_ID`,`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `dba_hist_waitstat_his` */

DROP TABLE IF EXISTS `dba_hist_waitstat_his`;

CREATE TABLE `dba_hist_waitstat_his` (
  `SNAP_ID` bigint(20) NOT NULL,
  `DBID` bigint(20) NOT NULL,
  `INSTANCE_NUMBER` bigint(20) NOT NULL,
  `CLASS` varchar(18) NOT NULL,
  `WAIT_COUNT` bigint(20) DEFAULT NULL,
  `TIME` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DBID`,`SNAP_ID`,`INSTANCE_NUMBER`,`CLASS`),
  KEY `SNAP_ID` (`SNAP_ID`,`DBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `firewall_info` */

DROP TABLE IF EXISTS `firewall_info`;

CREATE TABLE `firewall_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime DEFAULT NULL,
  `tcp_flow_count_limit_5` varchar(20) DEFAULT NULL COMMENT '下发',
  `tcp_embryonic_count_to_host_5` varchar(20) DEFAULT NULL COMMENT '下发半开连接',
  `tcp_flow_count_limit_24` varchar(20) DEFAULT NULL COMMENT '计费',
  `tcp_embryonic_count_to_host_24` varchar(20) DEFAULT NULL COMMENT '计费半开连接',
  `connections_current` varchar(10) DEFAULT NULL,
  `connections_average` varchar(10) DEFAULT NULL,
  `tcp_conns_current` varchar(10) DEFAULT NULL,
  `tcp_conns_average` varchar(10) DEFAULT NULL,
  `udp_conns_current` varchar(10) DEFAULT NULL,
  `udp_conns_average` varchar(10) DEFAULT NULL,
  `tcp_embryonic_conns_timeout_current` varchar(10) DEFAULT NULL,
  `tcp_embryonic_conns_timeout_average` varchar(10) DEFAULT NULL,
  `sendmsg` varchar(1) DEFAULT NULL COMMENT '是否发送邮件',
  `tcp_flow_count_limit_21` varchar(20) DEFAULT NULL COMMENT 'wap',
  `tcp_embryonic_count_to_host_21` varchar(20) DEFAULT NULL COMMENT 'wap半开连接',
  `tcp_flow_count_limit_106` varchar(20) DEFAULT NULL COMMENT 'www',
  `tcp_embryonic_count_to_host_106` varchar(20) DEFAULT NULL COMMENT 'www半开连接',
  `tcp_flow_count_limit_9` varchar(20) DEFAULT NULL COMMENT '互联星空1',
  `tcp_embryonic_count_to_host_9` varchar(20) DEFAULT NULL COMMENT '互联星空1半开连接',
  `tcp_flow_count_limit_11` varchar(20) DEFAULT NULL COMMENT '互联星空2',
  `tcp_embryonic_count_to_host_11` varchar(20) DEFAULT NULL COMMENT '互联星空2半开连接',
  `tcp_flow_count_limit_201` varchar(20) DEFAULT NULL COMMENT '有声下发',
  `tcp_embryonic_count_to_host_201` varchar(20) DEFAULT NULL COMMENT '有声下发半开连接',
  `tcp_flow_count_limit_176` varchar(20) DEFAULT NULL COMMENT '异网WAP',
  `tcp_embryonic_count_to_host_176` varchar(20) DEFAULT NULL COMMENT '异网WAP半开连接',
  `tcp_flow_count_limit_199` varchar(20) DEFAULT NULL COMMENT '有声接口',
  `tcp_embryonic_count_to_host_199` varchar(20) DEFAULT NULL COMMENT '有声接口半开连接',
  `tcp_flow_count_limit_175` varchar(20) DEFAULT NULL COMMENT '异网下发',
  `tcp_embryonic_count_to_host_175` varchar(20) DEFAULT NULL COMMENT '异网下发半开连接',
  `tcp_flow_count_limit_59` varchar(20) DEFAULT NULL COMMENT '异网有声音频',
  `tcp_embryonic_count_to_host_59` varchar(20) DEFAULT NULL COMMENT '异网有声音频半开连接',
  PRIMARY KEY (`id`),
  KEY `idx_switch_environment` (`gmt_create`,`sendmsg`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `myawr_db_config` */

DROP TABLE IF EXISTS `myawr_db_config`;

CREATE TABLE `myawr_db_config` (
  `db_id` varchar(40) NOT NULL,
  `host_id` varchar(40) DEFAULT NULL COMMENT '主机ID',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '别名',
  `db_type` smallint(6) DEFAULT NULL COMMENT 'db类型 见字典（sys_type）',
  `db_username` varchar(20) DEFAULT NULL COMMENT '数据库用户名',
  `db_passwd` varchar(300) DEFAULT NULL COMMENT '数据库密码',
  `sid` varchar(20) DEFAULT NULL COMMENT '实例名',
  `db_name` varchar(20) DEFAULT NULL COMMENT '数据库名',
  `port` varchar(10) DEFAULT NULL COMMENT '端口',
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `server_id` varchar(40) DEFAULT NULL COMMENT '采集的服务器,指定由哪个采集服务器来采集数据，达到分压的效果',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  `last_monitor_status` smallint(6) DEFAULT NULL COMMENT '上一次心跳监测的状态',
  `last_heartbeat_date` datetime DEFAULT NULL COMMENT '上一次心跳监测的时间',
  `global_schedule_conf` varchar(30) DEFAULT NULL COMMENT '全局定时配置 ,quartz的定义模式，用作心跳监测',
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库配置';

/*Table structure for table `myawr_query_review` */

DROP TABLE IF EXISTS `myawr_query_review`;

CREATE TABLE `myawr_query_review` (
  `checksum` bigint(20) unsigned NOT NULL,
  `fingerprint` text NOT NULL,
  `sample` longtext NOT NULL,
  `first_seen` datetime DEFAULT NULL,
  `last_seen` datetime DEFAULT NULL,
  `reviewed_by` varchar(20) DEFAULT NULL,
  `reviewed_on` datetime DEFAULT NULL,
  `comments` text,
  `reviewed_status` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`checksum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `myawr_query_review_history` */

DROP TABLE IF EXISTS `myawr_query_review_history`;

CREATE TABLE `myawr_query_review_history` (
  `dbid_max` varchar(11) DEFAULT NULL COMMENT '数据库ID，同表b_db_config的db_id列',
  `user_max` varchar(20) DEFAULT NULL COMMENT '应用程序携带过来的用户名',
  `ipaddr_max` varchar(20) DEFAULT NULL COMMENT '应用程序携带过来的IP地址',
  `db_max` varchar(64) DEFAULT NULL COMMENT '所访问的数据库名称',
  `checksum` bigint(20) unsigned NOT NULL COMMENT '当前SQL汇总去重校验值',
  `sample` text NOT NULL COMMENT '当前运行的SQL语句',
  `ts_min` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最早执行时间',
  `ts_max` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最晚执行时间',
  `ts_cnt` float DEFAULT NULL COMMENT '总执行次数',
  `Query_time_sum` float DEFAULT NULL COMMENT 'SQL执行消耗总时间',
  `Query_time_min` float DEFAULT NULL COMMENT 'SQL执行消耗最小时间',
  `Query_time_max` float DEFAULT NULL COMMENT 'SQL执行消耗最大时间',
  `Query_time_pct_95` float DEFAULT NULL COMMENT 'SQL执行消耗平均时间',
  `Query_time_stddev` float DEFAULT NULL,
  `Query_time_median` float DEFAULT NULL,
  `Lock_time_sum` float DEFAULT NULL COMMENT '锁等待总时间',
  `Lock_time_min` float DEFAULT NULL,
  `Lock_time_max` float DEFAULT NULL,
  `Lock_time_pct_95` float DEFAULT NULL,
  `Lock_time_stddev` float DEFAULT NULL,
  `Lock_time_median` float DEFAULT NULL,
  `Rows_sent_sum` float DEFAULT NULL COMMENT '传输数据行数',
  `Rows_sent_min` float DEFAULT NULL,
  `Rows_sent_max` float DEFAULT NULL,
  `Rows_sent_pct_95` float DEFAULT NULL,
  `Rows_sent_stddev` float DEFAULT NULL,
  `Rows_sent_median` float DEFAULT NULL,
  `Rows_examined_sum` float DEFAULT NULL COMMENT '扫描数据行数',
  `Rows_examined_min` float DEFAULT NULL,
  `Rows_examined_max` float DEFAULT NULL,
  `Rows_examined_pct_95` float DEFAULT NULL,
  `Rows_examined_stddev` float DEFAULT NULL,
  `Rows_examined_median` float DEFAULT NULL,
  `Rows_affected_sum` float DEFAULT NULL COMMENT '改变数据行数',
  `Rows_affected_min` float DEFAULT NULL,
  `Rows_affected_max` float DEFAULT NULL,
  `Rows_affected_pct_95` float DEFAULT NULL,
  `Rows_affected_stddev` float DEFAULT NULL,
  `Rows_affected_median` float DEFAULT NULL,
  `Rows_read_sum` float DEFAULT NULL,
  `Rows_read_min` float DEFAULT NULL,
  `Rows_read_max` float DEFAULT NULL,
  `Rows_read_pct_95` float DEFAULT NULL,
  `Rows_read_stddev` float DEFAULT NULL,
  `Rows_read_median` float DEFAULT NULL,
  `Merge_passes_sum` float DEFAULT NULL,
  `Merge_passes_min` float DEFAULT NULL,
  `Merge_passes_max` float DEFAULT NULL,
  `Merge_passes_pct_95` float DEFAULT NULL,
  `Merge_passes_stddev` float DEFAULT NULL,
  `Merge_passes_median` float DEFAULT NULL,
  `InnoDB_IO_r_ops_min` float DEFAULT NULL COMMENT 'innodb 读取的页数',
  `InnoDB_IO_r_ops_max` float DEFAULT NULL,
  `InnoDB_IO_r_ops_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_ops_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_ops_median` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_min` float DEFAULT NULL COMMENT 'innodb读取的字节数',
  `InnoDB_IO_r_bytes_max` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_bytes_median` float DEFAULT NULL,
  `InnoDB_IO_r_wait_min` float DEFAULT NULL COMMENT 'innodb读取数据发生的等待时间(秒)',
  `InnoDB_IO_r_wait_max` float DEFAULT NULL,
  `InnoDB_IO_r_wait_pct_95` float DEFAULT NULL,
  `InnoDB_IO_r_wait_stddev` float DEFAULT NULL,
  `InnoDB_IO_r_wait_median` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_min` float DEFAULT NULL COMMENT '行锁等待时间(秒)',
  `InnoDB_rec_lock_wait_max` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_pct_95` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_stddev` float DEFAULT NULL,
  `InnoDB_rec_lock_wait_median` float DEFAULT NULL,
  `InnoDB_queue_wait_min` float DEFAULT NULL COMMENT 'innodb队列等待时间(秒)',
  `InnoDB_queue_wait_max` float DEFAULT NULL,
  `InnoDB_queue_wait_pct_95` float DEFAULT NULL,
  `InnoDB_queue_wait_stddev` float DEFAULT NULL,
  `InnoDB_queue_wait_median` float DEFAULT NULL,
  `InnoDB_pages_distinct_min` float DEFAULT NULL COMMENT '读取的独立页数量',
  `InnoDB_pages_distinct_max` float DEFAULT NULL,
  `InnoDB_pages_distinct_pct_95` float DEFAULT NULL,
  `InnoDB_pages_distinct_stddev` float DEFAULT NULL,
  `InnoDB_pages_distinct_median` float DEFAULT NULL,
  `QC_Hit_cnt` float DEFAULT NULL,
  `QC_Hit_sum` float DEFAULT NULL,
  `Full_scan_cnt` float DEFAULT NULL COMMENT '全表扫描次数',
  `Full_scan_sum` float DEFAULT NULL,
  `Full_join_cnt` float DEFAULT NULL COMMENT '全表连接次数',
  `Full_join_sum` float DEFAULT NULL,
  `Tmp_table_cnt` float DEFAULT NULL COMMENT '使用临时表数量',
  `Tmp_table_sum` float DEFAULT NULL,
  `Tmp_table_on_disk_cnt` float DEFAULT NULL COMMENT '使用硬盘上临时表数量',
  `Tmp_table_on_disk_sum` float DEFAULT NULL,
  `Filesort_cnt` float DEFAULT NULL COMMENT '使用文件排序次数',
  `Filesort_sum` float DEFAULT NULL,
  `Filesort_on_disk_cnt` float DEFAULT NULL COMMENT '硬盘上文件排序次数',
  `Filesort_on_disk_sum` float DEFAULT NULL,
  PRIMARY KEY (`checksum`,`ts_min`,`ts_max`),
  KEY `idx_myawr_query_review_history_host_id` (`dbid_max`,`ts_max`),
  KEY `idx_myawr_query_review_history_ts_max` (`ts_max`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/*!50100 PARTITION BY RANGE (to_days(ts_max))
(PARTITION p1305 VALUES LESS THAN (735385) ENGINE = InnoDB,
 PARTITION p1306 VALUES LESS THAN (735415) ENGINE = InnoDB,
 PARTITION p1307 VALUES LESS THAN (735446) ENGINE = InnoDB,
 PARTITION p1308 VALUES LESS THAN (735477) ENGINE = InnoDB,
 PARTITION p1309 VALUES LESS THAN (735507) ENGINE = InnoDB,
 PARTITION p1310 VALUES LESS THAN (735538) ENGINE = InnoDB,
 PARTITION p1311 VALUES LESS THAN (735568) ENGINE = InnoDB,
 PARTITION p1312 VALUES LESS THAN (735599) ENGINE = InnoDB,
 PARTITION p1401 VALUES LESS THAN (735630) ENGINE = InnoDB,
 PARTITION p1402 VALUES LESS THAN (735661) ENGINE = InnoDB,
 PARTITION p1403 VALUES LESS THAN (735692) ENGINE = InnoDB,
 PARTITION p1404 VALUES LESS THAN (735723) ENGINE = InnoDB,
 PARTITION p1405 VALUES LESS THAN (735754) ENGINE = InnoDB,
 PARTITION p1406 VALUES LESS THAN (735785) ENGINE = InnoDB,
 PARTITION p1407 VALUES LESS THAN (735816) ENGINE = InnoDB,
 PARTITION p1408 VALUES LESS THAN (735847) ENGINE = InnoDB,
 PARTITION p1409 VALUES LESS THAN (735878) ENGINE = InnoDB,
 PARTITION p1410 VALUES LESS THAN (735909) ENGINE = InnoDB,
 PARTITION p1411 VALUES LESS THAN (735940) ENGINE = InnoDB,
 PARTITION p1412 VALUES LESS THAN (735971) ENGINE = InnoDB,
 PARTITION p1501 VALUES LESS THAN (736002) ENGINE = InnoDB,
 PARTITION p1502 VALUES LESS THAN (736033) ENGINE = InnoDB,
 PARTITION p1503 VALUES LESS THAN (736064) ENGINE = InnoDB,
 PARTITION p1504 VALUES LESS THAN (736095) ENGINE = InnoDB,
 PARTITION p1505 VALUES LESS THAN (736126) ENGINE = InnoDB,
 PARTITION p1506 VALUES LESS THAN (736157) ENGINE = InnoDB,
 PARTITION p1507 VALUES LESS THAN (736188) ENGINE = InnoDB,
 PARTITION p1508 VALUES LESS THAN (736219) ENGINE = InnoDB,
 PARTITION p1509 VALUES LESS THAN (736250) ENGINE = InnoDB,
 PARTITION p1510 VALUES LESS THAN (736281) ENGINE = InnoDB,
 PARTITION p1511 VALUES LESS THAN (736312) ENGINE = InnoDB,
 PARTITION p1512 VALUES LESS THAN (736343) ENGINE = InnoDB) */;

/*Table structure for table `switch_port_info` */

DROP TABLE IF EXISTS `switch_port_info`;

CREATE TABLE `switch_port_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `switch_ip` varchar(20) DEFAULT NULL COMMENT '交换机IP',
  `gmt_create` datetime DEFAULT NULL,
  `port_name` varchar(40) DEFAULT NULL COMMENT '交换机端口名称',
  `port_status` varchar(20) DEFAULT NULL COMMENT '交换机端口状态',
  `input_rate_b` bigint(20) DEFAULT NULL COMMENT '最近5分钟平均输入速率（bits/sec）',
  `output_rate_b` bigint(20) DEFAULT NULL COMMENT '最近5分钟平均输出速率（bits/sec）',
  `input_rate_p` bigint(20) DEFAULT NULL COMMENT '最近5分钟平均输入速率（packets/sec）',
  `output_rate_p` bigint(20) DEFAULT NULL COMMENT '最近5分钟平均输出速率（packets/sec）',
  `sendmsg` varchar(1) DEFAULT NULL COMMENT '是否发送邮件',
  PRIMARY KEY (`id`),
  KEY `idx_switch_ip_create` (`switch_ip`,`gmt_create`),
  KEY `idx_switch_port_info` (`sendmsg`,`gmt_create`),
  KEY `idx_switch_port` (`switch_ip`,`port_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Table structure for table `switch_user` */

DROP TABLE IF EXISTS `switch_user`;

CREATE TABLE `switch_user` (
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT 'dba名称',
  `user_mail` varchar(50) DEFAULT NULL COMMENT 'mail地址',
  `user_phone` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `phone_type` varchar(10) DEFAULT NULL COMMENT '号码类型',
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `t_resouce` */

DROP TABLE IF EXISTS `t_resouce`;

CREATE TABLE `t_resouce` (
  `id` varchar(40) NOT NULL,
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父资源ID',
  `resouce_name` varchar(40) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `status` char(1) DEFAULT NULL COMMENT '状态  0:正常,1:删除',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(40) NOT NULL COMMENT '角色ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `status` char(1) DEFAULT NULL COMMENT '状态 0:正常,1:删除',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `t_role_priv` */

DROP TABLE IF EXISTS `t_role_priv`;

CREATE TABLE `t_role_priv` (
  `role_id` varchar(40) NOT NULL COMMENT '角色ID',
  `resouce_id` varchar(40) NOT NULL COMMENT '资源ID',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`,`resouce_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Table structure for table `t_test` */

DROP TABLE IF EXISTS `t_test`;

CREATE TABLE `t_test` (
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL COMMENT '用户ID',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `email` varchar(40) DEFAULT NULL COMMENT '邮箱',
  `mobile_mail` varchar(40) DEFAULT NULL COMMENT '139邮箱',
  `phone` varchar(40) DEFAULT NULL COMMENT '手机',
  `status` char(1) DEFAULT NULL COMMENT '状态 0:正常,1:删除',
  `passwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` varchar(40) NOT NULL COMMENT '用户ID',
  `role_id` varchar(40) NOT NULL COMMENT '角色ID',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modifed` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/* Function  structure for function  `fun_identify_gen` */

/*!50003 DROP FUNCTION IF EXISTS `fun_identify_gen` */;
DELIMITER $$

/*!50003 CREATE  FUNCTION `fun_identify_gen`(tablename VARCHAR(50)) RETURNS varchar(40) CHARSET utf8
BEGIN
	DECLARE result VARCHAR(40);
	DECLARE cur_val INT;
	DECLARE prefix_keyword VARCHAR(10);
	DECLARE column_len INT;
	
	IF tablename <> '' THEN 
		SELECT 
			current_value,max_len,prefix INTO cur_val , column_len, prefix_keyword
		FROM b_identify_gen WHERE table_name=tablename;
		
		IF prefix_keyword = '' OR prefix_keyword IS NULL THEN 
			RETURN '';
		ELSE 
			UPDATE b_identify_gen SET current_value=current_value+1 WHERE table_name=tablename;
			SELECT cur_val+1 INTO result FROM DUAL;
			RETURN result;
		END IF;					
		
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `gen_identify` */

/*!50003 DROP PROCEDURE IF EXISTS  `gen_identify` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `gen_identify`(in  tablename varchar(50),in server_id varchar(15),out sid varchar(40))
BEGIN
        
        DECLARE result VARCHAR(40);
	DECLARE cur_val INT;
	DECLARE prefix_keyword VARCHAR(10);
	DECLARE column_len INT;
	SET autocommit=0;
	IF tablename <> '' THEN 
			UPDATE b_identify_gen SET current_value=current_value+1 WHERE table_name=tablename;
			SELECT current_value,max_len,prefix INTO cur_val , column_len, prefix_keyword
		          FROM b_identify_gen WHERE table_name=tablename;	
			SELECT cur_val INTO sid FROM DUAL;
	commit;
	SET autocommit=1;							
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_disk_init` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_disk_init` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_disk_init`(v_host_id VARCHAR(40))
BEGIN
	DECLARE v_snap_id INT;
	
	DELETE FROM b_disk_monitor WHERE host_id=v_host_id ;
	SELECT MAX(snap_id) INTO v_snap_id FROM `b_biz_snap` WHERE monitor_id=v_host_id AND config_type=1;
	INSERT INTO b_disk_monitor(host_id,disk_name,used,remain,used_percent,threshold_percent,threshold_minsize,gmt_created,gmt_modifed,threshold_type,warn_quota_value,critical_quota_value)
	SELECT host_id,disk_name,used,remain,used_percent,90,NULL,NOW(),NOW(),1,90,95 FROM b_disk_monitor_his WHERE snap_id=CONCAT(v_snap_id,'');
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_init_keepalived_quota` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_init_keepalived_quota` */;

DELIMITER $$

/*!50003 CREATE  PROCEDURE `p_init_keepalived_quota`(v_momitor_id VARCHAR(40))
BEGIN
        DECLARE d_counts INT ;
        
	SELECT COUNT(1) INTO d_counts FROM b_quota_schedule_config WHERE monitor_id= v_momitor_id AND config_type=2;      
        
IF d_counts = 0 THEN
       INSERT INTO b_quota_schedule_config
	(
	   id                   ,
	   monitor_id           ,
	   config_type          ,
	   catagory_quota_id    ,
	   quota_owner          ,
	   gmt_created          ,
	   gmt_modifed          
	) 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,4,3,'Alert_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,4,4,'HEART_BEAT_CATAGORY',NOW(),NOW() FROM DUAL ;
	COMMIT;
 
		
        END IF;
        
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_init_monitor_deamon_schedule_config` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_init_monitor_deamon_schedule_config` */;

DELIMITER $$

/*!50003 CREATE  PROCEDURE `p_init_monitor_deamon_schedule_config`(v_monitor_id VARCHAR(40),v_config_type INT)
BEGIN
	IF v_monitor_id IS NOT NULL AND v_config_type IN (1,2,3,4) THEN
		
		DELETE FROM b_daemon_schedule_config WHERE monitor_id=v_monitor_id AND config_type=v_config_type;
		
		INSERT INTO b_daemon_schedule_config
		(
		  id,monitor_id,config_type,job_id,job_name,
		 job_group, quartz_conf, poll_unit, poll_times, 
		 ext_col,gmt_created, gmt_modifed,bean_id,schedule_class,STATUS
		) 
		SELECT fun_identify_gen('b_daemon_schedule_config') ,t.monitor_id,t.config_type,
			t.job_id,
			CASE WHEN t.job_id IN (8,14,21) THEN
			    CONCAT(java.bean_prefix,t.monitor_id,'_',t.id,'_',java.name,'_monitor')
			ELSE 
			    CONCAT(java.bean_prefix,t.monitor_id,'_',java.name,'_monitor')
			END ,
			CONCAT(java.name,'_monitor_group'),
			CASE 
			WHEN java.id IN (1,2,3,4,11,12,13,8,14,20,21,23,26) THEN '20 0/1 * * * ?'
			WHEN java.id IN (6,27) THEN '20 0 0/1 * * ?'
			WHEN java.id IN (5) THEN '10 2,12,22,32,42,52 * * * ?'
			WHEN java.id IN (15,16) THEN '20 0 0/1 * * ?'
			WHEN java.id IN (7) THEN '10 1,11,21,31,41,51 * * * ?'
			ELSE '10 0,10,20,30,40,50 * * * ?' END ,
			1,
			CASE 
			WHEN java.id IN (11,12,13) THEN 1
			ELSE 10 END,'',NOW(),NOW(),
			CASE WHEN t.job_id IN (8,14,21) THEN
			    CONCAT(java.bean_prefix,t.id)
			ELSE 
			    CONCAT(java.bean_prefix,t.monitor_id)
			END ,
			java.pak_class,0
			
			
		FROM (
        SELECT r.job_id,config.monitor_id,config.config_type,'' id
         FROM b_quota_schedule_config config, b_quota_daemon_relation r 
        WHERE config.quota_owner = r.catagory_class 
        	AND config.catagory_quota_id=r.quota_catagory_id
        	AND monitor_id = v_monitor_id
        	AND config_type = v_config_type AND r.job_id NOT IN (8,14,21)
        GROUP BY config.monitor_id,config.config_type,r.job_id
        UNION 
        SELECT r.job_id,config.monitor_id,config.config_type,dir.id
         FROM b_quota_schedule_config config, b_quota_daemon_relation r ,b_host_dir_alert_config dir 
        WHERE config.quota_owner = r.catagory_class 
        	AND config.catagory_quota_id=r.quota_catagory_id
        	AND config.monitor_id = v_monitor_id
        	AND config.config_type = v_config_type AND r.job_id IN (8,14,21)
        	AND dir.monitor_id = config.monitor_id AND dir.config_type = config.config_type 
        GROUP BY config.monitor_id,config.config_type,r.job_id) t,`b_java_job_def` java 
		WHERE t.`job_id` = java.`id`;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_init_mysql_quota` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_init_mysql_quota` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_init_mysql_quota`(v_momitor_id VARCHAR(40))
BEGIN
        DECLARE d_counts INT ;
        
        DELETE FROM b_quota_schedule_config WHERE monitor_id= v_momitor_id AND config_type=3;  
        
	SELECT COUNT(1) INTO d_counts FROM b_quota_schedule_config WHERE monitor_id= v_momitor_id AND config_type=3;   
	   
        
        
IF d_counts = 0 THEN
       INSERT INTO b_quota_schedule_config
	(
	   id                   ,
	   monitor_id           ,
	   config_type          ,
	   catagory_quota_id    ,
	   quota_owner          ,
	   gmt_created          ,
	   gmt_modifed          
	) 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,1,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,2,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,3,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,4,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,5,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,6,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,7,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION 
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,8,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,9,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,10,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,11,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,12,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,13,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,14,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,15,'MY_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,3,'HEART_BEAT_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,2,'Alert_CATAGORY',NOW(),NOW() FROM DUAL UNION
		SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,3,2,'TABLE_SPACE_CATAGORY',NOW(),NOW() FROM DUAL;
	COMMIT;
 
		
        END IF;
        
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_init_oracle_quota` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_init_oracle_quota` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_init_oracle_quota`(v_momitor_id VARCHAR(40))
BEGIN
        DECLARE d_counts INT ;
        
	SELECT COUNT(1) INTO d_counts FROM b_quota_schedule_config WHERE monitor_id= v_momitor_id AND config_type=2;      
        
IF d_counts = 0 THEN
       INSERT INTO b_quota_schedule_config
	(
	   id                   ,
	   monitor_id           ,
	   config_type          ,
	   catagory_quota_id    ,
	   quota_owner          ,
	   gmt_created          ,
	   gmt_modifed          
	) 
		
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,1,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,2,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,3,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,4,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,5,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,6,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,7,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,8,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,8,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,9,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,10,'ORA_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,2,'HEART_BEAT_CATAGORY',NOW(),NOW() FROM DUAL UNION
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,1,'Alert_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,2,1,'TABLE_SPACE_CATAGORY',NOW(),NOW() FROM DUAL;
	COMMIT;
 
		
        END IF;
        
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_init_server_quota` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_init_server_quota` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_init_server_quota`(v_momitor_id VARCHAR(40))
BEGIN
        declare d_counts int ;
        
	select count(1) into d_counts from b_quota_schedule_config where monitor_id= v_momitor_id AND config_type=2;      
        
if d_counts = 0 then
       INSERT INTO b_quota_schedule_config
	(
	   id                   ,
	   monitor_id           ,
	   config_type          ,
	   catagory_quota_id    ,
	   quota_owner          ,
	   gmt_created          ,
	   gmt_modifed          
	) 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,1,'SYS_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,2,'SYS_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,3,'SYS_CATAGORY',NOW(),NOW() FROM DUAL UNION 
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,4,'SYS_CATAGORY',NOW(),NOW() FROM DUAL union
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,1,'HEART_BEAT_CATAGORY',NOW(),NOW() FROM DUAL union
	SELECT fun_identify_gen('b_quota_schedule_config'),v_momitor_id,1,5,'SYS_CATAGORY',NOW(),NOW() FROM DUAL;
	commit;
 
		
        end if;
        
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_mysql_quota_threshold_init` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_mysql_quota_threshold_init` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_mysql_quota_threshold_init`(v_db_id VARCHAR(40))
BEGIN
	-- 删除
	DELETE FROM b_threshold WHERE monitor_id = v_db_id AND config_type=3;
	
	-- 初始化
	INSERT INTO b_threshold(monitor_id,quota_id,config_type,quota_value,quota_metric,last_quota_value,STATUS,threshold_type,gmt_created,gmt_modifed,warn_quota_value,critical_quota_value,last_warn_quota_value,last_critical_quota_value)
	SELECT v_db_id,'77777779',3,1500,'%',NULL,0,1,NOW(),NOW(),1500,2000,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'21',3,500,' ',NULL,0,2,NOW(),NOW(),500,700,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'22',3,20,' ',NULL,0,2,NOW(),NOW(),20,40,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'77777780',3,60,' ',NULL,0,1,NOW(),NOW(),60,120,0,0 FROM DUAL ;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_oracle_quota_threshold_init` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_oracle_quota_threshold_init` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_oracle_quota_threshold_init`(v_db_id VARCHAR(40))
BEGIN
	-- 删除
	DELETE FROM b_threshold WHERE monitor_id = v_db_id AND config_type=2;
	
	-- 初始化
	INSERT INTO b_threshold(monitor_id,quota_id,config_type,quota_value,quota_metric,last_quota_value,STATUS,threshold_type,gmt_created,gmt_modifed,warn_quota_value,critical_quota_value,last_warn_quota_value,last_critical_quota_value)
	SELECT v_db_id,'1190468109',2,2000,' ',NULL,0,2,NOW(),NOW(),2000,2500,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'1236385760',2,9999999,' ',NULL,0,2,NOW(),NOW(),9999999,19999999,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'2263124246',2,7000,' ',NULL,0,2,NOW(),NOW(),7000,8000,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'4162191256',2,300000,' ',NULL,0,2,NOW(),NOW(),300000,350000,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'77777777',2,100,' ',NULL,0,1,NOW(),NOW(),100,150,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'77777778',2,1500,' ',NULL,0,1,NOW(),NOW(),1500,2000,0,0 FROM DUAL UNION ALL
	SELECT v_db_id,'2006',2,2000,' ',NULL,0,2,NOW(),NOW(),2000,2500,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'2016',2,9999999,' ',NULL,0,2,NOW(),NOW(),9999999,19999999,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'2004',2,7000,' ',NULL,0,2,NOW(),NOW(),7000,8000,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'2030',2,300000,' ',NULL,0,2,NOW(),NOW(),300000,350000,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'77777777',2,100,' ',NULL,0,1,NOW(),NOW(),100,150,0,0 FROM DUAL UNION ALL 
	SELECT v_db_id,'77777778',2,1500,' ',NULL,0,1,NOW(),NOW(),1500,2000,0,0 FROM DUAL
	
	;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_server_quota_threshold_init` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_server_quota_threshold_init` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_server_quota_threshold_init`(v_host_id VARCHAR(40))
BEGIN
	-- 删除
	DELETE FROM b_threshold WHERE monitor_id = v_host_id AND config_type=1;
	
	-- 初始化
	INSERT INTO b_threshold(monitor_id,quota_id,config_type,quota_value,quota_metric,last_quota_value,STATUS,threshold_type,gmt_created,gmt_modifed,warn_quota_value,critical_quota_value,last_warn_quota_value,last_critical_quota_value)
	SELECT v_host_id,'1',1,60,'%',NULL,0,1,NOW(),NOW(),60,80,0,0 FROM DUAL UNION ALL 
	SELECT v_host_id,'2',1,40,'%',NULL,0,1,NOW(),NOW(),40,60,0,0 FROM DUAL UNION ALL 
	SELECT v_host_id,'3',1,20,'%',NULL,0,3,NOW(),NOW(),20,10,0,0 FROM DUAL UNION ALL 
	SELECT v_host_id,'4',1,40,'%',NULL,0,1,NOW(),NOW(),40,60,0,0 FROM DUAL UNION ALL 
	SELECT v_host_id,'5',1,20,' ',NULL,0,1,NOW(),NOW(),20,30,0,0 FROM DUAL ;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `p_tablespace_init` */

/*!50003 DROP PROCEDURE IF EXISTS  `p_tablespace_init` */;

DELIMITER $$

/*!50003 CREATE   PROCEDURE `p_tablespace_init`(v_db_id VARCHAR(40))
BEGIN
	DECLARE v_snap_id INT;
	
	DELETE FROM b_db_tablespace WHERE db_id=v_db_id ;
	
	SELECT MAX(snap_id) INTO v_snap_id FROM `b_biz_snap` WHERE monitor_id=v_db_id AND config_type>1;
	
	INSERT INTO b_db_tablespace(db_id,tbs_name,used_tbs,max_tbs,use_percent,threshold_percent,threshold_minsize,gmt_created,gmt_modifed,threshold_type,warn_quota_value,critical_quota_value)
	SELECT db_id,tbs_name,used_tbs,max_tbs,use_percent,90,NULL,NOW(),NOW(),1,90,95 FROM b_db_tablespace_his WHERE snap_id=CONCAT(v_snap_id,'');
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
