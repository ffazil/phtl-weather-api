#!/usr/bin/env bash

echo "Installing NGINX ... "
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo amazon-linux-extras install nginx1.12'

echo "Copying NGINX configuration ... "
scp -i phtl.pem nginx.conf ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com:~/.
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo mv nginx.conf /etc/nginx'
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'rm -f nginx.conf'

scp -i phtl.pem phtl-weather-api.conf ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com:~/.
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo mv phtl-weather-api.conf /etc/nginx/conf.d/'
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'rm -f phtl-weather-api.conf'

echo "Starting NGINX ... "
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo chkconfig nginx on'
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo service nginx stop'
ssh -i phtl.pem ec2-user@ec2-18-191-193-239.us-east-2.compute.amazonaws.com 'sudo service nginx start'