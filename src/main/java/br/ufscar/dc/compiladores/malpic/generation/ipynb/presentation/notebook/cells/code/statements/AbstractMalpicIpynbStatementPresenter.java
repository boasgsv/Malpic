package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements;

public abstract class AbstractMalpicIpynbStatementPresenter implements MalpicIpynbStatementPresenter{
    String rawStatement;

    protected AbstractMalpicIpynbStatementPresenter(String rawStatement) {
        this.rawStatement = rawStatement + "\\n";
    }
    @Override
    public String toIpynbStatement() {
        return '"' + rawStatement.replaceAll(
                "\"",
                "\\\\\"") + '"';
    }
}
