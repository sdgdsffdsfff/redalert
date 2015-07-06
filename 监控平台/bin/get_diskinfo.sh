#!/bin/sh

export PATH=$PATH:/usr/bin:/bin
export LC_TIME="POSIX"

FNAME=`hostname`"_disk"
OSNAME=`uname -a |awk '{print $1}' `

if [ $OSNAME = "Linux" ]; then
   df -Pm | grep /dev |awk '{print $3,$4,$5,$6}'| sed 's/%//g'>/tmp/$FNAME.txt
fi
if [ $OSNAME = "AIX" ]; then
   df -Pm | grep /dev |awk '{print $3,$4,$5,$6}'| sed 's/%//g'>/tmp/$FNAME.txt
fi



