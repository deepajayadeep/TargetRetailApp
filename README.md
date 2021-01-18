# myRetail-Restful-Service
This is a Restful Service, POC, developed in SpringBoot.
# Technology Stack used
SpringBoot 2.3.0
Java 1.8
MongoDB
Maven 4.0.0
Swagger UI
# Database used
USed MongoDB Atlas, which is full managed cloud database.
# Build, Test and Run application
cd TargetRetailApp

Then run

mvn clean package

Then run the jar

java -jar target/retail-0.0.1-SNAPSHOT.jar

Application will start running on port 8080


# Use Swagger UI to see all services
http://localhost:8080/swagger-ui.html

# Calling restful api services

Call each services over there , no need to pass the id as id is auto generated