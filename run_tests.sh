#!/bin/bash

INPUT=tests/entrada/2-algorithm-with-linear-regression-correct.txt;
OUTPUT=output/output.txt;

mvn clean package;
java -jar target/malpic-1.0-SNAPSHOT-jar-with-dependencies.jar ${INPUT} ${OUTPUT};