#!/bin/bash
javac *.java
jar cmf META-INF/MANIFEST.MF AI.jar *.class
java -jar AI.jar 
