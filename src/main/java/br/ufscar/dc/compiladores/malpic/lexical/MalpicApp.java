package br.ufscar.dc.compiladores.malpic.lexical;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

/**
 * Hello world!
 *
 */
public class MalpicApp
{
    public static void main( String[] args )
    {
        try{
            CharStream cs = CharStreams.fromFileName(args[0], StandardCharsets.UTF_8);
            MalpicLexer lex = new MalpicLexer(cs);
            String filename = args[1];
            FileWriter writer = new FileWriter(filename);

            Token t = null;
            while((t = lex.nextToken()).getType() != Token.EOF) {
                String displayName = MalpicLexer.VOCABULARY.getDisplayName(t.getType());
                if (displayName.equals("PALAVRAS_CHAVES"))
                    writer.write("<'" + t.getText() + "'," + t.getText() + ">\n");
                else
                    writer.write("<'" + t.getText() + "','" + displayName + "'>\n");
            }

            writer.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
