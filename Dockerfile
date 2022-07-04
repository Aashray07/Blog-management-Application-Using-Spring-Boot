#we want to create a jenkins container with docker and ansible installed in that container, thats why we create a dockerfile for jenkins container too.
#in this docker file we mention of using jenkins as the baseimage(we will get jenkins container) and mention installation of docker and ansible 
FROM jenkins/jenkins

USER root

# Install ansible
RUN apt-get update && apt-get install python3-pip -y && \
    pip3 install ansible --upgrade

#as jenkins is a debian based linux, we use the debian command to install docker in jenkins
# Install Docker on jenkins base image
RUN apt-get update && \
apt-get -y install apt-transport-https \
     ca-certificates \
     curl \
     gnupg2 \
     software-properties-common && \
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
   $(lsb_release -cs) \
   stable" && \
apt-get update && \
apt-get -y install docker-ce

#install docker COmpose
RUN curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && chmod +x /usr/local/bin/docker-compose

RUN usermod -aG docker jenkins

USER jenkins