package br.ufscar.dc.compiladores.malpic;

import java.io.FileWriter;
import java.io.IOException;

import br.ufscar.dc.compiladores.malpic.analysis.listeners.MyCustomErrorListener;
//import br.ufscar.dc.compiladores.malpic.visitors.analysis.MalpicSemanticAnalyzer;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.MalpicIpynbGenerator;
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
            tokens.fill();
            for (Token token : tokens.getTokens()) {
                String displayName = MalpicLexer.VOCABULARY.getDisplayName(token.getType());
                System.out.println("< " +  displayName + ", " + token.toString() + ">");
            }

            //Configuração do erro customizado
            MyCustomErrorListener mcel = new MyCustomErrorListener(writer);
            parser.removeErrorListeners();
            parser.addErrorListener(mcel);

            MalpicParser.ProgramContext arvore = parser.program();
//            MalpicSemanticAnalyzer visitor = new MalpicSemanticAnalyzer();
//            String out = visitor.visitProgram(arvore);

            MalpicIpynbGenerator generator = new MalpicIpynbGenerator();
            String ipynb = generator.visitProgram(arvore);
            FileWriter ipynbWriter = new FileWriter("output.ipynb");
            ipynbWriter.write(ipynb);
            ipynbWriter.close();
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
