#!/bin/bash
if [($dpkg-query -W -f='${Status}' git 2>/dev/null) -eq 0];
then
sudo apt-get install git
fi
if [($dpkg-query -W -f='${Status}' *java* 2>/dev/null) -eq 0];
then 
sudo add-apt-repository ppa:webupd8-team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer oracle-java8-set-default
fi
git clone https://github.com/toad1359/AIndrew.git
cd AINDREW
javac *.java
jar cmf META-INF/MANIFEST.MF AI.jar *.class
java -jar AI.jar 
