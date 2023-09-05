package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.MalpicIpynbStatementPresenter;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbSnippetPresenter {
    List<MalpicIpynbStatementPresenter> ipynbStatements;

    public MalpicIpynbSnippetPresenter(List<MalpicIpynbStatementPresenter> ipynbStatements) {
        this.ipynbStatements = ipynbStatements;
    }

    public void clear() {
        ipynbStatements.clear();
    }

    public void addStatement(MalpicIpynbStatementPresenter ipynbStatement) {
        this.ipynbStatements.add(ipynbStatement);
    }

    public String toIpynbSnippet() {
        return this.getSnippetContents();
    }

    private String getSnippetContents() {
        StringBuilder ipynbSnippetBuilder = new StringBuilder();
        for (MalpicIpynbStatementPresenter ipynbStatement : this.ipynbStatements) {
            String ipynbStatementString = ipynbStatement.toIpynbStatement();
            ipynbSnippetBuilder.append(ipynbStatementString);
            ipynbSnippetBuilder.append(',');
            ipynbSnippetBuilder.append('\n');
        }
        return ipynbSnippetBuilder.toString();
    }
}
