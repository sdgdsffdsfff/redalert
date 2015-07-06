#!/bin/sh
pidfile="/var/run/pinger.$1..$2.pid"
if [ -f $pidfile ]
then
   kill -9 `cat $pidfile` > /dev/null 2>&1
fi
echo "$$" > $pidfile

ipaddr=$1
port=$2
mysqlstat=`/usr/bin/mysql -u"rnd" -p"P1WD#xyoP" -h$ipaddr -P$port  --skip-column-names   -e"select @@read_only" `


if [ $mysqlstat == "1" ]; then
   rm -f $pidfile
   /usr/bin/mysql -u"rnd" -p"P1WD#xyoP" -h$127.0.0.1 -P4444 
   echo $?
fi


if [ $mysqlstat == "0" ]; then
   rm -f $pidfile
   echo "up"
   echo $?
fi

