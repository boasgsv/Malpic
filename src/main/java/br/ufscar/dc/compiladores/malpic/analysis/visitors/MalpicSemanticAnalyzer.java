package br.ufscar.dc.compiladores.malpic.analysis.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.analysis.visitors.dataset.MalpicDatasetSemanticAnalyzer;
import br.ufscar.dc.compiladores.malpic.analysis.visitors.model.MalpicModelSemanticAnalyzer;
import br.ufscar.dc.compiladores.malpic.struct.impl.AbstractMalpicSymbolTable;

import java.util.List;

public class MalpicSemanticAnalyzer extends MalpicBaseVisitor<String> {
    MalpicDatasetSemanticAnalyzer datasetSemanticAnalyzer;
    MalpicModelSemanticAnalyzer modelSemanticAnalyzer;
    public MalpicSemanticAnalyzer() {
        this.datasetSemanticAnalyzer = new MalpicDatasetSemanticAnalyzer();
        this.modelSemanticAnalyzer = new MalpicModelSemanticAnalyzer();
    }
    @Override
    public String visitProgram(MalpicParser.ProgramContext ctx) {
        return this.visitPipeline(ctx.pipeline());
    }

    @Override
    public String visitPipeline(MalpicParser.PipelineContext ctx) {
        String result = null;
        try {
            List<MalpicParser.SplitContext> splits = ctx.dataset().split();
            if (splits.isEmpty())
                return "OK";

            MalpicParser.SplitContext firstSplit = splits.get(0);
            String partialResult = datasetSemanticAnalyzer.visitSplitPartitions(firstSplit.splitPartitions());
            if (!partialResult.equals("OK"))
                throw new RuntimeException(partialResult);
            partialResult = modelSemanticAnalyzer.visitModelParameters(
                    ctx.model().modelParameters()
            );
            if (!partialResult.equals("OK"))
                throw new RuntimeException(partialResult);
            result = partialResult;
        } catch(RuntimeException e) {
            result = e.getMessage();
        }
        return result;
    }


}
