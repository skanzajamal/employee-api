# employee-api

Java application which performs all the CRUD operations and pushes related event in Kafka.

# Prerequisites

- JDK 11
-IntelliJ IDEA or any IDE
- Apache Kafka

# Technologies:

- Back-end: SpringBoot (JAVA)
- Database: PostgreSQL

# Build Instructions

- Install IntelliJ IDEA
- Install JDK 11

to install kafka visit https://kafka.apache.org/downloads
and download kafka from binary downloads, verion 3.5.1

open terminal in kafka folder and run the following command:

$ .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

open a new terminal in the same kafka folder and run the following command:

$ .\bin\windows\kafka-server-start.bat .\config\server.properties

in your IDE press debug or run button, then the application will start on port 8081

for api docs: open in browser http://localhost:8081/swagger-ui/index.html#

provide username = "admin" and password = "password", to interact with the endpoints.

# Viewing messages in Kafka

use the Kafka-console-consumer to view your messages.

open a new terminal in the same kafka folder and run the following command to get all messages in a topic from the beginning.

$ .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic employee --from-beginning



