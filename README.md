# Ticket Manager

A simple ticket management system built with Java, Tomcat, and MongoDB.

## Prerequisites

- Java 11 or higher
- Apache Tomcat 9 or higher
- MongoDB running on localhost:27017
- Maven

## Setup

1. Clone or download the project.
2. Run MongoDB: `docker run -d --platform linux/amd64 -p 27017:27017 -v /Volumes/DATA/MongoDB:/bitnami/mongodb -e MONGODB_ROOT_PASSWORD=1070.. bitnami/mongodb:latest`
3. Build the project: `mvn clean package`
4. Deploy the WAR file to Tomcat.
5. Access the application at `http://localhost:8080/tickets-tomcat/`

## Features

- View all tickets
- Add new tickets