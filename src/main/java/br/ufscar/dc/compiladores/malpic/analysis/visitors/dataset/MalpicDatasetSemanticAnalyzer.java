
package br.ufscar.dc.compiladores.malpic.analysis.visitors.dataset;

import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
import br.ufscar.dc.compiladores.malpic.MalpicParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;


public class MalpicDatasetSemanticAnalyzer extends MalpicBaseVisitor<String> {

    public MalpicDatasetSemanticAnalyzer() {}

    @Override
    public String visitDataset(MalpicParser.DatasetContext ctx) {
        return "";
    }

    @Override
    public String visitSource(MalpicParser.SourceContext ctx) {
        return super.visitSource(ctx);
    }

    @Override
    public String visitSourceTarget(MalpicParser.SourceTargetContext ctx) {
        return super.visitSourceTarget(ctx);
    }


    @Override
    public String visitSplitPartitions(MalpicParser.SplitPartitionsContext ctx) {
        String result;
        try {

            List<TerminalNode> rates = ctx.NUM_REAL();
            Double totalRate = 0.0;
            for (TerminalNode rate: rates)
            {
                totalRate += Double.parseDouble(rate.getText());
            }

            Double epsilon = 1E-10;
            if (Math.abs(totalRate - 1.0) > epsilon)
                throw new RuntimeException("SEMANTIC ERROR: " +
                                            "Total split rates " +
                                            "must amount to 1.");

            result = "OK";
        } catch(RuntimeException e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String visitSourceFeatures(MalpicParser.SourceFeaturesContext ctx) {
        return super.visitSourceFeatures(ctx);
    }



    @Override
    public String visitPreprocess(MalpicParser.PreprocessContext ctx) {
        return super.visitPreprocess(ctx);
    }

    @Override
    public String visitVisualize(MalpicParser.VisualizeContext ctx) {
        return super.visitVisualize(ctx);
    }


}
