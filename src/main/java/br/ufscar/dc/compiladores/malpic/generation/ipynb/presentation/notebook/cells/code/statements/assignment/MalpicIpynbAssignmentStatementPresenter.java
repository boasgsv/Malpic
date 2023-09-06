package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.assignment;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.AbstractMalpicIpynbStatementPresenter;

public class MalpicIpynbAssignmentStatementPresenter extends AbstractMalpicIpynbStatementPresenter {
    String lhs;
    String rhs;

    public MalpicIpynbAssignmentStatementPresenter(String lhs, String rhs) {
        super(lhs + " = " + rhs);
        this.lhs = lhs;
        this.rhs = rhs;
    }

}
