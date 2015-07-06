#-*-coding:utf-8-*-
import MySQLdb
import sys
import subprocess
import time
import datetime
import random
import os
import re

reload(sys)
sys.setdefaultencoding('gbk')

g_gmt_create_dt=datetime.datetime.now()
gmt=g_gmt_create_dt.strftime('%Y%m%d%H%M%S')

ywpt_db_conf='/home/mysql/admin/bin/newbin/ywpt_db.conf'

monitor_db={}
tmp_monitor_db={}

def read_conf(conf_file,type):
    file_hand=open(conf_file)
    for line in file_hand:
        line=line.strip()
        (name,value)=line.split(':')
        if type=='ywpt':
            monitor_db[name]=value
        if type=='tmp':
            tmp_monitor_db[name]=value
    file_hand.close()

read_conf(ywpt_db_conf,'ywpt')

mysqldb = MySQLdb.connect(host=monitor_db.get('ip'), user=monitor_db.get('user'),passwd=monitor_db.get('pass'),db=monitor_db.get('db'),port=int(monitor_db.get('port')) ,charset="utf8")
mysqldb2 = MySQLdb.connect(host=monitor_db.get('ip'), user=monitor_db.get('user'),passwd=monitor_db.get('pass'),db=monitor_db.get('db'),port=int(monitor_db.get('port')) ,charset="utf8")

mysqlcur=mysqldb.cursor()
mysqlcur2=mysqldb2.cursor()


def write_file (gmt,sid,g_str):
        file_object = open('/home/mysql/admin/bin/newbin/test.txt', 'a')
        file_object.writelines(('%s \"%s\" \"%s\"\r\n' % (gmt,sid,g_str)))
        file_object.close


def regular_exp(g_in):
        p1=re.compile('(\".*?\")|(\'.*?\')|(\(.*?\))|(\(.*?\))|\d+(\.?\d+)?')
        g_out=p1.sub('?',g_in)
        p2=re.compile('\s\s+')
        g_out=p2.sub(' ',g_out)
        return g_out

def list_statment():
        mysqlcur.execute("set names utf8")
        mysqlcur.execute("SELECT id,info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE COMMAND <> 'sleep'")
        data=mysqlcur.fetchall()
        for x in data :
                v_id=str(x[0])
                v_info=str(x[1])

                v_info=regular_exp(v_info)
                #print 'inf:' + v_info
                #print 'out:' + g_out
                #print ' '
                if g_out==v_info :  
                        write_file(gmt,v_id,v_info.upper())
			mysqlcur2.execute("kill " + v_id +";")
	mysqlcur2.close()

def run_time(v_second):
	for x in sorted(range(v_second),reverse=True):
		time.sleep(1)
		list_statment()	
	

if __name__=="__main__":
        g_str=sys.argv[1]
         
        g_out=regular_exp(g_str)

        run_time(int(sys.argv[2]))
