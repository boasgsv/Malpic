package br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.MalpicIpynbCell;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonStatement;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbCodeCell implements MalpicIpynbCell {
    List<MalpicPythonStatement> pythonStatements;
    public MalpicIpynbCodeCell() {
        this.pythonStatements = new ArrayList<>();
    }


    @Override
    public boolean isEmpty() {
        return this.pythonStatements.isEmpty();
    }

    public void appendPythonStatement(MalpicPythonStatement pythonStatement) {
        this.pythonStatements.add(pythonStatement);
    }

    @Override
    public void clear() {
        this.pythonStatements.clear();
    }

    private String getCodeCellIpynbString() {
        StringBuilder pyCodeSnippetBuilder = new StringBuilder();
        pyCodeSnippetBuilder.append("\n");

        for (MalpicPythonStatement pythonStatement: this.pythonStatements) {
            String pyCodeLine = pythonStatement.toPythonString();
            pyCodeLine = pyCodeLine.replaceAll("\"", "\\\\\"");
            pyCodeSnippetBuilder.append("\"");
            pyCodeSnippetBuilder.append(pyCodeLine);
            pyCodeSnippetBuilder.append("\\n\",\n");
        }
        String pyCodeSnippet = pyCodeSnippetBuilder.toString();
        pyCodeSnippet = pyCodeSnippet.substring(0, pyCodeSnippet.length() - 5) + "\"\n";
        return pyCodeSnippet;

    }

    @Override
    public String toIpynbString() {
        String codeCellIpynbString = this.isEmpty() ? "" : getCodeCellIpynbString();
        String ipynbString =  "{\n" +
                "   \"cell_type\": \"code\",\n" +
                "   \"execution_count\": null,\n" +
                "   \"metadata\": {\n" +
                "    \"collapsed\": true,\n" +
                "    \"is_executing\": true\n" +
                "   },\n" +
                "   \"outputs\": [],\n" +
                "   \"source\": [" +
                codeCellIpynbString +
                "   ]\n" +
                "}";
        return ipynbString;
    }
}
