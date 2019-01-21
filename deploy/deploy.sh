#!/usr/bin/env bash

targetUserAndHost="ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com"
targetBaseDirectory="/opt/phtl/weather-api/"
artifact="weather-api-0.0.1-SNAPSHOT.jar"

echo "Local: Building artifact"
mvn -f ../pom.xml clean install -DskipTests=true

echo "Remote: + install directory / set permission"
ssh -i phtl.pem $targetUserAndHost 'sudo mkdir -p '"$targetBaseDirectory"
ssh -i phtl.pem $targetUserAndHost 'sudo chown -R ec2-user:ec2-user '"$targetBaseDirectory"

echo "Remote: Killing already running phtl-weather-api, if any"
ssh -i phtl.pem $targetUserAndHost 'pkill java'

echo "Local ==> Remote: Copying $artifact"
scp -i phtl.pem ../target/$artifact $targetUserAndHost:$targetBaseDirectory

echo "Remote: Starting API"
ssh -i phtl.pem $targetUserAndHost 'nohup java -jar -Duser.timezone=UTC -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=ec2 '"$targetBaseDirectory$artifact"' &> '"$targetBaseDirectory"'nohup.out'