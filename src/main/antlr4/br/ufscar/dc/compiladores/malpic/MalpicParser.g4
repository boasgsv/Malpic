parser grammar MalpicParser;

options {
    tokenVocab=MalpicLexer;
}

header: PIPELINE SWS VALUE SEMI_COLON;
entry: IDENTIFIER COLON value;
value:  scalar | vector | children;
scalar: (SWS+ VALUE | IDENTIFIER);
vector: (SWS* NEWLINE DWS+ DASH scalar)+;
children: (SWS* NEWLINE DWS+ entry)+;
dictionary: entry;
program: header (SWS* NEWLINE (SWS* NEWLINE)+ dictionary)+ (SWS* NEWLINE)* EOF;

