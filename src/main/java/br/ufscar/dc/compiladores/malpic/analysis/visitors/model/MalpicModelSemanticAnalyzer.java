package br.ufscar.dc.compiladores.malpic.analysis.visitors.model;

import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
import br.ufscar.dc.compiladores.malpic.MalpicLexer;
import br.ufscar.dc.compiladores.malpic.MalpicParser;
import jdk.internal.org.jline.terminal.Terminal;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MalpicModelSemanticAnalyzer extends MalpicBaseVisitor<String> {
    @Override
    public String visitModelParameters(MalpicParser.ModelParametersContext ctx) {
        String result = null;
        try{
            List<TerminalNode> integers = ctx.NUM_INT();
            List<TerminalNode> reals = ctx.NUM_INT();
            int childrenCount = ctx.getChildCount();
            for (int i = 0; i < childrenCount / 5; i++) {
                int parameterIndex = 2 + (i * 4);
                int valueIndex = parameterIndex + 2;
                String parameterName = ctx.getChild(parameterIndex).getText();
                TerminalNode value = (TerminalNode) ctx.getChild(valueIndex);
                System.out.println(value.getSymbol().getType());
                if (parameterName.equals("epochs") &&
                        value.getSymbol().getType() != MalpicLexer.NUM_INT)
                    throw new RuntimeException(
                            "SEMANTIC ERROR: " +
                                    "'epochs' should be integer.");
                result = "OK";
            }
        } catch(RuntimeException e) {
            result = e.getMessage();
        }

        return result;
    }
}
