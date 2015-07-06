#!/bin/sh

export PATH=$PATH:/usr/bin:/bin
export LC_TIME="POSIX"

FNAME=`hostname`"_cpu"
OSNAME=`uname -a |awk '{print $1}' `
LOAD=` w|grep 'load'|awk '{print $(NF-1)}'|cut -f 1 -d","`

tmp_time=`date +'%Y-%m-%d %H:%M:%S'`

if [ $OSNAME = "Linux" ]; then 
   export LANG="zh_CN.UTF-8"
   tmp=`sar -u 1 3|tail -1|awk '{ print $3,$5,$6,$8}'`
   tmp=$tmp_time" "$tmp" "$LOAD
   echo $tmp>/tmp/$FNAME.txt
fi
if [ $OSNAME = "AIX" ]; then
    tmp=`vmstat 1 3|tail -1|awk '{print $14,$15,$17,$16}'`
    #tmp=`sar|awk '{a[NR]=$0} END{print a[NR=FNR-2]}'|awk '{ print $2,$3,$4,$5}'`
    tmp=$tmp_time" "$tmp" "$LOAD
    echo $tmp>/tmp/$FNAME.txt
fi
