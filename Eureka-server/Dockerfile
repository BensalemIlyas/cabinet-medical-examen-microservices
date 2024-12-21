FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
RUN ls -alh /app/target/

FROM bitnami/java:17
COPY --from=build /app/target/Eureka-server-0.0.1-SNAPSHOT.jar /usr/app/Eureka-server.jar
WORKDIR /usr/app
EXPOSE 8761
CMD ["java", "-jar", "Eureka-server.jar"]

