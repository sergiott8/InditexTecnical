FROM adoptopenjdk/openjdk11
ENV JAVA_VERSION=1.11

VOLUME /tmp

COPY target/SGA-Inditex-1.0-SNAPSHOT.jar app.jar


RUN sh -c 'touch /app.jar'
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar"]