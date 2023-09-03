package br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements;

public class MalpicPythonAssignmentStatement implements MalpicPythonStatement{
    String ref;
    String value;

    public MalpicPythonAssignmentStatement(String ref, String value) {
        this.ref = ref;
        this.value = value;
    }

    @Override
    public String toPythonString() {
        return ref + " = " + value;
    }
}
