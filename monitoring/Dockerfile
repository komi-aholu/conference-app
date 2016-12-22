FROM java:8

MAINTAINER Komi AHOLU <komi.aholu@yahoo.fr>

VOLUME /tmp

ADD target/monitoring*.war app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
