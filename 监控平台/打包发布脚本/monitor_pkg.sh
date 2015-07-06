#!/bin/sh

cd /ywpt/pkg/com-tyyd-ywpt-deploy
mvn clean install -e -Dmaven.test.skip -Dautoconfig.strict=false -Dautoconfig.interactive=false -Dautoconfig.userProperties=/ywpt/properties/monitor.properties

# cp
rm -f /ywpt/war/monitor.war
cp /ywpt/pkg/com-tyyd-ywpt-war/target/monitor.war /ywpt/war/monitor.war
echo 'copy /ywpt/pkg/com-tyyd-ywpt-war/target/monitor.war to /ywpt/war/monitor.war'

