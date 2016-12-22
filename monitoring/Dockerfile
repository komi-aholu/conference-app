FROM java:8

MAINTAINER Komi AHOLU <komi.aholu@yahoo.fr>

VOLUME /tmp

EXPOSE 8888

ADD target/monitoring*.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
