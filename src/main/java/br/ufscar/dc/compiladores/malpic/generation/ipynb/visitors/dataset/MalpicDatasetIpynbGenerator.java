package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.dataset;

import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands.AbstractMalpicIpynbCommandPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.MalpicIpynbStatementPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.assignment.MalpicIpynbAssignmentStatementPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.atomic.MalpicIpynbAtomicStatementPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.AbstractMalpicIpynbGenerator;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class MalpicDatasetIpynbGenerator extends AbstractMalpicIpynbGenerator {

    public MalpicDatasetIpynbGenerator() {
        super();
    }

    @Override
    public String visitDataset(MalpicParser.DatasetContext ctx) {
        this.visitSource(ctx.source());
        List<MalpicParser.PreprocessContext> preprocesses = ctx.preprocess();
        for (MalpicParser.PreprocessContext preprocess: preprocesses) {
//             this.visitPreprocess(preprocess);
        }
        return "";
    }

    @Override
    public String visitPreprocess(MalpicParser.PreprocessContext ctx) {

        List<MalpicParser.PreprocessCommandContext> preprocessCommandChain = ctx.preprocessCommand();
        for (MalpicParser.PreprocessCommandContext preprocessCommand: preprocessCommandChain) {
            this.visitPreprocessCommand(preprocessCommand);
        }

        return "";
    }

    @Override
    public String visitPreprocessCommand(MalpicParser.PreprocessCommandContext ctx) {
        MalpicParser.PreprocessContext parent = (MalpicParser.PreprocessContext) ctx.getParent();
        if (parent == null)
            throw new RuntimeException(
                    "Generator Error: preprocess command " +
                    "requires reference to parent.\n");

        MalpicParser.VariableContext preprocessTarget = parent.variable();
        String preprocessTargetStr = preprocessTarget == null ?
                ipynbVariablesTable.getDatasetAlias() :
                ipynbVariablesTable.get(preprocessTarget.getText());


        String commandStr = ctx.getChild(0).getText();
        AbstractMalpicIpynbCommandPresenter command;
        command = ipynbCommandsTable.get(commandStr);

        List<TerminalNode> rawArgs = ctx.STRING();
        List<String> args = new ArrayList<>();
        args.add(preprocessTargetStr);
        args.add(preprocessTargetStr);
        for (TerminalNode rawArg: rawArgs) {
            args.add(rawArg.getText());
        }

        command.parseAndRun(args);
        int cellIndex = ipynbBuilder.nextCodeCell();
        //ipynbBuilder.appendIpynbSnippetToCell(command, cellIndex);
        return "";
    }

    @Override
    public String visitSource(MalpicParser.SourceContext ctx) {

        // Dataset Definition Snippet
        String sourceString = ctx.STRING().getText();
        String datasetAlias = ipynbVariablesTable.getDatasetAlias();

        MalpicIpynbSnippetPresenter datasetSnippet = ipynbCommandsTable.getReadCsvCallSnippet(
                datasetAlias,
                sourceString);

        MalpicIpynbAtomicStatementPresenter datasetDisplayStmt =
                new MalpicIpynbAtomicStatementPresenter(datasetAlias);
        datasetSnippet.addStatement(datasetDisplayStmt);
        int datasetCellIndex = ipynbBuilder.nextCodeCell();
        //

        // Target Definition Statement
        MalpicParser.SourceTargetContext sourceTarget = ctx.sourceTarget();
        String labelColumnName = sourceTarget.STRING().getText();
        String targetAlias = sourceTarget.variable().getText();
        ipynbVariablesTable.setTargetAlias(targetAlias);

        MalpicIpynbStatementPresenter targetInitStmt =
                new MalpicIpynbAssignmentStatementPresenter(
                        targetAlias,
                        datasetAlias + "[" + labelColumnName + "]");
        MalpicIpynbAtomicStatementPresenter targetDisplayStmt =
                new MalpicIpynbAtomicStatementPresenter(targetAlias);
        MalpicIpynbSnippetPresenter targetSnippet =
                new MalpicIpynbSnippetPresenter(
                        Arrays.asList(targetInitStmt, targetDisplayStmt)
                );
        int targetCellIndex = ipynbBuilder.nextCodeCell();
        //

        // Feature Definition Statement
        MalpicParser.SourceFeaturesContext sourceFeatures = ctx.sourceFeatures();
        TerminalNode featuresRemaining = sourceFeatures.REMAINING();
        String featuresAlias = sourceFeatures.variable().getText();
        ipynbVariablesTable.setFeaturesAlias(featuresAlias);


        MalpicIpynbAtomicStatementPresenter featuresDisplayStmt =
                new MalpicIpynbAtomicStatementPresenter(featuresAlias);

        MalpicIpynbSnippetPresenter featuresSnippet =
                ipynbCommandsTable.getRemoveColumnCmdCallSnippet(
                        featuresAlias,
                        datasetAlias,
                        labelColumnName);
        featuresSnippet.addStatement(featuresDisplayStmt);
        int featuresCellIndex = ipynbBuilder.nextCodeCell();

        // Build Ipynb
        ipynbBuilder.appendIpynbSnippetToCell(datasetSnippet, datasetCellIndex);
        ipynbBuilder.appendIpynbSnippetToCell(targetSnippet, targetCellIndex);
        ipynbBuilder.appendIpynbSnippetToCell(featuresSnippet, featuresCellIndex);
        return "";
    }
}
