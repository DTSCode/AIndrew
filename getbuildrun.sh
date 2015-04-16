#!/bin/bash
#for use on ubuntu, mint, debian
#anything besides an apt-get system will require a manual python install
if [$(python -V | grep "Python 2.7") -ne "-1"];
then
	echo "Installing Python 2.7"
	sudo apt-get install python2.7
fi
#commands for other distros coming soon!
if [$(python -mplatform | grep Ubuntu) -o $(python -mplatform | grep Debian) -o $(python -mplatform | grep Mint)];
then
	if [$(dpkg-query -W -f='${Status}' git 2>/dev/null) -eq 0];
	then
		echo "Installing git"
		sudo apt-get install git
	fi
	if [$(dpkg-query -W -f='${Status}' *java* 2>/dev/null) -eq 0 -o $(javac -version 2>&1 | sed 's/java version "\(.*\)\.\(.*\)\..*"/\1\2/; 1q') -lt 18];
	then 
		echo "Java"
		sudo add-apt-repository ppa:webupd8-team/java
		sudo apt-get update
		sudo apt-get install oracle-java8-installer oracle-java8-set-default
	fi
fi
if [$(basename "$PWD") -ne AIndrew];
then
	echo "Making build directory"
	mkdir AIndrew
	cd AIndrew
	git clone https://github.com/toad1359/AIndrew.git
fi
echo "Pulling latest"
git pull
if ["$?" -eq "-1"];
then 
	echo Error, No git repo in this directory... setting up
	git clone https://github.com/toad1359/AIndrew.git
	git pull
fi
echo "Compiling"
javac *.java
jar cmf META-INF/MANIFEST.MF AI.jar *.class
echo "Jar created"
echo "Running"
java -jar AI.jar 
