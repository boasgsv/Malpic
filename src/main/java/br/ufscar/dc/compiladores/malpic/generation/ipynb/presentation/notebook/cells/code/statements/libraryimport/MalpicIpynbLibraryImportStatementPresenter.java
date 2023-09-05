package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.libraryimport;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.statements.AbstractMalpicIpynbStatementPresenter;

public class MalpicIpynbLibraryImportStatementPresenter extends AbstractMalpicIpynbStatementPresenter {
    String libraryName;
    String alias;

    public MalpicIpynbLibraryImportStatementPresenter(String libraryName, String alias) {
        super("import " + libraryName + " as " + alias);
        this.libraryName = libraryName;
        this.alias = alias;
    }

    public String getId() {
        // in python, identifier is either the libraryName or its alias, if it exists
        return this.alias == null ? this.libraryName : this.alias;
    }

}
