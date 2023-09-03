grammar Malpic;

WS: ( ' ' | '\t' | '\r' | '\n' ) -> skip;
REMAINING: 'remaining';
UPPERCASE_IDENTIFIER: [A-Z] [a-zA-Z0-9]*;
LOWERCASE_IDENTIFIER: [a-z] [a-zA-Z0-9]*;
STRING: '"' ~('\n'|'"')* '"';
STRING_NOT_CLOSED: '"' ~('\n'|'"')* '\n';
COLON: ':';
SEMICOLON: ';';
COMMENTS: '//' ~('\n' | '/')* { skip(); };
NUM_INT: ('0'..'9')+;
NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;

program: pipeline EOF;
pipeline: 'pipeline' UPPERCASE_IDENTIFIER SEMICOLON dataset;
dataset:
    'dataset' UPPERCASE_IDENTIFIER STRING COLON
        source
        (preprocess |
            visualize |
                split)*;

source:
    'from' STRING 'with'
        sourceTarget
        sourceFeatures;
sourceTarget: 'target' ('as' variable)? '=' STRING;
sourceFeatures: 'features' ('as' variable)? '=' REMAINING;

preprocess: 'preprocess' variable
                preprocessCommand ('->'
                    preprocessCommand)*;
preprocessCommand:
    ('remove' |
    'normalize' |
    'one_hot_encode')
    '(' STRING (', ' STRING)* ')';


visualize: 'visualize' variable plotset | plot;
plotset: '{' plot (',' plot)* '}';
plot: 'histogram' | 'correlation';

split: 'split' variable 'as' variable (splitPartitions | 'using' splitStrategy);
splitPartitions:
    variable '=' NUM_REAL (',' variable '=' NUM_REAL)+;
splitStrategy:
    'holdout' '(' NUM_REAL ')';

variable: (LOWERCASE_IDENTIFIER | UPPERCASE_IDENTIFIER)
            ('.' (LOWERCASE_IDENTIFIER | UPPERCASE_IDENTIFIER))*;



/*

holdoutTest: NUM_REAL 'as test';
holdoutTrain: NUM_REAL 'as train';
holdout: 'holdout' holdoutTrain ', ' holdoutTest;
splitTarget: IDENTIFIER;
remove_cmd: 'remove' '(' STRING (', ' STRING)* ')';
normalize_cmd: 'normalize' '(' STRING (', ' STRING)* ')';



model: 'model' STEP_ID STRING;
*/

