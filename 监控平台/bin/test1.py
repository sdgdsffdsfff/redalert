# -*- coding: utf-8 -*-
import os,MySQLdb
import getopt
import sys, time,datetime
import socket

G_HOST="127.0.0.1"
G_PORT="3306"
G_USER="root"
G_PASSWORD="123456"
G_GLOBAL_VARIABLES={'binlog_format':0,
'max_binlog_cache_size':0,
'max_binlog_size':0,
'max_connect_errors':0,
'max_connections':0,
'max_user_connections':0,
'open_files_limit':0,
'sync_binlog':0,
'table_definition_cache':0,
'table_open_cache':0,
'thread_cache_size':0,
'innodb_adaptive_flushing':0,
'innodb_adaptive_hash_index':0,
'innodb_buffer_pool_size':0,
'innodb_file_per_table':0,
'innodb_flush_log_at_trx_commit':0,
'innodb_flush_method':0,
'innodb_io_capacity':0,
'innodb_lock_wait_timeout':0,
'innodb_log_buffer_size':0,
'innodb_log_file_size':0,
'innodb_log_files_in_group':0,
'innodb_max_dirty_pages_pct':0,
'innodb_open_files':0,
'innodb_read_io_threads':0,
'innodb_thread_concurrency':0,
'innodb_write_io_threads':0,
'port':0}

def main():
        global G_HOST,G_PORT,G_USER,G_PASSWORD

        try:
                opts, args = getopt.getopt(sys.argv[1:], "Hh:p:u:w:", ["help", "host=","port=","user=","password="])
                if len(sys.argv)==1: 
                   usage()
                   sys.exit(2)
        except getopt.GetoptError:
                # print help information and exit:
                usage()
                sys.exit(2)
        for o,a in opts:
                if o in ("-H","--help"):
                   usage()
                   sys.exit()
                if o in ("-h","--host"):
                   G_HOST=a
                if o in ("-p","--port"):
                   G_PORT=a
                if o in ("-u","--user"):
                   G_USER=a
                if o in ("-w","--password"):
                   G_PASSWORD=a
        while True:
            time.sleep(1)
            get_mysql_stat()
            show_info()
            sys.stdout.flush()
            
def show_info():
    t_head2= "MYSQL TOOL".center(150,'*')
    set_colol(6,35,40,t_head2)
    #print datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    sys.stdout.write("\n")
    #set_colol(6,31,40,"Date:")
    date_time=datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    host_name=socket.gethostname()
    host_ip=socket.gethostbyname(host_name)     
    set_colol(6,31,40," DateTime:")
    sys.stdout.write("%s"% (date_time))
    set_colol(6,31,40,"   Host:")
    sys.stdout.write("%s"% (host_name))
    set_colol(6,31,40,"   Ip:")
    sys.stdout.write("%s"% (host_ip)) 
    set_colol(6,31,40," 数据库参数:")
    set_colol(6,32,40,"binlog_format:")
    sys.stdout.write("%s"% (G_GLOBAL_VARIABLES.get('binlog_format')))
    set_colol(6,32,40,"innodb_adaptive_flushing:")
    sys.stdout.write("%s \r "% (G_GLOBAL_VARIABLES.get('innodb_adaptive_flushing')))
    #set_colol(6,32,40,"max_connections:")
    #sys.stdout.write("%s \r"% (G_GLOBAL_VARIABLES.get('max_connections')))
    #set_colol(6,32,40,"innodb_io_capacity:")
    #sys.stdout.write("%s \r"% (G_GLOBAL_VARIABLES.get('innodb_io_capacity')))
    #set_colol(6,32,40,"innodb_flush_log_at_trx_commit:")
    #sys.stdout.write("%s \r"% (G_GLOBAL_VARIABLES.get('innodb_flush_log_at_trx_commit')))
    
def show_mysql():
    set_colol(6,31,40,"   Version:")
    sys.stdout.write(" %s  \r"% (t_mysql_version))
    #set_colol(6,31,40," 数据库参数: ")
    #print "\r"    
      
        
def set_colol(c1,c2,c3,c4):
         #\033[0m 默认输出
         #格式：\033[显示方式;前景色;背景色m
         #前景色            背景色           颜色
         #30                40              黑色
         #31                41              红色
         #32                42              绿色
         #33                43              黃色
         #34                44              蓝色
         #35                45              紫红色
         #36                46              青蓝色
         #37                47              白色
         #显示方式           意义
         #0                终端默认设置
         #1                高亮显示
         #4                使用下划线
         #5                闪烁
         #7                反白显示
         #8                不可见

        t_colol='\033['+str(c1)+';'+str(c2)+';'+str(c3)+'m'+c4+'\033[0m'
        print t_colol,

def get_mysql_stat():
         mysqldb = MySQLdb.connect(host=G_HOST, user=G_USER,passwd=str(G_PASSWORD),port=int(G_PORT) ,charset="utf8")
         mysqlcur=mysqldb.cursor()
         global t_old_value
         global t_new_value
         global G_GLOBAL_VARIABLES
         global t_mysql_version
         t_old_value=0
         t_new_value=0
         t_cnt=0
         t_mysql_version=""
         t_name=''

         ###初始化数据库版本
         mysqlcur.execute("select @@version")
         t_mysql_version=mysqlcur.fetchone()
         ###初始化参数
         for t_name in G_GLOBAL_VARIABLES:
                 t_sql="SELECT variable_value FROM information_schema.GLOBAL_VARIABLES WHERE LOWER(variable_name)=LOWER(%s)"
                 mysqlcur.execute(t_sql,(t_name,))
                 t_value=mysqlcur.fetchone()
                 G_GLOBAL_VARIABLES[t_name]=t_value[0]




def usage():
    print '''
---------------usage:------------------
python showmysql.py -h host -p port -w password -u username -H help
or
python showmysql.py --host=host --port=port --password=password --user=username --help=help
---------------------------------------
'''


if __name__=="__main__":
   main()

