FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
RUN ls -alh /app/target/

FROM bitnami/java:17
COPY --from=build /app/target/praticien-service-0.0.1-SNAPSHOT.jar /usr/app/praticien-service.jar
WORKDIR /usr/app
EXPOSE 8081
CMD ["java", "-jar", "praticien-service.jar"]

