package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.dataset;

import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonAssignmentStatement;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonStatement;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.AbstractMalpicIpynbGenerator;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MalpicDatasetIpynbGenerator extends AbstractMalpicIpynbGenerator {

    public MalpicDatasetIpynbGenerator() {
        super();
    }

    @Override
    public String visitDataset(MalpicParser.DatasetContext ctx) {
        this.visitSource(ctx.source());
        List<MalpicParser.PreprocessContext> preprocesses = ctx.preprocess();
        for (MalpicParser.PreprocessContext preprocess: preprocesses) {
            this.visitPreprocess(preprocess);
        }
        return "";
    }

    @Override
    public String visitPreprocess(MalpicParser.PreprocessContext ctx) {
        MalpicParser.VariableContext preprocessTarget = ctx.variable();
        String preprocessTargetString = preprocessTarget == null ?
                "all" :
                preprocessTarget.getText();

        System.out.println(preprocessTargetString);
        List<MalpicParser.PreprocessCommandContext> preprocessCommandChain = ctx.preprocessCommand();
        for (MalpicParser.PreprocessCommandContext preprocessCommand: preprocessCommandChain) {
            this.visitPreprocessCommand(preprocessCommand);
        }

        return "";
    }

    @Override
    public String visitPreprocessCommand(MalpicParser.PreprocessCommandContext ctx) {
        String commandName = ctx.getChild(0).getText();
        return "";
    }

    @Override
    public String visitSource(MalpicParser.SourceContext ctx) {
        // Dataframe Definition Statement
        String sourceString = ctx.STRING().getText();
        int cellIndex = ipynbBuilder.nextCodeCell();
        String pdReadCsv = "pd.read_csv(" + sourceString + ")";
        MalpicPythonAssignmentStatement stmt1 =
                new MalpicPythonAssignmentStatement("df", pdReadCsv);

        // Target Definition Statement
        MalpicParser.SourceTargetContext sourceTarget = ctx.sourceTarget();
        String targetAlias = sourceTarget.variable().getText();
        String label = sourceTarget.STRING().getText();
        MalpicPythonStatement stmt2 = new MalpicPythonAssignmentStatement(
                targetAlias,
                "df[" + label + "]");

        // Feature Definition Statement
        MalpicParser.SourceFeaturesContext sourceFeatures = ctx.sourceFeatures();
        String featuresAlias = sourceFeatures.variable().getText();
        TerminalNode featuresRemaining = sourceFeatures.REMAINING();
        if (featuresRemaining == null) {
            throw new RuntimeException("Generator Error: Unimplemented\n");
        }


        MalpicPythonStatement stmt3 = new MalpicPythonAssignmentStatement(
                featuresAlias,
                "df.drop(" + label + ", axis=1)");

        ipynbBuilder.appendPythonStatementToCell(stmt1, cellIndex);
        ipynbBuilder.appendPythonStatementToCell(stmt2, cellIndex);
        ipynbBuilder.appendPythonStatementToCell(stmt3, cellIndex);
        return "";
    }
}
