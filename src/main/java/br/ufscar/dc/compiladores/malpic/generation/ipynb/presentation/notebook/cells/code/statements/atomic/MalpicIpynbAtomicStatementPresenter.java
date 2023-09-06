package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.atomic;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.AbstractMalpicIpynbStatementPresenter;

public class MalpicIpynbAtomicStatementPresenter extends AbstractMalpicIpynbStatementPresenter {
    String atom;
    public MalpicIpynbAtomicStatementPresenter(String atom) {
        super(atom);
        this.atom = atom;
    }
}
