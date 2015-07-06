#!/bin/sh

export PATH=$PATH:/usr/bin:/bin
export LC_TIME="POSIX"

FNAME=`hostname`"_net"
OSNAME=`uname -a |awk '{print $1}' `

if [ $OSNAME = "Linux" ]; then
   tmp_time=`date +'%Y-%m-%d %H:%M:%S'`   
   tmp_rec=`ifconfig bond0|grep bytes|awk '{print $2}'|sed 's/bytes://g'|awk '{print $1/1024}'`
   tmp_send=`ifconfig bond0|grep bytes|awk '{print $6}'|sed 's/bytes://g'|awk '{print $1/1024}'`
   echo $tmp_time" "$tmp_rec" "$tmp_send>/tmp/$FNAME.txt
fi

if [ $OSNAME = "AIX" ]; then
   tmp_time=`date +'%Y-%m-%d %H:%M:%S'`
   tmp_rec=`netstat -p ip|grep  "packets received"|awk '{print $1*1500/1024}'`
   tmp_send=`netstat -p ip|grep  "sent from this host"|awk '{print $1*1500/1024}'`
   echo $tmp_time" "$tmp_rec" "$tmp_send>/tmp/$FNAME.txt
fi


