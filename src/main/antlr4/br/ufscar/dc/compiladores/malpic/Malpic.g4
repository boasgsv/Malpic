grammar Malpic;

WS: ( ' ' | '\t' | 'r' | '\n' ) {skip();};
STEP_ID: [A-Z] [a-zA-Z0-9]*;
IDENTIFIER: [a-z] [a-zA-Z0-9]*;
STRING: '"' ~('\n'|'"')* '"';
STRING_NOT_CLOSED: '"' ~('\n'|'"')* '\n';
COLON: ':';
SEMICOLON: ';';
COMMENTS: '//' ~('\n' | '/')* { skip(); };
COMMENTS_NOT_CLOSED: '/' ~('\n'|'/')* '\n';
NUM_INT: ('0'..'9')+;
NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;
KEYWORD:
    'pipeline' |
    'data';
ERROR: .;

train: 'train';
test: 'test';
split: 'split' COLON train COLON NUM_REAL test COLON NUM_REAL;
histogram: 'histogram';
correlation_matrix: 'correlation';
plot: histogram | correlation_matrix;
plotset: '{' plot (',' plot)* '}';
visualize: 'visualize' plotset | plot;
remove_cmd: 'remove' '(' STRING (', ' STRING)* ')';
normalize_cmd: 'normalize' '(' STRING (', ' STRING)* ')';
preprocess_cmds: remove_cmd | normalize_cmd;
preprocess: 'preprocess' preprocess_cmds ('->' preprocess_cmds)*;
source: 'source' '=' STRING;
dataset:
    'dataset' STEP_ID STRING COLON
        source
        preprocess
        visualize
        split;
pipeline: 'pipeline' STRING SEMICOLON;
program: pipeline dataset;


