lexer grammar MalpicLexer;

@members {
    private java.util.Stack<Integer> indentStack = new java.util.Stack<>();
    { indentStack.push(0); }  // Initialize with 0 indentation.
}

DWS: SWS SWS;
SWS: ' ';
NEWLINE: '\r'? '\n';

INDENT: ('  ' {indentStack.push(2);}) ;
DEDENT: {indentStack.pop();} -> skip;

SEMI_COLON: ';';
COLON: ':';
DASH: '-';

STRING_NOT_CLOSED: '"' ~('\n'|'"')* '\n';

COMMENT: '//' ~('\n' | '/')* { skip(); };
COMMENT_NOT_CLOSED: '/' ~('\n'|'/')* '\n';



// KEYWORD:  PIPELINE;
PIPELINE: 'Pipeline';

VALUE: BOOLEAN | STRING | NUM_INT | NUM_REAL |
       ALGORITHM | VISUALIZATION | METRIC | DEPLOYMENT_TYPE;
STRING: '"' ~('\n'|'"')* '"';
BOOLEAN: 'True' | 'False';
NUM_INT: ('0'..'9')+;
NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;
ALGORITHM: 'Linear Regression';
VISUALIZATION: 'Histogram';
METRIC: 'MSE';
DEPLOYMENT_TYPE: 'Local';

IDENTIFIER: [a-z] [a-zA-Z0-9_]*;
KEY: ': ' NEWLINE;







