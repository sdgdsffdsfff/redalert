#!/bin/sh


#build
cd /ywpt/pkg/report/com-tyyd-ywpt-report-deploy
mvn clean install -e -Dmaven.test.skip -Dautoconfig.strict=false -Dautoconfig.interactive=false  -Dautoconfig.userProperties=/ywpt/properties/monitor.properties 

# cp
rm -f /ywpt/war/report.war
cp /ywpt/pkg/report/com-tyyd-ywpt-report-war/target/report.war /ywpt/war/report.war
echo 'copy /ywpt/pkg/report/com-tyyd-ywpt-report-war/target/report.war /ywpt/war/report.war '

