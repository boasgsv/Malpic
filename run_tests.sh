#!/bin/bash

if [ "$#" -lt 1 ]; then
    echo "Usage: $0 <nome_do_teste>"
    exit 1
fi

test=$1

INPUT=tests/entrada/$test.txt;
TESTRESULT_OUTPUT=output/testresult.out;
LEXICAL_OUTPUT=output/lexical.out;
SYNTAX_OUTPUT=output/syntactical.out;
SEMANTICAL_OUTPUT=output/semantical.out;
GENERATOR_OUTPUT=output/outpt.ipynb;

mvn clean package;
java -jar target/malpic-1.0-SNAPSHOT-jar-with-dependencies.jar \
  ${INPUT} ${LEXICAL_OUTPUT} ${SYNTAX_OUTPUT}\
   ${SEMANTICAL_OUTPUT} ${GENERATOR_OUTPUT}\
   ${TESTRESULT_OUTPUT};
