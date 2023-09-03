//
//package br.ufscar.dc.compiladores.malpic.visitors.analysis.dataset;
//
//import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
//import br.ufscar.dc.compiladores.malpic.MalpicParser;
//import br.ufscar.dc.compiladores.malpic.core.MalpicSymbolTable;
//import org.antlr.v4.runtime.tree.TerminalNode;
//
//import java.util.List;
//
//public class MalpicDatasetSemanticAnalyzer extends MalpicBaseVisitor<String> {
//    MalpicSymbolTable symbolTable;
//    public MalpicDatasetSemanticAnalyzer(MalpicSymbolTable symbolTable) {
//        this.symbolTable = symbolTable;
//    }
//
//    @Override
//    public String visitDataset(MalpicParser.DatasetContext ctx) {
//        this.visitSource(ctx.source());
//        List<MalpicParser.PreprocessContext> preprocesses = ctx.preprocess();
//        List<MalpicParser.VisualizeContext> visualizes = ctx.visualize();
//        List<MalpicParser.SplitContext> splits = ctx.split();
//
//        for (MalpicParser.PreprocessContext preprocess: preprocesses) {
//            this.visitPreprocess(preprocess);
//        }
//
//        for (MalpicParser.VisualizeContext visualize: visualizes) {
//            this.visitVisualize(visualize);
//        }
//
//        for (MalpicParser.SplitContext split: splits) {
//            this.visitSplit(split);
//        }
//
//        return "in dataset2!\n";
//    }
//
//    @Override
//    public String visitSource(MalpicParser.SourceContext ctx) {
//        this.visitSourceTarget(ctx.sourceTarget());
//        this.visitSourceFeatures(ctx.sourceFeatures());
//        return super.visitSource(ctx);
//    }
//
//    @Override
//    public String visitSourceTarget(MalpicParser.SourceTargetContext ctx) {
//        MalpicParser.VariableContext var = ctx.variable();
//        String id = var != null ? var.getText() : "target";
//        String val = ctx.STRING().getText();
//        symbolTable.insert(id, val);
//
//        return super.visitSourceTarget(ctx);
//    }
//
//
//
//
//    @Override
//    public String visitSourceFeatures(MalpicParser.SourceFeaturesContext ctx) {
//        MalpicParser.VariableContext var = ctx.variable();
//        String id = var != null ? var.getText() : "features";
//        TerminalNode remaining = ctx.REMAINING();
//        if (remaining != null)
//
//        symbolTable.insert(id, val);
//
//        return super.visitSourceFeatures(ctx);
//    }
//
//
//
//    @Override
//    public String visitPreprocess(MalpicParser.PreprocessContext ctx) {
//        MalpicParser.VariableContext target = ctx.variable();
//        System.out.println(target.children);
//
//        return super.visitPreprocess(ctx);
//    }
//
//    @Override
//    public String visitVisualize(MalpicParser.VisualizeContext ctx) {
//        return super.visitVisualize(ctx);
//    }
//
//    @Override
//    public String visitSplit(MalpicParser.SplitContext ctx) {
//
////        this.visitSplitStrategy(ctx.splitStrategy());
//        return "in split!";
//    }
////
////    @Override
////    public String visitSplitStrategy(MalpicParser.SplitStrategyContext ctx) {
////        this.visitHoldout(ctx.holdout());
////        return "in splitStrategy!";
////    }
////
////    @Override
////    public String visitHoldout(MalpicParser.HoldoutContext ctx) {
////
////        MalpicParser.HoldoutTrainContext train = ctx.holdoutTrain();
////        MalpicParser.HoldoutTestContext test = ctx.holdoutTest();
////
////        Double trainRate = Double.parseDouble(train.NUM_REAL().getText());
////        Double testRate = Double.parseDouble(test.NUM_REAL().getText());
////
////        if (Double.compare(trainRate + testRate, 1.0) != 0)
////            System.out.println("Invalid holdout split proportions");
////        else
////            System.out.println("OK!");
////        return "in holdout!";
////    }
//}
