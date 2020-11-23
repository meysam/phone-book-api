# springboot-phonebook-api

## Requirements

For building and running the application you need:

- [JDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `digital.paisley.phonebook.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Running the application in a docker container

The easiest way to run the application in a docker container is to use the [Docker CLI](https://docs.docker.com/engine/reference/commandline/cli/):

1. Go to the folder containing Dockerfile
```shell
cd project
```

2. sudo docker build -t name:tag . 

```shell
sudo docker build -t phonebook:1.0 .
```

3. Start the docker container phonebook:1.0, run the /opt/app/app.jar file during startup.
```shell
sudo docker run -d -p 8080:8080 -t spring-boot:1.0
```
4. use this URL http://localhost:8080/

## OpenAPI
After running thr App you can use this [URL](http://localhost:8080/swagger-ui) to find OpenAPI specification for the REST APIs

## Document
You can find here all documents related to this project:
[Notion Documents](https://www.notion.so/Phone-Book-APIs-77b1b85c5b32424d9ccc997d95e6cd65)

