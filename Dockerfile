FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY . /build/
WORKDIR /build/
RUN mvn package -DskipTests
RUN ls /build/target

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/universidad-ms-1.0.0.jar /app/
ENTRYPOINT ["java", "-jar", "universidad-ms-1.0.0.jar"]