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
import curses

##### zhou fei 2014-07-28

G_HOST="127.0.0.1"
G_PORT="3306"
G_USER="root"
G_PASSWORD="123456"
G_MY_SCREEN=curses.initscr()
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

def init_colol():
    curses.init_pair(1, curses.COLOR_RED, curses.COLOR_BLACK) 
    curses.init_pair(2, curses.COLOR_GREEN, curses.COLOR_BLACK)
    curses.init_pair(3, curses.COLOR_YELLOW, curses.COLOR_BLACK)
    curses.init_pair(4, curses.COLOR_BLUE, curses.COLOR_BLACK)
    curses.init_pair(5, curses.COLOR_WHITE, curses.COLOR_BLACK)



def set_colol(obj1,newline,string,colol):
    y,x=obj1.getyx()
    x=x+1
    if newline<>0:
       y=y+newline
       x=1
    obj1.addstr(y,x,string,curses.color_pair(colol))  

def set_colol2(obj1,y,string,colol):
    obj1.addstr(y,1,string,curses.color_pair(colol))
	
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
	try:		   
              G_MY_SCREEN.clear()
              curses.start_color() 
	      init_colol()
	      G_MY_SCREEN.border(0)
	      get_mysql_stat() 
              #G_MY_SCREEN.getch()			  
        finally:
              curses.endwin()  
def get_mysql_stat():
         
         mysqldb = MySQLdb.connect(host=G_HOST, user=G_USER,passwd=str(G_PASSWORD),port=int(G_PORT) ,charset="utf8")
         mysqlcur=mysqldb.cursor()
         global G_GLOBAL_VARIABLES
         global G_GLOBAL_STATUS
         global G_GLOBAL_OLD_STATUS
         t_cnt=0
         t_mysql_version=""
         def get_head():
                 t_head1=''
                 t_head2=''
                 t_head3=''
                 g_y,g_x=G_MY_SCREEN.getmaxyx()
                 t_head1= "*"*(g_x-2)
                 t_head2= "MYSQL TOOL".center((g_x-2),'*')
                 t_head3= "*"*(g_x-2)
                 set_colol(G_MY_SCREEN,1,t_head1,3)
                 set_colol(G_MY_SCREEN,1,t_head2,3)
                 set_colol(G_MY_SCREEN,1,t_head3,3)
                 set_colol(G_MY_SCREEN,1,"Date:",1)
                 set_colol(G_MY_SCREEN,0,datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'),5)
                 set_colol(G_MY_SCREEN,0,"Host:",1)
                 set_colol(G_MY_SCREEN,0,G_HOST,5)
                 set_colol(G_MY_SCREEN,0,"Version:",1)
                 set_colol(G_MY_SCREEN,0,t_mysql_version[0],5)
                 #换行
                 set_colol(G_MY_SCREEN,1,"Database Var:",1)
                 set_colol(G_MY_SCREEN,1,"binlog_format:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('binlog_format').ljust(8),1)	
                 set_colol(G_MY_SCREEN,0,"innodb_adaptive_flushing:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_adaptive_flushing').ljust(8),1)	
                 set_colol(G_MY_SCREEN,0,"innodb_io_capacity:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_io_capacity').ljust(8),1)	
                 set_colol(G_MY_SCREEN,0,"max_connections:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('max_connections').ljust(8),1)	

                 set_colol(G_MY_SCREEN,1,"innodb_flush_log_at_trx_commit:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_flush_log_at_trx_commit').ljust(8),1)  
                 set_colol(G_MY_SCREEN,0,"max_binlog_cache_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(str(int(G_GLOBAL_VARIABLES.get('max_binlog_cache_size'))/1024/1024)+'MB').ljust(8),1)  
                 set_colol(G_MY_SCREEN,0,"innodb_buffer_pool_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(str(int(G_GLOBAL_VARIABLES.get('innodb_buffer_pool_size'))/1024/1024)+'MB').ljust(8),1)  
                 set_colol(G_MY_SCREEN,0,"max_connect_errors:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('max_connect_errors').ljust(8),1)  

                 set_colol(G_MY_SCREEN,1,"innodb_adaptive_hash_index:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_adaptive_hash_index').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"max_binlog_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(str(int(G_GLOBAL_VARIABLES.get('max_binlog_size'))/1024/1024)+'MB').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"max_user_connections:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('max_user_connections').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_flush_method:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_flush_method').ljust(8),1)

                 set_colol(G_MY_SCREEN,1,"innodb_max_dirty_pages_pct:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_max_dirty_pages_pct').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_file_per_table:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(G_GLOBAL_VARIABLES.get('innodb_file_per_table')).ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_read_io_threads:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_read_io_threads').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_write_io_threads:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_write_io_threads').ljust(8),1)	

                 set_colol(G_MY_SCREEN,1,"table_definition_cache:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('table_definition_cache').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"sync_binlog:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(G_GLOBAL_VARIABLES.get('sync_binlog')).ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"table_open_cache:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('table_open_cache').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"thread_cache_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('thread_cache_size').ljust(8),1)	


                 set_colol(G_MY_SCREEN,1,"innodb_log_files_in_group:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,G_GLOBAL_VARIABLES.get('innodb_log_files_in_group').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_lock_wait_timeout:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(G_GLOBAL_VARIABLES.get('innodb_lock_wait_timeout')).ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_log_file_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(str(int(G_GLOBAL_VARIABLES.get('innodb_log_file_size'))/1024/1024)+'MB').ljust(8),1)
                 set_colol(G_MY_SCREEN,0,"innodb_log_buffer_size:".rjust(31),5)
                 set_colol(G_MY_SCREEN,0,(str(int(G_GLOBAL_VARIABLES.get('innodb_log_buffer_size'))/1024/1024)+'MB').ljust(8),1)
                 set_colol(G_MY_SCREEN,1,"-"*(g_x-2),5)

         def set_mysql_title():
             set_colol(G_MY_SCREEN,1,"QPS:".rjust(25),2)
             set_colol(G_MY_SCREEN,0,"TPS:".rjust(20),2)

             set_colol(G_MY_SCREEN,1,"Time:".rjust(19),2)
             set_colol(G_MY_SCREEN,0,"sel".rjust(6),2)
             set_colol(G_MY_SCREEN,0,"ins".rjust(4),2)
             set_colol(G_MY_SCREEN,0,"upd".rjust(4),2)
             set_colol(G_MY_SCREEN,0,"del".rjust(4),2)
             set_colol(G_MY_SCREEN,0,"rep".rjust(4),2)
             
             set_colol(G_MY_SCREEN,0,"lor".rjust(7),2)


         def get_body(G_GLOBAL_OLD_STATUS,mysql_y,G_LOOP):
                  t_star=1
                  t_str=""
                  G_GLOBAL_STATUS['time']=datetime.datetime.now()
                  for t_name in G_GLOBAL_STATUS:
                      if t_name<>'time':
                           t_sql="SELECT variable_value from information_schema.GLOBAL_STATUS  WHERE LOWER(variable_name)=LOWER(%s)"
                           mysqlcur.execute(t_sql,t_name)
                           t_value=mysqlcur.fetchone()
                           G_GLOBAL_STATUS[t_name]=t_value[0]
                  if  (int(G_GLOBAL_OLD_STATUS.get('Com_select')))>0:
                      mysql_y=(G_LOOP % 15)+mysql_y+1
                      set_colol2(G_MY_SCREEN,mysql_y,str(G_GLOBAL_STATUS.get('time').strftime('%Y-%m-%d %H:%M:%S')).rjust(19),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('Com_select'))-int(G_GLOBAL_OLD_STATUS.get('Com_select'))).rjust(6),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('Com_insert'))-int(G_GLOBAL_OLD_STATUS.get('Com_insert'))).rjust(4),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('Com_update'))-int(G_GLOBAL_OLD_STATUS.get('Com_update'))).rjust(4),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('Com_delete'))-int(G_GLOBAL_OLD_STATUS.get('Com_delete'))).rjust(4),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('Com_replace'))-int(G_GLOBAL_OLD_STATUS.get('Com_replace'))).rjust(4),3)
                      set_colol(G_MY_SCREEN,0,str(int(G_GLOBAL_STATUS.get('INNODB_BUFFER_POOL_READ_REQUESTS'))-int(G_GLOBAL_OLD_STATUS.get('INNODB_BUFFER_POOL_READ_REQUESTS'))).rjust(7),3)

                  G_GLOBAL_OLD_STATUS.update(G_GLOBAL_STATUS)
				  
         ###初始化数据库版本 
         mysqlcur.execute("select @@version")
         t_mysql_version=mysqlcur.fetchone()
         ###初始化参数
         for t_name in G_GLOBAL_VARIABLES:
                 t_sql="SELECT variable_value FROM information_schema.GLOBAL_VARIABLES WHERE LOWER(variable_name)=LOWER(%s)"
                 mysqlcur.execute(t_sql,t_name)
                 t_value=mysqlcur.fetchone()
                 G_GLOBAL_VARIABLES[t_name]=t_value[0]


         get_head()
         set_mysql_title()
         mysql_y,mysql_x=G_MY_SCREEN.getyx()
         G_LOOP=1
         while True:
              
              get_body(G_GLOBAL_OLD_STATUS,mysql_y,G_LOOP)
              G_MY_SCREEN.refresh()
              G_LOOP=G_LOOP+1
              time.sleep(1)

def usage():
        print "python showmysql.py -h host -p port -w password -u username -H help"
                 
if __name__=="__main__":
   main()
