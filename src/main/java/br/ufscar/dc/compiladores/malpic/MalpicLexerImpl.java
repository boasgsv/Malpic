package br.ufscar.dc.compiladores.malpic;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.RecognitionException;

public class MalpicLexerImpl extends MalpicLexer {
    public MalpicLexerImpl(CharStream input) {
        super(input);
    }






    static class MalpicLexerException extends Exception {

        public MalpicLexerException(String s) {
            super(s);
        }
    }
}
