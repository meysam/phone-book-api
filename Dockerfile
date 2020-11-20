FROM openjdk:11-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} phonebook.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/phonebook.jar"]