#!/bin/bash
clear

# Compilation
javac --source-path app/main/src -d bin \
  --module-path /usr/share/openjfx/lib/ \
  --add-modules javafx.controls \
  app/main/src/*.java app/main/src/models/*.java

# Génération de la javadoc
javadoc --source-path app/main/src -d doc \
  --module-path /usr/share/openjfx/lib/ \
  --add-modules javafx.controls \
  app/main/src/*.java app/main/src/models/*.java

clear

# Exécution (en supposant que AppLibrairie est dans le package sae)
java -cp bin:/usr/share/java/mariadb-java-client.jar \
  --module-path /usr/share/openjfx/lib/ \
  --add-modules javafx.controls \
  AppLibrairie
