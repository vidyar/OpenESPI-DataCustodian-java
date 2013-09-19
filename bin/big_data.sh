#!/bin/sh
export MAVEN_OPTS=-Xmx5096m
mvn -q exec:java -Dexec.mainClass=org.energyos.espi.datacustodian.console.BigDataMaker -Dexec.args="$1" -e
