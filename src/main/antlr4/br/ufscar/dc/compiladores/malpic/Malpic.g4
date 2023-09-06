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
pipeline: 'pipeline' UPPERCASE_IDENTIFIER SEMICOLON dataset model deployment;
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

preprocess: 'preprocess' variable?
                preprocessCommand ('->'
                    preprocessCommand)*;
preprocessCommand:
    ('remove_column' |
    'normalize' |
    'one_hot_encode')
     ('()' |
    '(' STRING (', ' STRING)* ')');


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


model:
    'model' UPPERCASE_IDENTIFIER STRING COLON
            modelAlgorithm
            modelParameters
            modelTrain
            modelEvaluate;
modelAlgorithm: 'algorithm' '=' '"linear_regression"';
modelParameters:
    'parameters' '{'
        ('learning_rate' | 'epoch') '=' (NUM_INT | NUM_REAL)
        (',' ('learning_rate' | 'epochs') '=' (NUM_INT | NUM_REAL))*
    '}';

modelTrain:
    'train on' obj;

modelEvaluate: 'evaluate on' obj 'using' '{'
                    modelEvaluationMetrics
                    modelEvaluationVisualization
                '}';

modelEvaluationVisualization:
    'visualize' '{'
        modelEvaluationVisualizationOptions
        (',' modelEvaluationVisualizationOptions)*
    '}';

modelEvaluationVisualizationOptions:
'residuals_plot' | 'prediction_vs_actual' | 'feature_importance';

modelEvaluationMetrics: 'metrics' '=' STRING (', ' STRING)*;
deployment:
    'deployment' UPPERCASE_IDENTIFIER STRING COLON
            'type' '=' ('"local"' | '"remote"');

obj: LOWERCASE_IDENTIFIER ('.' LOWERCASE_IDENTIFIER)*;
