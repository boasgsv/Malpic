package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.MalpicIpynbStatementPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.libraryimport.MalpicIpynbLibraryImportStatementPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.dataset.MalpicDatasetIpynbGenerator;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbGenerator extends AbstractMalpicIpynbGenerator {
    MalpicDatasetIpynbGenerator datasetIpynbGenerator;
    public MalpicIpynbGenerator(){
        super();
        this.datasetIpynbGenerator = new MalpicDatasetIpynbGenerator();
    }

    @Override
    public String visitProgram(MalpicParser.ProgramContext ctx) {
        this.visitPipeline(ctx.pipeline());
        return ipynbBuilder.build();
    }

    @Override
    public String visitPipeline(MalpicParser.PipelineContext ctx) {
        List<MalpicIpynbStatementPresenter> stmtList = new ArrayList<>();
        MalpicIpynbLibraryImportStatementPresenter numpyImportStmt =
                new MalpicIpynbLibraryImportStatementPresenter(
                        "numpy",
                        "np");

        MalpicIpynbLibraryImportStatementPresenter pandasImport =
                new MalpicIpynbLibraryImportStatementPresenter("pandas", "pd");

        if (ipynbImportsTable.putIfAbsent(numpyImportStmt.getId(), numpyImportStmt) == null)
            stmtList.add(numpyImportStmt);

        if (ipynbImportsTable.putIfAbsent(pandasImport.getId(), pandasImport) == null)
            stmtList.add(pandasImport);

        MalpicIpynbSnippetPresenter pipelineImportSnippet =
                new MalpicIpynbSnippetPresenter(stmtList);

        int cellIndex = ipynbBuilder.nextCodeCell();
        ipynbBuilder.appendIpynbSnippetToCell(pipelineImportSnippet, cellIndex);
        datasetIpynbGenerator.visitDataset(ctx.dataset());
        return "";

    }
}
