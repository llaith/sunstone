#!/bin/bash
mvn -s .mvn/settings.xml clean verify sonar:sonar \
  -Dsonar.organization=llaith-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=2cc60ca5b2a1acaec5960df59af4f9ad35878fc7 \
  -DskipTests=true \
  -DskipIT=true
