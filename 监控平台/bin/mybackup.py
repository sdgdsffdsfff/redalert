# -*- coding: utf-8 -*- 
import MySQLdb
from fabric.api import env,run,put,get,local
import getopt
import aes 
import sys
import os

ywpt_db_conf='/home/mysql/admin/bin/newbin/ywpt_db.conf'
tmp_ywpt_db_conf='/home/mysql/admin/bin/newbin/tmp_ywpt_db.conf'

monitor_db={}
tmp_monitor_db={}
is_completed='N'

def main():
    global h_ip,h_port,b_type
    try:
        opts, args = getopt.getopt(sys.argv[1:], "Hh:i:p:t:", ["help",
                "ip=","port=","type="])
#        if len(sys.argv)!=3: 
#           usage()
#           sys.exit(2)
    except getopt.GetoptError:
        usage()
        sys.exit(2)
    for name,value in opts:
        if name in ("-H","--help"):
           usage()
           sys.exit()
        if name in ("-h","--help"):
           usage()
           sys.exit()
        if name in ("-i","--ip"):
           h_ip=value
           print "HOST_IP:"+h_ip
           print len(h_ip)
        if name in ("-p","--port"):
           h_port=value
           print "HOST_PORT:"+h_port
        if name in ("-t","--type"):
           b_type=value
           print "BAK_TYPE:"+b_type
    connect_ssh(h_ip)


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




#取得机器密码
def get_server_pass(t_ip_addr):
    global ip_addr
    global username
    global password
    global hostname,host_id,rootaccount,rootpasswd
    ip_addr=t_ip_addr
    mysqlcur.execute("set names utf8")
    t_sql="select user_account,user_passwd,host_name,host_id,root_account,IFNULL(root_passwd,'GFyBYZKLwVrhDj0Sh5x9uu==') root_passwd from b_host_config where ip_addr=%s limit 1"
    mysqlcur.execute(t_sql,(ip_addr,))
    data = mysqlcur.fetchone()
        #hostname 一定要设对，不然后续获得文件名会出错
    if data==None:
       print 'Fetch password failed'
       sys.exit(2)
    username,password,hostname,host_id,rootaccount,rootpasswd=data
    print rootpasswd
    rootpasswd=aes.jiemi(rootpasswd)

def full_backup():
   # put('/home/mysql/admin/bin/newbin/bakscripts/bak_uic.sh','/tmp')
   # run('chmod +x /tmp/bak_uic.sh')
   # run('/tmp/bak_uic.sh')
# run('/tmp/fullback_mysql.sh '+port)
#   get('/tmp/'+hostname+'_disk.txt','/tmp/'+ip_addr+'_disk.txt')
#   local('rm -rf '+'/tmp/'+ip_addr+'_disk.txt')
    print "Full backup"


def incr_backup():
    print "Incr backup"

def connect_ssh(g_ip):
    host_ip=g_ip
    #根据传入IP取得对应服务器访问的账号等信息
    get_server_pass(host_ip)
    env.user=rootaccount
    env.password = rootpasswd
    env.host_string=ip_addr+':22'
    if b_type=='full':
            full_backup() 
    if b_type=='incr':
            incr_backup()


def usage():

    print '''
---------------usage:------------------
python mybackup.py -i ip -p port -t full|incr
or
python mybackup.py --host=ip --port=port --type=full|incr
---------------------------------------
'''

if __name__=="__main__":

   main()

