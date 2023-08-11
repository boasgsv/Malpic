lexer grammar MalpicLexer;

ARGUMENT_FUNCTION: '(' '"' ~('\n'|'"')* '"' ')';
CADEIA: '"' ~('\n'|'"')* '"';

PIPELINE_STEP:
    'data' | 'preprocessing' |
    'model' | 'evaluation' |
    'deployment';

DATA_CHILD_TAGS:
    'source' | 'split'
    ;

MODEL_CHILD_TAGS: 'algorithm' | 'parameters';

