lexer grammar MalpicLexer;

PIPELINE_STEP:
    'data' | 'preprocessing' |
    'model' | 'training' | 'evaluation' |
    'deployment';

DATA_CHILD_TAGS:
    'source' | 'split'
    ;

MODEL_CHILD_TAGS: 'algorithm';