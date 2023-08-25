package br.ufscar.dc.compiladores.malpic;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import br.ufscar.dc.compiladores.malpic.listeners.MyCustomErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;


/**
 * Hello world!
 *
 */
public class MalpicApp
{
    public static void main(String [] args)
    {
        try {
            CharStream cs = CharStreams.fromFileName(args[0]);
            MalpicLexer lexer = new MalpicLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MalpicParser parser = new MalpicParser(tokens);

            String filename = args[1];
            FileWriter writer = new FileWriter(filename);

            //Configuração do erro customizado
            MyCustomErrorListener mcel = new MyCustomErrorListener(writer);
            parser.removeErrorListeners();
            parser.addErrorListener(mcel);

            parser.program();

            writer.close();
        } catch(IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }

    /*
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
                if (displayName.equals("COMMENTS_NOT_CLOSED")){
                    writer.write("Linha " + t.getLine() + ": " + t.getText() + " - comentario nao fechado\n");
                    break;
                }
                else if (displayName.equals("STRING_NOT_CLOSED")){
                    writer.write("Linha " + t.getLine() + ": " + t.getText() + " - cadeia nao fechada\n");
                    break;
                }
                else if (displayName.equals("ERROR")){
                    writer.write("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado\n");
                    break;
                }
                else { // no error
                    writer.write("<'" + t.getText() + "','" + displayName + "'>\n");
                }
            }

            writer.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */


}
