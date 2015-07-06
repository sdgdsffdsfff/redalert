#-*-coding:utf-8-*-
import MySQLdb
import sys
import subprocess
import time
import string
import datetime
import random
import os
from fabric.api import env,run,put,get,local
import getopt
##### zhou fei 2014-07-28

reload(sys)
sys.setdefaultencoding('gbk')
os.getenv("ORACLE_HOME")
os.getenv("LD_LIBRARY_PATH")
os.getenv("NLS_LANG")
os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.UTF8'
import cx_Oracle



ywpt_db_conf='/home/mysql/admin/bin/newbin/ywpt_db.conf'
tmp_ywpt_db_conf='/home/mysql/admin/bin/newbin/tmp_ywpt_db.conf'


monitor_db={}
tmp_monitor_db={}
is_completed='N'
g_gmt_create_dt=datetime.datetime.now()
g_gmt_create=g_gmt_create_dt.strftime('%Y%m%d%H%M%S')
g_gmt_modify=g_gmt_create

def main():
    global g_db_id
    g_table_name2='b_db_tablespace_his'
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
           g_db_id=value
           print "DBID:"+g_db_id
    get_db_pass(g_db_id)        
    tmpid=0
    mysqlcur.callproc('gen_identify',('b_biz_snap',monitor_db.get('hid'),tmpid))
    mysqlcur.execute('select @_gen_identify_2')
    data=mysqlcur.fetchone()
    t_snap_id=data[0]
    if g_remark=='ORACLE':
        tns_name = cx_Oracle.makedsn(g_ip_addr,g_port,g_sid)
        oracledb = cx_Oracle.connect(g_username,g_password, tns_name)
        oraclecur=oracledb.cursor()
        tmp_sql = "  SELECT D.TABLESPACE_NAME ,                           \
                    SPACE-NVL(FREE_SPACE,0) USED_SPACE,                  \
                    SPACE SUM_SPACE,                                     \
                    ROUND((1-NVL(FREE_SPACE,0)/SPACE)*100,2) USED_RATE   \
               FROM (SELECT TABLESPACE_NAME,                             \
                            ROUND(SUM(BYTES)/(1024*1024),2) SPACE        \
                       FROM sys.DBA_DATA_FILES                           \
                     GROUP BY TABLESPACE_NAME) D,                        \
                    (SELECT TABLESPACE_NAME,                             \
                            ROUND(SUM(BYTES)/(1024*1024),2) FREE_SPACE   \
                       FROM sys.DBA_FREE_SPACE                           \
                      GROUP BY TABLESPACE_NAME) F                        \
               WHERE  D.TABLESPACE_NAME = F.TABLESPACE_NAME(+) "
        oraclecur.execute(tmp_sql)
        data=oraclecur.fetchall()
        for x in data:
            t_tablespace_name,t_used,t_total,t_per=x
            t_id=monitor_db.get('hid')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
            info=[t_id,t_snap_id,g_db_id,t_tablespace_name,t_used,t_total,t_per,g_completed,g_gmt_create,g_gmt_modify]
            insert_data(g_table_name2,info)
        info=[t_snap_id,g_db_id,g_db_type,'2',g_gmt_create,g_gmt_modify]
        insert_data('b_biz_snap',info)
 
    if g_remark=='MYSQL':
        mysqlremotedb=MySQLdb.connect(host=g_ip_addr, user=g_username,passwd=g_password,db='information_schema',port=int(g_port), charset="utf8")
        remotecur=mysqlremotedb.cursor()
        remotecur.execute("set names utf8")
        tmp_sql="SELECT table_schema,ROUND(SUM((DATA_LENGTH+index_length)/1024/1024)) used ,9999999 total,0 per FROM TABLES \
                   GROUP BY table_schema "
        remotecur.execute(tmp_sql)
        data=remotecur.fetchall()
        for x in data:
            t_tablespace_name,t_used,t_total,t_per=x
            t_id=monitor_db.get('hid')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
            info=[t_id,t_snap_id,g_db_id,t_tablespace_name,t_used,t_total,t_per,g_completed,g_gmt_create,g_gmt_modify] 
            insert_data(g_table_name2,info)
        info=[t_snap_id,g_db_id,g_db_type,'2',g_gmt_create,g_gmt_modify]
        insert_data('b_biz_snap',info)


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
mysqlcur=mysqldb.cursor()


def get_db_pass(t_db_id):
    global g_username
    global g_password,g_completed
    global g_sid,g_db_name,g_port,g_remark,g_ip_addr,g_db_type
    g_completed='N'
    mysqlcur.execute("set names utf8")
    t_sql="SELECT  c.ip_addr,db_username,db_passwd,sid,db_name,PORT,upper(remark),a.db_type FROM b_db_config a,b_dict_def b,b_host_config c WHERE db_id=%s AND a.db_type=b.id AND a.host_id=c.host_id limit 1 "
    mysqlcur.execute(t_sql,(t_db_id,))
    data = mysqlcur.fetchone()
        #hostname
    g_ip_addr,g_username,g_password,g_sid,g_db_name,g_port,g_remark,g_db_type=data


def insert_data(table_name,varlist):
                get_table_column('sys',(table_name,))
                mysqlcur.execute("replace into "+table_name+" ("+table_column_mysql+" ) values ("+var_list+")",(varlist))
                mysqldb.commit()



def get_table_column(table_owner,table_name):
    global table_column
    global table_column_mysql
    global var_list
    global table_column_cnt
    read_conf(tmp_ywpt_db_conf,'tmp')
    mysqldbtmp =  MySQLdb.connect(host=tmp_monitor_db.get('ip'), user=tmp_monitor_db.get('user'),passwd=tmp_monitor_db.get('pass'),db=tmp_monitor_db.get('db'),port=int(tmp_monitor_db.get('port')), charset="utf8")
    tmpcur=mysqldbtmp.cursor()
    tmpcur.execute("set names utf8")
    tmpcur.execute("SELECT column_name FROM  COLUMNS WHERE table_name=%s ORDER BY ORDINAL_POSITION",(table_name))
    data=tmpcur.fetchall()
    table_column=''
    table_column_mysql=''
    table_column_cnt=0
    var_list=''
    for x in data:
       table_column_cnt=table_column_cnt+1
       table_column=table_column+str(x[0])+','
       table_column_mysql=table_column_mysql+'`'+str(x[0])+'`'+','
       var_list=var_list+'%s'+','
    table_column=table_column.rstrip(',')
    table_column_mysql=table_column_mysql.rstrip(',')
    var_list=var_list.rstrip(',')

'''
if __name__=="__main__":
     g_table_name2='b_db_tablespace_his'
     g_db_id=g_type=sys.argv[1]
     get_db_pass(g_db_id)
     #取得id值 传入参数表名，server_id或IP
     tmpid=0
     mysqlcur.callproc('gen_identify',('b_biz_snap',monitor_db.get('hid'),tmpid))
     mysqlcur.execute('select @_gen_identify_2')
     data=mysqlcur.fetchone()
     t_snap_id=data[0]
     if g_remark=='ORACLE':
         tns_name = cx_Oracle.makedsn(g_ip_addr,g_port,g_sid)
         oracledb = cx_Oracle.connect(g_username,g_password, tns_name)
         oraclecur=oracledb.cursor()
         tmp_sql = "  SELECT D.TABLESPACE_NAME ,                           \
                     SPACE-NVL(FREE_SPACE,0) USED_SPACE,                  \
                     SPACE SUM_SPACE,                                     \
                     ROUND((1-NVL(FREE_SPACE,0)/SPACE)*100,2) USED_RATE   \
                FROM (SELECT TABLESPACE_NAME,                             \
                             ROUND(SUM(BYTES)/(1024*1024),2) SPACE        \
                        FROM sys.DBA_DATA_FILES                           \
                      GROUP BY TABLESPACE_NAME) D,                        \
                     (SELECT TABLESPACE_NAME,                             \
                             ROUND(SUM(BYTES)/(1024*1024),2) FREE_SPACE   \
                        FROM sys.DBA_FREE_SPACE                           \
                       GROUP BY TABLESPACE_NAME) F                        \
                WHERE  D.TABLESPACE_NAME = F.TABLESPACE_NAME(+) "
         oraclecur.execute(tmp_sql)
         data=oraclecur.fetchall()
         for x in data:
             t_tablespace_name,t_used,t_total,t_per=x
             t_id=monitor_db.get('hid')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
             info=[t_id,t_snap_id,g_db_id,t_tablespace_name,t_used,t_total,t_per,g_completed,g_gmt_create,g_gmt_modify]
             insert_data(g_table_name2,info)
         info=[t_snap_id,g_db_id,g_db_type,'2',g_gmt_create,g_gmt_modify]
         insert_data('b_biz_snap',info)
 
     if g_remark=='MYSQL':
         mysqlremotedb=MySQLdb.connect(host=g_ip_addr, user=g_username,passwd=g_password,db='information_schema',port=int(g_port), charset="utf8")
         remotecur=mysqlremotedb.cursor()
         remotecur.execute("set names utf8")
         tmp_sql="SELECT table_schema,ROUND(SUM((DATA_LENGTH+index_length)/1024/1024)) used ,9999999 total,0 per FROM TABLES \
                    GROUP BY table_schema "
         remotecur.execute(tmp_sql)
         data=remotecur.fetchall()
         for x in data:
             t_tablespace_name,t_used,t_total,t_per=x
             t_id=monitor_db.get('hid')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
             info=[t_id,t_snap_id,g_db_id,t_tablespace_name,t_used,t_total,t_per,g_completed,g_gmt_create,g_gmt_modify] 
             insert_data(g_table_name2,info)
         info=[t_snap_id,g_db_id,g_db_type,'2',g_gmt_create,g_gmt_modify]
         insert_data('b_biz_snap',info)
''' 

def usage():
    print '''
---------------usage:------------------
python get_tablespace -d dbid
or
python get_tablespace --dbid=dbid
---------------------------------------
'''
         
if __name__=="__main__":
   main()
