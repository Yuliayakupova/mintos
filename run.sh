#!/bin/bash
JAR_FILE="target/mintos-0.0.1-SNAPSHOT.jar"
DOCKER_IMAGE_NAME="mintos"
docker build -t $DOCKER_IMAGE_NAME .
docker run -p 8080:8080 $DOCKER_IMAGE_NAME