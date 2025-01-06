#!/bin/bash

set -e

echo "Building the project with Maven..."
mvn clean install

if [ ! -f target/todo-0.0.1-SNAPSHOT.jar ]; then
    echo "JAR file not found. Build failed."
    exit 1
fi

echo "Starting the Spring Boot application..."
java -jar target/todo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &

echo "Application started successfully. Running processes:"
ps aux | grep java
