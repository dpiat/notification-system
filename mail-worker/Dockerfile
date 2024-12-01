FROM amazoncorretto:17-alpine-jdk as build

WORKDIR /app

COPY . .

ARG GITHUB_REGISTRY_USERNAME
ARG GITHUB_REGISTRY_PASSWORD
ENV GITHUB_REGISTRY_USERNAME=$GITHUB_REGISTRY_USERNAME
ENV GITHUB_REGISTRY_PASSWORD=$GITHUB_REGISTRY_PASSWORD

RUN ./gradlew application:clean application:build

FROM openjdk:17-jdk-alpine as runtime

WORKDIR /app

COPY --from=build /app/application/build/libs/*.jar /app/application.jar

ENTRYPOINT ["java", "-jar", "/app/application.jar"]