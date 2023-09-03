//package br.ufscar.dc.compiladores.malpic.visitors.analysis;
//
//import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
//import br.ufscar.dc.compiladores.malpic.MalpicParser;
//import br.ufscar.dc.compiladores.malpic.core.MalpicSymbolTable;
//import br.ufscar.dc.compiladores.malpic.visitors.analysis.dataset.MalpicDatasetSemanticAnalyzer;
//
//public class MalpicSemanticAnalyzer extends MalpicBaseVisitor<String> {
//    MalpicDatasetSemanticAnalyzer datasetSemanticAnalyzer;
//    MalpicSymbolTable symbolTable;
//
//    public MalpicSemanticAnalyzer() {
//        datasetSemanticAnalyzer = new MalpicDatasetSemanticAnalyzer(symbolTable);
//    }
//    @Override
//    public String visitProgram(MalpicParser.ProgramContext ctx) {
//        this.visitPipeline(ctx.pipeline());
//        return "in program!\n";
//    }
//
//    @Override
//    public String visitPipeline(MalpicParser.PipelineContext ctx) {
//        datasetSemanticAnalyzer.visitDataset(ctx.dataset());
//        return "in pipeline!\n";
//    }
//
//
//}
