package br.ufscar.dc.compiladores.malpic.generation.ipynb.builders;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.MalpicIpynbNotebook;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonStatement;


public class MalpicIpynbBuilder {
    MalpicIpynbNotebook ipynbNotebook;
    public MalpicIpynbBuilder() {
        this.ipynbNotebook = new MalpicIpynbNotebook();
    }

    public int nextCodeCell() {
        return this.ipynbNotebook.nextCodeCell();
    }

    public void appendPythonStatementToCell(MalpicPythonStatement pythonStatement, int cellIndex) {
        try {
            this.ipynbNotebook.appendPythonStatementToCell(pythonStatement, cellIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException( "IpynbBuilder Error: cannot add pyCodeLine " +
                                        "to cell because cell does not exist");
        }
    }

    public String build() {
        return ipynbNotebook.toIpynbString();
    }

    public void reset() {

    }
}
