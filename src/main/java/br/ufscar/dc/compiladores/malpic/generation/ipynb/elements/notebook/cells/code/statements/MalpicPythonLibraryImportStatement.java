package br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements;

public class MalpicPythonLibraryImportStatement implements MalpicPythonStatement {
    String libraryName;
    String alias;

    public MalpicPythonLibraryImportStatement(String libraryName, String alias) {
        this.libraryName = libraryName;
        this.alias = alias;
    }

    public String getId() {
        // in python, identifier is either the libraryName or its alias, if it exists
        return this.alias == null ? this.libraryName : this.alias;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toPythonString() {
        String trail = this.alias == null ? "" : " as " + alias;
        return "import " + libraryName + trail;
    }
}
