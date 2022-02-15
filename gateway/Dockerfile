FROM openjdk:17
ARG JAR_FILE=target/gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
WORKDIR /
ENTRYPOINT ["java","-jar","app.jar"]