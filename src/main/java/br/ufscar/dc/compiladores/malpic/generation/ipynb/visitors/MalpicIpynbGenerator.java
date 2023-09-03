package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonLibraryImportStatement;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.struct.MalpicIpynbImportsTable;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors.dataset.MalpicDatasetIpynbGenerator;

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
        MalpicIpynbImportsTable importsTable = super.ipynbGenerationAtlas.getImportsTable();
        int cellIndex = ipynbBuilder.nextCodeCell();

        MalpicPythonLibraryImportStatement numpyImport =
                new MalpicPythonLibraryImportStatement("numpy", "np");
        if (importsTable.putIfAbsent(numpyImport.getId(), numpyImport) == null)
            ipynbBuilder.appendPythonStatementToCell(numpyImport, cellIndex);

        MalpicPythonLibraryImportStatement pandasImport =
                new MalpicPythonLibraryImportStatement("pandas", "pd");
        if (importsTable.putIfAbsent(pandasImport.getId(), pandasImport) == null)
            ipynbBuilder.appendPythonStatementToCell(pandasImport, cellIndex);

        datasetIpynbGenerator.visitDataset(ctx.dataset());
        return "";

    }
}
