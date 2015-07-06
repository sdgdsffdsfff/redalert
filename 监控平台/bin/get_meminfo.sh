#!/bin/sh

export PATH=$PATH:/usr/bin:/bin
export LC_TIME="POSIX"

FNAME=`hostname`"_mem"
OSNAME=`uname -a |awk '{print $1}' `

if [ $OSNAME = "Linux" ]; then
   # mem-(total,used,free,buffers,cached) swap-(total,used,free,swapin swapout)
   tmp_mem=`free -m|grep Mem|awk '{print $2,$3,$4,$6,$7}'`
   tmp_swap=`free -m|grep Swap|awk '{print $2,$3,$4}'`
   tmp_swap_inout=`vmstat 1 3 -SM|tail -1|awk '{  print $7,$8}'`
   tmp_time=`date +'%Y-%m-%d %H:%M:%S'`
   tmp_value=$OSNAME" "$tmp_time" "$tmp_mem" "$tmp_swap" "$tmp_swap_inout
   echo $tmp_value>/tmp/$FNAME.txt
fi
if [ $OSNAME = "AIX" ]; then
   # mem-(total,used,free) swap-(total,used,free)
   tmp_mem=`svmon -G -O unit=MB|grep memory|awk '{print $2,$3,$4}'`
   tmp_swap=`svmon -G -O unit=MB|grep "pg space"|awk '{print $3,$4,$3-$4}'`
   tmp_swap_inout=`vmstat 1 3|tail -1|awk '{print $6,$7}'`
   tmp_time=`date +'%Y-%m-%d %H:%M:%S'`
   tmp_value=$OSNAME" "$tmp_time" "$tmp_mem" "$tmp_swap" "$tmp_swap_inout
   echo $tmp_value>/tmp/$FNAME.txt
fi



