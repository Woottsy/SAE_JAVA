#!/bin/bash
clear
javac --source-path ./src -d ./bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls SAE_JAVA/main/src/*.java
javadoc  --source-path ./src -d ./doc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls SAE_JAVA/main/src/*.java
clear
java -cp ./bin:/usr/share/java/mariadb-java-client.jar --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls AppLibrairie