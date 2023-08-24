package br.ufscar.dc.compiladores.malpic;

import java.io.FileWriter;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import br.ufscar.dc.compiladores.malpic.listeners.MyCustomErrorListener;

/**
 * Hello world!
 *
 */
public class MalpicApp
{
    public static void main( String[] args )
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
}