# -*- coding: utf-8 -*- 
import json
import pycurl
import cStringIO
import traceback
import time
import datetime
import random
import sys
import MySQLdb
import os
import commands

##  zhou fei 2014-11-04
ywpt_db_conf='/home/mysql/admin/bin/newbin/ywpt_db.conf'
tmp_ywpt_db_conf='/home/mysql/admin/bin/newbin/tmp_ywpt_db.conf'

monitor_db={}
tmp_monitor_db={}
is_completed='N'
g_gmt_create_dt=datetime.datetime.now()
g_gmt_create=g_gmt_create_dt.strftime('%Y%m%d%H%M%S')
g_gmt_modify=g_gmt_create


hbase_node={}
hbase_50070={}

def read_conf(conf_file,type):
    file_hand=open(conf_file)
    for line in file_hand:
        line=line.strip()
        (name,value)=line.split(':')
        name=name.upper()
        if type=='ywpt':
            monitor_db[name]=value
        if type=='tmp':
            tmp_monitor_db[name]=value
        if type=='hbase':
            hbase_node[name]=value
    file_hand.close()


def read_hadoop_ip_port_key(nodeip,nodeport,key):
    buf = cStringIO.StringIO()
    c = pycurl.Curl()
    tmp_url='http://'+nodeip+':'+nodeport+'/jmx?qry='+key
    c.setopt(c.URL, tmp_url)
    c.setopt(c.WRITEFUNCTION, buf.write)
    c.perform()
    tmp_json_str=json.loads(buf.getvalue())
    keyname=tmp_json_str.keys()
    json_str=tmp_json_str[keyname[0]][0]
    print json_str       
    buf.close()



def read_namenode_status(namenode_ip):
    buf = cStringIO.StringIO()
    c = pycurl.Curl()
    tmp_url='http://'+namenode_ip+':50070/jmx'
    c.setopt(c.URL, tmp_url)
    c.setopt(c.WRITEFUNCTION, buf.write)
    c.perform()
    json_str=json.loads(buf.getvalue())
    buf.close()
    keyname=json_str.keys()
    for i in range(0,len(json_str[keyname[0]])):
           hbase_50070[json_str[keyname[0]][i]["name"]]=json_str[keyname[0]][i]

def insert_data(table_name,varlist):
                get_table_column('sys',table_name)
                mysqlcur.execute("replace into "+table_name+" ("+table_column_mysql+" ) values ("+var_list+")",(varlist))
                mysqldb.commit()


def get_table_column(table_owner,table_name):
    global table_column
    global table_column_mysql
    global var_list
    global table_column_cnt
    read_conf(tmp_ywpt_db_conf,'tmp')
    mysqldbtmp =  MySQLdb.connect(host=tmp_monitor_db.get('IP'), user=tmp_monitor_db.get('USER'),passwd=tmp_monitor_db.get('PASS'),db=tmp_monitor_db.get('DB'),port=int(tmp_monitor_db.get('PORT')), charset="utf8")
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



if __name__=="__main__":
       g_db_id=sys.argv[1]
       read_conf('/home/mysql/admin/bin/newbin/hbase_conf','hbase')
       read_conf(ywpt_db_conf,'ywpt')
       mysqldb = MySQLdb.connect(host=monitor_db.get('IP'), user=monitor_db.get('USER'),passwd=monitor_db.get('PASS'),db=monitor_db.get('DB'),port=int(monitor_db.get('PORT')) ,charset="utf8")
       mysqlcur=mysqldb.cursor()
       try:
           read_namenode_status(hbase_node['NAMENODE'])
       except Exception as e:
           try:
              read_namenode_status(hbase_node['SECONDARYNAMENODE'])
           except Exception as e:
                  tmsg="namenode down!!!Please check!!!"
                  t_tmd5="Hbasenamenode"
                  t_id=monitor_db.get('HID')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
                  info=[t_id,g_db_id,tmsg,is_completed,t_tmd5,g_gmt_create,g_gmt_modify,'0','7']
                  #6:hbase namenode down
                  insert_data('b_error_msg',info)
       dead_nodes=json.loads(hbase_50070['hadoop:service=NameNode,name=NameNodeInfo']['DeadNodes'])
       if len(dead_nodes.keys()) >=1:
           tmsg="datanode down!!!Please check!!!"+str(dead_nodes)
           t_tmd5="Hbasedatanode"
           t_id=monitor_db.get('HID')+datetime.datetime.now().strftime('%Y%m%d%H%M%S')+str(random.randint(10000,90000))
           info=[t_id,g_db_id,tmsg,is_completed,t_tmd5,g_gmt_create,g_gmt_modify,'0','8']
           #7:hbase datanode down
           insert_data('b_error_msg',info)   
       ########################get dead_nodes 
       #print hbase_50070['hadoop:service=NameNode,name=NameNodeInfo']['DeadNodes']
       #print hbase_50070['hadoop:service=NameNode,name=NameNodeInfo']['LiveNodes']
       ##
       read_hadoop_ip_port_key('192.168.10.36','60010',"java.lang:type=GarbageCollector,name=ConcurrentMarkSweep")

