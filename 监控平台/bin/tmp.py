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

def alter_mysql_pass(user,pwd):
    global username,password,port,ip_addr,host_id,g_h_username,g_h_passwd
    mysqlcur.execute("set names utf8")
    t_sql="SELECT b.ip_addr,c.nick_name,c.port FROM b_host_config b,b_db_config c WHERE b.host_id=c.host_id AND c.db_type=3 "
    mysqlcur.execute(t_sql,)
    data = mysqlcur.fetchall()
    for d in data:
        ip_addr=d[0]
        port=d[2]
        print ip_addr,port
        remotedb = MySQLdb.connect(host=ip_addr,user=user,passwd=pwd,port=int(port),charset="utf8")
        remotecur=remotedb.cursor()
        select_sql="show variables like 'server_id';"
        t_sql="grant all on *.* to root@'%' identified by 'tyyd!QAZxsw2';"
        #remotecur.execute(t_sql)
        remotecur.execute(select_sql)
        result=remotecur.fetchall()
        for r in result: 
           print "server_id: ",r[1]

if __name__=="__main__":
       username=sys.argv[1]  
       password=sys.argv[2]
       alter_mysql_pass(username,password)

