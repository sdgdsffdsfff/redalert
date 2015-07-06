#!/bin/env python
#-*-coding:utf-8-*-

import MySQLdb
import string
import sys 
reload(sys) 
sys.setdefaultencoding('utf8')

monitor_db={}

def read_conf():
    conf_file='/home/mysql/admin/bin/newbin/ywpt_db.conf'
    file_hand=open(conf_file)
    for line in file_hand:
        line=line.strip()
        (name,value)=line.split(':')
        monitor_db[name]=value
    file_hand.close()
read_conf()

host=monitor_db.get('ip')
user=monitor_db.get('user')
passwd=monitor_db.get('pass')
dbname=monitor_db.get('db')
port=monitor_db.get('port')

def mysql_exec(sql,param):
    conn=MySQLdb.connect(host=host,user=user,passwd=passwd,port=int(port),connect_timeout=5,charset='utf8')
    conn.select_db(dbname)
    cursor = conn.cursor()
    if param <> '':
        cursor.execute(sql,param)
    else:
        cursor.execute(sql)
    conn.commit()
    cursor.close()
    conn.close()

def mysql_query(sql):
    conn=MySQLdb.connect(host=host,user=user,passwd=passwd,port=int(port),connect_timeout=5,charset='utf8')
    conn.select_db(dbname)
    cursor = conn.cursor()
    count=cursor.execute(sql)
    if count == 0 :
        result=0
    else:
        result=cursor.fetchall()
    return result
    cursor.close()
    conn.close()

