FROM ubuntu:18.04


ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update -y
RUN apt-get install -y console-setup sudo wget curl unzip openjdk-17-jre \
	&& apt-get install -y --no-install-recommends software-properties-common

RUN useradd --create-home -s /bin/bash user && \
    adduser user sudo && \
    echo '%sudo ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers 


COPY --chown=user:user target/flight-status-backend*.jar /flight-status-backend.jar
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENTRYPOINT ["java","-jar","flight-status-backend.jar"]
