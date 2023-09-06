package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.assignment.MalpicIpynbAssignmentStatementPresenter;

import java.util.List;


public class MalpicIpynbRemoveColumnCommandPresenter extends AbstractMalpicIpynbCommandPresenter {
    static private final int lhsDataframeArgNo = 0;
    static private final int rhsDataframeArgNo = 1;
    static private final int columnNameArgNo = 2;

    String lhsDataframeParam;
    String rhsDataframeParam;
    String columnNameParam;

    public void setLhsDataframeParam(String lhsDataframeParam) {
        this.lhsDataframeParam = lhsDataframeParam;
    }

    public void setRhsDataframeParam(String rhsDataframeParam) {
        this.rhsDataframeParam = rhsDataframeParam;
    }

    public void setColumnNameParam(String columnNameParam) {
        this.columnNameParam = columnNameParam;
    }


    @Override
    public void run() {
        super.clear();
        MalpicIpynbAssignmentStatementPresenter removeCmdStmt =
                new MalpicIpynbAssignmentStatementPresenter(
                        lhsDataframeParam,
                    rhsDataframeParam + ".drop(" + columnNameParam + ", axis=1)");
        super.addStatement(removeCmdStmt);
    }

    @Override
    public void parseAndRun(List<String> args) {
        if (args.size() != 3)
            throw new RuntimeException("Error in args size!\n");
        this.lhsDataframeParam = args.get(lhsDataframeArgNo);
        this.rhsDataframeParam = args.get(rhsDataframeArgNo);
        this.columnNameParam = args.get(columnNameArgNo);
        run();
    }
}
