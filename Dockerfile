FROM openjdk:17-oracle
EXPOSE 5500
COPY target/moneyTransferSystemBackend-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]