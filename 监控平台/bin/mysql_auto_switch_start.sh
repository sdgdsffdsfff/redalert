#!/bin/bash
step=2 #间隔的秒数
for (( i = 0; i < 3600; i=(i+step) )); do
$(python /home/mysql/admin/bin/newbin/get_mysql_replication.py 40)
$(python /home/mysql/admin/bin/newbin/get_mysql_replication.py 41)
$(python /home/mysql/admin/bin/newbin/mysql_auto_switch.py 40 auto >/tmp/40.log)
$(python /home/mysql/admin/bin/newbin/mysql_auto_switch.py 41 auto >/tmp/41.log)
sleep $step
done
exit 0
