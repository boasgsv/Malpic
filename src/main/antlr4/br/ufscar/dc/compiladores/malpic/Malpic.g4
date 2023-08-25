grammar Malpic;

WS: ( ' ' | '\t' | 'r' | '\n' ) {skip();};
STRING: '"' ~('\n'|'"')* '"';
STRING_NOT_CLOSED: '"' ~('\n'|'"')* '\n';
COMMENTS: '/' ~('\n' | '/')* '/' { skip(); };
COMMENTS_NOT_CLOSED: '/' ~('\n'|'/')* '\n';
NUM_INT: ('0'..'9')+;
NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;
KEY_WORDS:  ':' | 'pipeline' 
    | 'data' | 'preprocess' | 'model' | 'evaluate' | 'deploy' | 'remove' | 'normalize'
    | 'split' | 'rate' | 'epoch' | 'MSE' | 'plot' | 'x' | 'y' | 'LinearRegression';
ERROR: .;

program: EOF;



