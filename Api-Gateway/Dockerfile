FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
RUN ls -alh /app/target/

FROM bitnami/java:17
COPY --from=build /app/target/Api-Gateway-0.0.1-SNAPSHOT.jar /usr/app/Api-Gateway.jar
WORKDIR /usr/app
EXPOSE 8085
ENV SPRING_PROFILES_ACTIVE=docker
CMD ["java", "-jar", "Api-Gateway.jar"]

