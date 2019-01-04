FROM openjdk:8-jdk-alpine AS builder
COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN apk --no-cache add maven
RUN mvn package -DskipTests

FROM gcr.io/distroless/java
COPY --from=builder /usr/src/app/target/voting-service-0.0.1-SNAPSHOT.jar /usr/app/app.jar
WORKDIR /root/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/app/app.jar"]

EXPOSE 8080
