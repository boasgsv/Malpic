lexer grammar MalpicLexer;

WS: ( ' ' | '\t' | 'r' | '\n' ) {skip();};

CADEIA: '"' ~('\n'|'"')* '"';

NUM_INT: ('0'..'9')+;

NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;

PALAVRAS_CHAVES:  ':' | '(' | ')' | '.' | 'pipeline' | 'generate' | 'Jupyter' | '-';

PIPELINE_STEP:
    'data' | 'preprocessing' |
    'model' | 'evaluation' |
    'deployment';

DATA_CHILD_TAGS:
    'source';

MODEL_CHILD_TAGS: 'algorithm' | 'parameters';

ALGORITHM_CHILD_TAGS: 'LinearRegression';

PREPROCESSING_CHILD_TAGS: 'removeColumn' | 'normalize' | 'split';

EVALUATION_CHILD_TAGS: 'metric';

METRIC_CHILD_TAGS: 'MSE';

DEPLOYMENT_CHILD_TAGS: 'type' | 'fields'



