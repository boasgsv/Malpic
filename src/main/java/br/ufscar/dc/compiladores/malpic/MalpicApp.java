package br.ufscar.dc.compiladores.malpic;

import java.io.FileWriter;
import java.io.IOException;

import br.ufscar.dc.compiladores.malpic.analysis.listeners.MyCustomErrorListener;
import br.ufscar.dc.compiladores.malpic.analysis.visitors.MalpicSemanticAnalyzer;
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
    public static void main(String [] args) {
        try {

            String inputPath = args[0];
            String lexicalOutputPath = args[1];
            String syntacticalOutputPath = args[2];
            String semanticOutputPath = args[3];
            String generatorOutputPath = args[4];
            String testResultOutputPath = args[5];

            CharStream cs = CharStreams.fromFileName(inputPath);
            MalpicLexer lexer = new MalpicLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MalpicParser parser = new MalpicParser(tokens);

            FileWriter lexicalOutputWriter = new FileWriter(lexicalOutputPath);
            FileWriter syntacticalOutputWriter = new FileWriter(syntacticalOutputPath);
            FileWriter semanticOutputWriter = new FileWriter(semanticOutputPath);
            FileWriter testResultOutputWriter = new FileWriter(testResultOutputPath);
            FileWriter ipynbCodeGenerationWriter = new FileWriter(generatorOutputPath);


            // Lexico
            StringBuilder lexicalOutput = new StringBuilder();
            tokens.fill();
            for (Token token : tokens.getTokens()) {
                String displayName = MalpicLexer.VOCABULARY.getDisplayName(token.getType());
                String tokenOutputRepresentation = "< " + displayName + ", " + token.toString() + ">\n";
                lexicalOutput.append(tokenOutputRepresentation);
            }
            lexicalOutputWriter.write(lexicalOutput.toString());



            // Sintaxe
            MyCustomErrorListener mcel = new MyCustomErrorListener(syntacticalOutputWriter);
            parser.removeErrorListeners();
            parser.addErrorListener(mcel);
            MalpicParser.ProgramContext arvore = parser.program();

            // Semantico
            MalpicSemanticAnalyzer semanticAnalyzer = new MalpicSemanticAnalyzer();
            String semanticAnalyzerOutput = semanticAnalyzer.visitProgram(arvore);
            semanticOutputWriter.write(semanticAnalyzerOutput);

            // Geracao de Codigo
            MalpicIpynbGenerator generator = new MalpicIpynbGenerator();
            String ipynbOutput = generator.visitProgram(arvore);
            ipynbCodeGenerationWriter.write(ipynbOutput);

            ipynbCodeGenerationWriter.close();
            lexicalOutputWriter.close();
            syntacticalOutputWriter.close();
            semanticOutputWriter.close();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
