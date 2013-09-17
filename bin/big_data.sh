#!/bin/sh
mvn -q exec:java -Dexec.mainClass=org.energyos.espi.datacustodian.console.BigDataMaker -Dexec.args="$1" -e
