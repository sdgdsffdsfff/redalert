#!/bin/sh

export PATH=$PATH:/usr/bin:/bin
export LC_TIME="POSIX"

FNAME=`hostname`"_ssd"
OSNAME=`uname -a |awk '{print $1}' `

tmp_time=`date +'%Y-%m-%d %H:%M:%S'`

rm -rf /tmp/$FNAME.txt


if [ $OSNAME = "Linux" ]; then 
   export LANG="zh_CN.UTF-8"
fi

NF=`/opt/MegaRAID/MegaCli/MegaCli64 -cfgdsply -aALL|grep -n "Solid State Device" | head -1 |awk -F':' '{print $1}'`
SIZE=`/opt/MegaRAID/MegaCli/MegaCli64 -cfgdsply -aALL|awk 'NR<$NF'|grep -B2 "Optimal" |tail -3|head -1 |awk -F':' '{print $2}'`
#echo "The ssd size is$SIZE"
string=` /opt/MegaRAID/MegaCli/MegaCli64 -cfgdsply -aALL|awk 'NR<$NF'|grep -B2 "Optimal" |tail -3|head -1 |cut -d : -f 2 |cut -b 2-5 `
ssd_disk=/dev/`dmesg |grep $string |cut -d '[' -f2|cut -d ']' -f1|sed -n '1p'`
#echo "The ssd fdisk is $ssd_disk"
#以上寻找ssd所在盘符

ssd_disk_number=`/opt/MegaRAID/MegaCli/MegaCli64 -PDList -aALL |grep "Solid State Device"|wc -l`
#echo $ssd_disk_number

ssd=`/opt/MegaRAID/MegaCli/MegaCli64 -PDList -aALL|sed "/Solid State Device/"q |sed -n '$='`
st=`/opt/MegaRAID/MegaCli/MegaCli64 -PDList -aALL|sed -n "1,$ssd p"|tail -50|sed "/Device Id:/"q |sed -n '$='` 
ed=`/opt/MegaRAID/MegaCli/MegaCli64 -PDList -aALL|sed -n "1,$ssd p"|tail -50|sed "/Solid State Device/"q |sed -n '$='`  
#echo $st
#echo $ed

ipos=$[ed-st]
#echo $ipos

for dev in `/opt/MegaRAID/MegaCli/MegaCli64 -PDList -aALL|sed -nr "H;/Solid State Device/{x;s///;s/.*\n(.*)(.*\n){$ipos}/\1/p}"|awk '{print $3}'|sed 's/Media/ /g'`; do
    #寿命
    disk_info1=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Media_Wearout_Indicator"|awk '{print $2"="$4}'`
    #坏块数，初始100，如果有坏块，从1开始，每4个坏块加1
    disk_info2=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Reallocated_Sector_Ct"|awk '{print $2"="$4}'`
    #磁盘写入数
    disk_info3=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Host_Writes_32MiB"|awk '{print $2"="$10}'`
    #可用保留空间%  到10%时。基本不可用
    disk_info4=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Available_Reservd_Space"|awk '{print $2"="$4}'`
    disk_info5=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Wear_Leveling_Count"|awk '{print $2"="$4}'`
    disk_info6=`smartctl -a -d sat+megaraid,$dev $ssd_disk|grep "Device Model:"|awk '{print $3"-"$4"-"$5"-"$6}'`
    echo "disk_type:"$disk_info6"|""disk_path:"$ssd_disk"|""device_id:"$dev"|""disk_stat:"$disk_info1","$disk_info2","$disk_info3","$disk_info4","$disk_info5>>/tmp/$FNAME.txt
done

