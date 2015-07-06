#!/bin/sh
alert_file=$1
alert_key=$2
db_id=$3

hname=`hostname`

dt=`date`

err_file='/tmp/alert-error_'$db_id'.txt'

file_name='/tmp/alert-'$hname'_'$db_id'.txt'


if [ ! -f $alert_file ]
then
        echo "Can not locate alert log file - Aborting..."
        exit 1
fi


file_size=`ls -l $alert_file  |awk '{print $5}'`

tail -3 $alert_file > $err_file

ERRORCNT=`egrep -ci "$alert_key" $err_file`

rm -rf $file_name

touch $file_name

if [ $ERRORCNT -gt 0 ]; then
   OSNAME=`uname -a |awk '{print $1}' `
   if [ $OSNAME = "Linux" ]; then
      tmd5=`md5sum $err_file|cut -d ' ' -f1`
   fi
   if [ $OSNAME = "AIX" ]; then
     tmd5=`csum  $err_file|cut -d ' ' -f1`
   fi
   egrep -i "$alert_key" $err_file|awk -v var=${tmd5} '{print var"$$$$$" $0}'> $file_name
fi

# backup alertlog when it is too large
if [ $file_size -gt 50000000 ]; then
    echo "xxxxxxxxx$dt \$\$\$\$\$ alert file is too large">> $file_name
fi

exit 0

