@echo off

setlocal enabledelayedexpansion

echo Building the project with Maven...
mvn clean install

if not exist core\target\core-0.0.1-SNAPSHOT.jar (
    echo JAR file not found. Build failed.
    pause
    exit /b 1
)

echo Starting the Spring Boot application...
start java -jar core\target\core-0.0.1-SNAPSHOT.jar > app.log 2>&1

echo Application started successfully. Running processes:
tasklist /FI "IMAGENAME eq java.exe"
