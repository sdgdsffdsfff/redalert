#!/bin/env python
#coding:utf-8
import os
import sys
import string
import time
import datetime
import MySQLdb
import global_functions as func
import getopt

def main():
    global h_dbid

    try:
        opts, args = getopt.getopt(sys.argv[1:], "Hh:d:", ["help", "dbid="])
        if len(sys.argv)==1: 
           usage()
           sys.exit(2)
    except getopt.GetoptError:
        # print help information and exit:
        usage()
        sys.exit(2)
    for name,value in opts:
        if name in ("-H","--help"):
           usage()
           sys.exit()
        if name in ("-h","--help"):
           usage()
           sys.exit()
        if name in ("-d","--dbid"):
           h_dbid=value
           print "h_dbid:"+h_dbid
    get_mysql_pass(h_dbid)
    get_mysql_replication()

#获取连接信息
def get_mysql_pass(t_dbid):
    global username,password,port,ip_addr,host_id,g_h_username,g_h_passwd
    conn=MySQLdb.connect(host=func.host,user=func.user,passwd=func.passwd,port=int(func.port),db=func.dbname,connect_timeout=2,charset='utf8')
    mysqlcur=conn.cursor()
    mysqlcur.execute("set names utf8")
    t_sql="SELECT a.db_username,a.db_passwd,a.port,b.ip_addr,a.host_id,b.user_account,b.user_passwd FROM b_db_config a,b_host_config b WHERE a.host_id=b.host_id  and a.db_id=%s   limit 1 "
    mysqlcur.execute(t_sql,(t_dbid,))
    data = mysqlcur.fetchone()
    username,password,port,ip_addr,host_id,g_h_username,g_h_passwd=data
    #print data

#获取主从关系
def get_mysql_replication():
    connect=MySQLdb.connect(host=ip_addr, user=username,passwd=password,port=int(port),connect_timeout=2,charset="utf8")
    try:
        cur=connect.cursor()
        connect.select_db('information_schema')
        master_thread=cur.execute("select * from information_schema.processlist where COMMAND = 'Binlog Dump' or COMMAND = 'Binlog Dump GTID';")
        slave_status=cur.execute('show slave status;')

        datalist=[]
        uptime=cur.execute('SHOW GLOBAL STATUS LIKE "Uptime";');
        uptime_data = cur.fetchone()
        uptime_data = int(uptime_data[1])
        uptime_sql="SELECT NOW() - INTERVAL %s SECOND 'startup_time';"
        cur.execute(uptime_sql,(uptime_data,))
        startup_time=cur.fetchone()
        runtime_sql="SELECT TIMESTAMPDIFF(DAY,%s,NOW());"
        cur.execute(runtime_sql,(startup_time[0],))
        run_time=cur.fetchone()
        version=cur.execute('select version();')
        version_data=cur.fetchone()

        if master_thread >= 1:
            datalist.append(int(1))
            if slave_status <> 0:
                datalist.append(int(1))
            else:
                datalist.append(int(0))
        else:
            datalist.append(int(0))
            if slave_status <> 0:
                datalist.append(int(1))
            else:
                datalist.append(int(0))
            
        if slave_status <> 0:
            read_only=cur.execute("select * from information_schema.global_variables where variable_name='read_only';")
            result=cur.fetchone()
            datalist.append(result[1])
            slave_info=cur.execute('show slave status;')
            result=cur.fetchone()
            master_server=result[1]
            master_port=result[3]
            slave_io_run=result[10]
            slave_sql_run=result[11]
            delay=result[32]
            current_binlog_file=result[9]
            current_binlog_pos=result[21]
            master_binlog_file=result[5]
            master_binlog_pos=result[6]
           
            datalist.append(master_server)
            datalist.append(master_port)
            datalist.append(slave_io_run)
            datalist.append(slave_sql_run)
            datalist.append(delay)
            datalist.append(current_binlog_file)
            datalist.append(current_binlog_pos)
            datalist.append(master_binlog_file)
            datalist.append(master_binlog_pos)
        
        elif master_thread >= 1:
             read_only=cur.execute("select * from information_schema.global_variables where variable_name='read_only';")
             result=cur.fetchone()
             datalist.append(result[1])
             datalist.append('---')
             datalist.append('---')
             datalist.append('---')
             datalist.append('---')
             datalist.append('---')
             datalist.append('---')
             datalist.append('---')
             master=cur.execute('show master status;')
             master_result=cur.fetchone()
             datalist.append(master_result[0])
             datalist.append(master_result[1])
        
        else:
            datalist=[]
                  
        cur.close()
        connect.close()
        result=datalist
        #print result
        
        if result:
            sql="replace into b_mysql_replication(db_id,db_ip,startup_time,run_time,version,is_master,is_slave,read_only,master_server,master_port,slave_io_run,slave_sql_run,delay,current_binlog_file,current_binlog_pos,master_binlog_file,master_binlog_pos) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            param=(h_dbid,ip_addr,startup_time[0],run_time[0],version_data[0],result[0],result[1],result[2],result[3],result[4],result[5],result[6],result[7],result[8],result[9],result[10],result[11])
            func.mysql_exec(sql,param)
    except MySQLdb.Error,e:
             print "Mysql Error %d: %s" % (e.args[0], e.args[1])   

'''
if __name__=="__main__":
        h_dbid=sys.argv[1]   #要访问的db_id
        get_mysql_pass(h_dbid)     
        get_mysql_replication() 
'''

def usage():
    print '''
---------------usage:------------------
python get_mysql_replication_new.py -d dbid
or
python get_mysql_replication_new.py --dbid=dbid 
---------------------------------------
'''

if __name__=="__main__":
   main()
