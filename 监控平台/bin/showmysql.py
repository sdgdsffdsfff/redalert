# -*- coding: utf-8 -*- 
import MySQLdb
import sys
import subprocess
import time
import subprocess
import os
import datetime
import random
import socket
from fabric.api import env,run,put,get,local 
import getopt

##### zhou fei 2014-07-28

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

G_GLOBAL_STATUS={'time':datetime.datetime.now(),
'THREADS_CONNECTED':0,
'THREADS_RUNNING':0,
'Queries':0,
'Slow_queries':0,
'SELECT_SCAN':0,
'SELECT_RANGE':0,
'SELECT_RANGE_CHECK':0,
'SELECT_FULL_JOIN':0,
'SELECT_FULL_RANGE_JOIN':0,
'SORT_SCAN':0,
'SORT_RANGE':0,
'SORT_MERGE_PASSES':0,
'INNODB_ROWS_READ':0,
'INNODB_ROWS_INSERTED':0,
'INNODB_ROWS_DELETED':0,
'INNODB_ROWS_UPDATED':0,
'SORT_ROWS':0,
'INNODB_BUFFER_POOL_PAGES_DIRTY':0,
'INNODB_BUFFER_POOL_PAGES_FREE':0,
'INNODB_BUFFER_POOL_PAGES_TOTAL':0,
'INNODB_BUFFER_POOL_PAGES_DATA':0,
'COM_COMMIT':0,
'Innodb_os_log_written':0,
'HANDLER_COMMIT':0,
'BYTES_SENT':0,
'BYTES_RECEIVED':0,
'INNODB_BUFFER_POOL_READ_REQUESTS':0,
'INNODB_BUFFER_POOL_READS':0,
'INNODB_DATA_READS':0,
'Innodb_data_writes':0,
'Innodb_buffer_pool_write_requests':0,
'INNODB_DATA_FSYNCS':0,
'INNODB_OS_LOG_FSYNCS':0,
'Innodb_log_writes':0,
'Table_locks_immediate':0,
'Table_locks_waited':0,
'Created_tmp_disk_tables':0,
'Created_tmp_files':0,
'Created_tmp_tables':0,
'Com_insert':0,
'Com_select':0,
'Com_update':0,
'Com_delete':0,
'Com_replace':0}

G_GLOBAL_OLD_STATUS={'time':datetime.datetime.now(),
'THREADS_CONNECTED':0,
'THREADS_RUNNING':0,
'Queries':0,
'Slow_queries':0,
'SELECT_SCAN':0,
'SELECT_RANGE':0,
'SELECT_RANGE_CHECK':0,
'SELECT_FULL_JOIN':0,
'SELECT_FULL_RANGE_JOIN':0,
'SORT_SCAN':0,
'SORT_RANGE':0,
'SORT_MERGE_PASSES':0,
'INNODB_ROWS_READ':0,
'INNODB_ROWS_INSERTED':0,
'INNODB_ROWS_DELETED':0,
'INNODB_ROWS_UPDATED':0,
'SORT_ROWS':0,
'INNODB_BUFFER_POOL_PAGES_DIRTY':0,
'INNODB_BUFFER_POOL_PAGES_FREE':0,
'INNODB_BUFFER_POOL_PAGES_TOTAL':0,
'INNODB_BUFFER_POOL_PAGES_DATA':0,
'COM_COMMIT':0,
'Innodb_os_log_written':0,
'HANDLER_COMMIT':0,
'BYTES_SENT':0,
'BYTES_RECEIVED':0,
'INNODB_BUFFER_POOL_READ_REQUESTS':0,
'INNODB_BUFFER_POOL_READS':0,
'INNODB_DATA_READS':0,
'Innodb_data_writes':0,
'Innodb_buffer_pool_write_requests':0,
'INNODB_DATA_FSYNCS':0,
'INNODB_OS_LOG_FSYNCS':0,
'Innodb_log_writes':0,
'Table_locks_immediate':0,
'Table_locks_waited':0,
'Created_tmp_disk_tables':0,
'Created_tmp_files':0,
'Created_tmp_tables':0,
'Com_insert':0,
'Com_select':0,
'Com_update':0,
'Com_delete':0,
'Com_replace':0}

def set_colol(c1,c2,c3,c4):
	t_colol='\033['+str(c1)+';'+str(c2)+';'+str(c3)+'m'+c4+'\033[0m'
	print t_colol,
	

def main():
	global G_HOST,G_PORT,G_USER,G_PASSWORD

	try:
		opts, args = getopt.getopt(sys.argv[1:], "Hh:p:u:w:", ["help", "host=","port=","user=","password="])

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
	get_mysql_stat()     
	  
def get_mysql_stat():
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
	 
	 mysqldb = MySQLdb.connect(host=G_HOST, user=G_USER,passwd=str(G_PASSWORD),port=int(G_PORT) ,charset="utf8")
	 mysqlcur=mysqldb.cursor()
	 global G_GLOBAL_VARIABLES
         global G_GLOBAL_STATUS
         global G_GLOBAL_OLD_STATUS
	 t_cnt=0
	 t_mysql_version=""
	 
	 ###初始化数据库版本 
	 mysqlcur.execute("select @@version")
	 t_mysql_version=mysqlcur.fetchone()
	 ###初始化参数
	 for t_name in G_GLOBAL_VARIABLES:
		 t_sql="SELECT variable_value FROM information_schema.GLOBAL_VARIABLES WHERE LOWER(variable_name)=LOWER(%s)"
		 mysqlcur.execute(t_sql,t_name)
		 t_value=mysqlcur.fetchone()
		 G_GLOBAL_VARIABLES[t_name]=t_value[0]

	 def get_head():
		 t_head1=''
		 t_head2=''
		 t_head3=''
		 t_head1= "*"*150
		 t_head2= "MYSQL TOOL".center(150,'*')
		 t_head3= "*"*150
		 set_colol(6,35,40,t_head1+"\n")
		 set_colol(6,35,40,t_head2+"\n")
		 set_colol(6,35,40,t_head3+"\n")
		 set_colol(6,31,40,"Date:")
		 print datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
		 set_colol(6,31,40,"   Host:")
		 print socket.gethostname(),
		 set_colol(6,31,40,"   Ip:")
		 print socket.gethostbyname(socket.gethostname()),
		 set_colol(6,31,40,"   Version:")
		 print t_mysql_version[0]
		 set_colol(6,31,40," 数据库参数:\n")

		 set_colol(6,32,40,"binlog_format:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('binlog_format').ljust(8))
		 set_colol(6,32,40,"innodb_adaptive_flushing:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_adaptive_flushing').ljust(8))
		 set_colol(6,32,40,"max_connections:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('max_connections').ljust(8))
		 set_colol(6,32,40,"innodb_io_capacity:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_io_capacity').ljust(8)+"\n")



		 set_colol(6,32,40,"innodb_flush_log_at_trx_commit:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_flush_log_at_trx_commit').ljust(8))
		 set_colol(6,32,40,"max_binlog_cache_size:".rjust(25))
		 set_colol(6,31,40,(str(int(G_GLOBAL_VARIABLES.get('max_binlog_cache_size'))/1024/1024)+'MB').ljust(8))
		 set_colol(6,32,40,"innodb_buffer_pool_size:".rjust(25))
		 set_colol(6,31,40,(str(int(G_GLOBAL_VARIABLES.get('innodb_buffer_pool_size'))/1024/1024)+'MB').ljust(8))
		 set_colol(6,32,40,"max_connect_errors:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('max_connect_errors').ljust(8)+"\n")


		 set_colol(6,32,40,"innodb_adaptive_hash_index:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_adaptive_hash_index').ljust(8))
		 set_colol(6,32,40,"max_binlog_size:".rjust(25))
		 set_colol(6,31,40,(str(int(G_GLOBAL_VARIABLES.get('max_binlog_size'))/1024/1024)+'MB').ljust(8))
		 set_colol(6,32,40,"max_user_connections:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('max_user_connections').ljust(8))
		 set_colol(6,32,40,"innodb_flush_method:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_flush_method').ljust(8)+"\n")



		 set_colol(6,32,40,"innodb_max_dirty_pages_pct:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_max_dirty_pages_pct').ljust(8))
		 set_colol(6,32,40,"innodb_file_per_table:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_file_per_table').ljust(8))
		 set_colol(6,32,40,"innodb_read_io_threads:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_read_io_threads').ljust(8))
		 set_colol(6,32,40,"innodb_write_io_threads:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_write_io_threads').ljust(8)+"\n")

		 set_colol(6,32,40,"table_definition_cache:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('table_definition_cache').ljust(8))
		 set_colol(6,32,40,"sync_binlog:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('sync_binlog').ljust(8))
		 set_colol(6,32,40,"table_open_cache:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('table_open_cache').ljust(8))
		 set_colol(6,32,40,"thread_cache_size:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('thread_cache_size').ljust(8)+"\n")
		 
		 set_colol(6,32,40,"innodb_log_files_in_group:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_log_files_in_group').ljust(8))
		 set_colol(6,32,40,"innodb_lock_wait_timeout:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_lock_wait_timeout').ljust(8))
		 set_colol(6,32,40,"innodb_log_file_size:".rjust(25))
		 set_colol(6,31,40,(str(int(G_GLOBAL_VARIABLES.get('innodb_log_file_size'))/1024/1024)+'MB').ljust(8))
		 set_colol(6,32,40,"innodb_log_buffer_size:".rjust(25))
		 set_colol(6,31,40,(str(int(G_GLOBAL_VARIABLES.get('innodb_log_buffer_size'))/1024/1024)+'MB').ljust(8)+"\n")

		 set_colol(6,32,40,"innodb_thread_concurrency:".rjust(31))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_thread_concurrency').ljust(8))
		 set_colol(6,32,40,"innodb_open_files:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('innodb_open_files').ljust(8))
		 set_colol(6,32,40,"open_files_limit:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('open_files_limit').ljust(8))
		 set_colol(6,32,40,"port:".rjust(25))
		 set_colol(6,31,40,G_GLOBAL_VARIABLES.get('port').ljust(8)+"\n")




                 #第一行
		 set_colol(6,31,40,"QPS:".rjust(6))
                 set_colol(6,31,40,"TPS:".rjust(19))
                 set_colol(6,31,40,"----innodb bp  pages status----"+"\n")
                 #第二行
                 #QPS
                 set_colol(4,31,40,"sel".rjust(6))
                 #TPS
                 set_colol(4,31,40,"ins".rjust(4))
                 set_colol(4,31,40,"upd".rjust(4))
                 set_colol(4,31,40,"del".rjust(4))
                 set_colol(4,31,40,"rep".rjust(4))
                 #innodb bp pages
                 set_colol(4,31,40,"data".rjust(7))
                 set_colol(4,31,40,"free".rjust(7))
                 set_colol(4,31,40,"dirty".rjust(7))
                 set_colol(4,31,40,"flush".rjust(7)+"\n")
 

	 def get_body(G_GLOBAL_OLD_STATUS):
		  t_star=1
		  t_str=""
                  G_GLOBAL_STATUS['time']=datetime.datetime.now()
                  for t_name in G_GLOBAL_STATUS:
                      if t_name<>'time':
                           t_sql="SELECT variable_value from information_schema.GLOBAL_STATUS  WHERE LOWER(variable_name)=LOWER(%s)"
                           mysqlcur.execute(t_sql,t_name)
                           t_value=mysqlcur.fetchone()
                           G_GLOBAL_STATUS[t_name]=t_value[0]
                  if  (int(G_GLOBAL_OLD_STATUS.get('Com_select')))==0:
                      set_colol(6,32,40,"\n")
                  else:
                      set_colol(6,32,40,str(int(G_GLOBAL_STATUS.get('Com_select'))-int(G_GLOBAL_OLD_STATUS.get('Com_select'))).rjust(6))
                      set_colol(6,32,40,str(int(G_GLOBAL_STATUS.get('Com_insert'))-int(G_GLOBAL_OLD_STATUS.get('Com_insert'))).rjust(4))
                      set_colol(6,32,40,str(int(G_GLOBAL_STATUS.get('Com_update'))-int(G_GLOBAL_OLD_STATUS.get('Com_update'))).rjust(4))
                      set_colol(6,32,40,str(int(G_GLOBAL_STATUS.get('Com_delete'))-int(G_GLOBAL_OLD_STATUS.get('Com_delete'))).rjust(4))
                      set_colol(6,32,40,str(int(G_GLOBAL_STATUS.get('Com_replace'))-int(G_GLOBAL_OLD_STATUS.get('Com_replace'))).rjust(4)+"\n")
                  
		  G_GLOBAL_OLD_STATUS.update(G_GLOBAL_STATUS)
	 while True:
		  try:
			 if (t_cnt % 15)==0:
                            os.system('clear')
	                    get_head()
			 get_body(G_GLOBAL_OLD_STATUS)
			 t_cnt=t_cnt+1
			 #sys.stdout.flush()
			 time.sleep(1)
		  except:
		  	print '\033[0m'
	                sys.exit()
def usage():
	print "python showmysql.py -h host -p port -w password -u username -H help"
		 
if __name__=="__main__":
   main()
