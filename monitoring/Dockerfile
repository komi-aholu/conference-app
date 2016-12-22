FROM java:8

MAINTAINER Komi AHOLU <komi.aholu@yahoo.fr>

VOLUME /tmp

ADD target/monitoring*.war app.war

RUN bash -c 'touch /app.war'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
