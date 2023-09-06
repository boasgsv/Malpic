package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.assignment.MalpicIpynbAssignmentStatementPresenter;

import java.util.List;

public class MalpicIpynbReadCsvCommandPresenter extends AbstractMalpicIpynbCommandPresenter {
    static private final int dataframeArgNo = 0;
    static private final int pathArgNo = 1;

    String dataframeParam;
    String pathParam;

    public void setDataframeParam(String dataframe) {
        this.dataframeParam = dataframe;
    }

    public void setPathParam(String path) {
        this.pathParam = path;
    }


    @Override
    public void run() {
        MalpicIpynbAssignmentStatementPresenter readCsvStmt =
                new MalpicIpynbAssignmentStatementPresenter(
                        this.dataframeParam,
                        "pd.read_csv(" + pathParam + ")"
                );
        super.addStatement(readCsvStmt);
    }

    @Override
    public void parseAndRun(List<String> args) {
        assert(args.size() == 2);
        this.dataframeParam = args.get(dataframeArgNo);
        this.pathParam = args.get(pathArgNo);
        run();
    }
}
