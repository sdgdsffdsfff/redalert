#!/bin/sh

#build
cd /ywpt/pkg/schedule/com-tyyd-ywpt-schedule-deploy
mvn clean install -e -Dmaven.test.skip -Dautoconfig.strict=false -Dautoconfig.interactive=false  -Dautoconfig.userProperties=/ywpt/properties/monitor.properties 

# cp
rm -f /ywpt/war/warn_schedule.war
cp /ywpt/pkg/schedule/com-tyyd-ywpt-schedule-war/target/warn_schedule.war /ywpt/war/warn_schedule.war
echo 'cp /ywpt/pkg/schedule/com-tyyd-ywpt-schedule-war/target/warn_schedule.war /ywpt/war/warn_schedule.war'


