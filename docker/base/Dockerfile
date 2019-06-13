FROM ubuntu:16.04

RUN apt-get update
RUN apt-get install -y curl wget

# Java Setup
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:openjdk-r/ppa -y
RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN update-java-alternatives -s java-1.8.0-openjdk-amd64

# Spark setup
RUN curl -O http://mirror.synyx.de/apache/spark/spark-2.3.3/spark-2.3.3-bin-hadoop2.7.tgz
RUN tar -zxvf spark-2.3.3-bin-hadoop2.7.tgz -C /opt
RUN rm spark-2.3.3-bin-hadoop2.7.tgz
ENV SPARK_HOME /opt/spark-2.3.3-bin-hadoop2.7
ENV PATH $SPARK_HOME/bin:$PATH

CMD /bin/bash -i -l