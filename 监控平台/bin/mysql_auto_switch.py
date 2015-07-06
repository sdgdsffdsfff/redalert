#!/bin/env python
#coding:utf-8
import os
import commands
import sys
import string
import time
import datetime
import MySQLdb
import global_functions as func
from fabric.api import env,run,put,get,local 
import aes

conn=MySQLdb.connect(host=func.host,user=func.user,passwd=func.passwd,port=int(func.port),db=func.dbname,connect_timeout=2,charset='utf8')
mysqlcur=conn.cursor()

def get_mysql_pass(t_dbid):
    global username,password,port,ip_addr,host_id,g_h_username,g_h_passwd
    mysqlcur.execute("set names utf8")
    t_sql="SELECT a.slow_log_user,a.slow_log_pass,a.port,b.ip_addr,a.host_id,b.user_account_1,b.user_passwd_1 FROM b_db_config a,b_host_config b WHERE a.host_id=b.host_id  and a.db_id=%s   limit 1 "
    mysqlcur.execute(t_sql,(t_dbid,))
    data = mysqlcur.fetchone()
    username=data[0]
    password=data[1]
    port=data[2]
    ip_addr=data[3]
    host_id=data[4]
    g_h_username=data[5]
    g_h_passwd=aes.jiemi(data[6])
    print 'IP: '+ip_addr


def mysql_switch_auto(g_dbid):
    conn=MySQLdb.connect(host=func.host,user=func.user,passwd=func.passwd,port=int(func.port),db=func.dbname,connect_timeout=2,charset='utf8')
    try:
        mysqlcur=conn.cursor()
        mysqlcur.execute("set names utf8")
        t_sql="SELECT read_only,master_server,master_port,current_binlog_file,current_binlog_pos,master_binlog_file,master_binlog_pos FROM b_mysql_replication WHERE db_id=%s"
        mysqlcur.execute(t_sql,(g_dbid,))
        data = mysqlcur.fetchone()
        read_only,slave,port,current_binlog_file,current_binlog_pos,master_binlog_file,master_binlog_pos=data
        print 'Read_Only: '+read_only
        print 'Slave: '+slave
        print 'Port: '+port
        env.user = g_h_username
        env.host_string = ip_addr+':22'
        env.password = g_h_passwd
        if read_only=='OFF':
           m_process=run('ps -ef|grep mysqld|grep '+port+'|wc -l')
           k_process=run('ps -ef|grep keepalived|wc -l')
           slavedb = MySQLdb.connect(host=slave, user=username,passwd=password,port=int(port),charset="utf8")
           slavecur = slavedb.cursor()
           sql_show="show variables like 'read_only';"
           sql_on="set global read_only='on';"
           sql_off="set global read_only='off';"
           if int(m_process) < 2 or int(k_process) <= 4:
              if current_binlog_file == master_binlog_file and current_binlog_pos == master_binlog_pos:
                 slavecur.execute(sql_off)
                 run('service keepalived stop')
                 env.user = g_h_username
                 env.host_string = slave+':22'
                 env.password = g_h_passwd
                 run('service keepalived start')
        else:pass
    except MySQLdb.Error,e:
             print "Mysql DB is Down :%s" % (g_dbid)   
    

def mysql_switch_manual(g_dbid):
    conn=MySQLdb.connect(host=func.host,user=func.user,passwd=func.passwd,port=int(func.port),db=func.dbname,connect_timeout=2,charset='utf8')
    mysqlcur=conn.cursor()
    mysqlcur.execute("set names utf8")
    t_sql="SELECT read_only,master_server,master_port,current_binlog_file,current_binlog_pos,master_binlog_file,master_binlog_pos FROM b_mysql_replication WHERE db_id=%s"
    mysqlcur.execute(t_sql,(g_dbid,))
    data = mysqlcur.fetchone()
    read_only,slave,port,current_binlog_file,current_binlog_pos,master_binlog_file,master_binlog_pos=data
    print 'Read_Only: '+read_only
    print 'Slave: '+slave
    print 'Port: '+port
    env.user = g_h_username
    env.host_string = ip_addr+':22'
    env.password = g_h_passwd
    if read_only=='OFF':
       masterdb = MySQLdb.connect(host=ip_addr, user=username,passwd=password,port=int(port),charset="utf8")
       mastercur = masterdb.cursor()
       slavedb = MySQLdb.connect(host=slave, user=username,passwd=password,port=int(port),charset="utf8")
       slavecur = slavedb.cursor()
       sql_show="show variables like 'read_only';"
       sql_on="set global read_only='on';"
       sql_off="set global read_only='off';"
       mastercur.execute(sql_on)
       if current_binlog_file == master_binlog_file and current_binlog_pos == master_binlog_pos:
          slavecur.execute(sql_off)
          run('service keepalived stop')
          env.user = g_h_username
          env.host_string = slave+':22'
          env.password = g_h_passwd
          run('service keepalived start')
    else:
       print 'DBID: '+g_dbid+' is slave! Please chose a master!'

if __name__=="__main__":
       h_dbid=sys.argv[1]  
       h_type=sys.argv[2] #切换方式:手动(manual)，自动(auto)

       if h_type=='manual':
          get_mysql_pass(h_dbid)
          mysql_switch_manual(h_dbid)
       elif h_type=='auto':
            get_mysql_pass(h_dbid)
            mysql_switch_auto(h_dbid)

'''
       while True: 
             check_mysql_status(h_gid)
             get_keepalived_status(h_gid)
'''
