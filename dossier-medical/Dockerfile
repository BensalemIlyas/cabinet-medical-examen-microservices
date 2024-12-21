FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
RUN ls -alh /app/target/

FROM bitnami/java:17
COPY --from=build /app/target/dossier-medical-0.0.1-SNAPSHOT.jar /usr/app/dossier-medical.jar
WORKDIR /usr/app
EXPOSE 8084
CMD ["java", "-jar", "dossier-medical.jar"]

