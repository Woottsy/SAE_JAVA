#!/bin/bash
clear

# Compilation
javac --source-path SAE_JAVA/main/src -d bin \
  --module-path /usr/share/openjfx/lib/ \
  --add-modules javafx.controls \
  SAE_JAVA/main/src/*.java


# Exécution (en supposant que AppLibrairie est dans le package sae)
java -cp bin:/usr/share/java/mariadb-java-client.jar \
  --module-path /usr/share/openjfx/lib/ \
  --add-modules javafx.controls \
  AppLibrairie
