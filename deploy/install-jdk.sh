#!/usr/bin/env bash

echo "Downloading Oracle JDK 8 ... "
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.rpm'
echo "Installing Oracle JDK 8 ... "
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo yum install -y jdk-8u141-linux-x64.rpm'
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'rm -f ~/jdk-8u141-linux-x64.rpm'
