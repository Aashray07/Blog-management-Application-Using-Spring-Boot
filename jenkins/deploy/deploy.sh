#!/bin/bash

#we create a file named .auth to store all the variables which will be needed for deployment
echo maven-project > /tmp/.auth
echo $BUILD_TAG >> /tmp/.auth
echo $PASS >> /tmp/.auth

#as we will be deploying from the linux enviorment i.e. the virtual machine, we need to transfer our variables(.auth file) to the remote machine
#transfer from one machine to another is done using scp command
scp -i /opt/prod /tmp/.auth prod-user@linuxfacilito.online:/tmp/.auth
#wa also need to transfer the publish file in which we have mentioned how to run the app container inside VM.
scp -i /opt/prod ./jenkins/deploy/publish prod-user@linuxfacilito.online:/tmp/publish
#run the publish file inside the remote machine using ssh
ssh -i /opt/prod prod-user@linuxfacilito.online "/tmp/publish"
