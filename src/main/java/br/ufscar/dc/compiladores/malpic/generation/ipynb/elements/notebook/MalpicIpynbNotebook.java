package br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.statements.MalpicPythonStatement;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.MalpicIpynbCell;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells.code.MalpicIpynbCodeCell;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.MalpicIpynbConvertible;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbNotebook implements MalpicIpynbConvertible {
    List<MalpicIpynbCodeCell> ipynbCodeCells;
    List<MalpicIpynbCell> ipynbCells;
     public MalpicIpynbNotebook() {
         this.ipynbCodeCells = new ArrayList<>();
         this.ipynbCells = new ArrayList<>();
    }

    private int nextCell(MalpicIpynbCell ipynbCell) {
         this.ipynbCells.add(ipynbCell);
         return this.ipynbCells.size() - 1;
    }

    public int nextCodeCell() {
         MalpicIpynbCodeCell ipynbCodeCell = new MalpicIpynbCodeCell();
         this.ipynbCodeCells.add(ipynbCodeCell);
         int cellIndexInCells = this.nextCell(ipynbCodeCell);
         int cellIndexInCodeCells = this.ipynbCodeCells.size() - 1;
         if (cellIndexInCodeCells != cellIndexInCells)
             throw new RuntimeException(
                     "Notebook Error: Cell index " +
                     "mismatch in cells and codeCells");
         return cellIndexInCells;
    }


    public void appendPythonStatementToCell(
            MalpicPythonStatement pythonStatement,
            int cellIndex) {
        try {
            this.ipynbCodeCells.get(cellIndex).appendPythonStatement(pythonStatement);
        } catch(IndexOutOfBoundsException e) {
            throw new RuntimeException(
                    "Notebook Error: Cannot append pythonStatement[" + pythonStatement +
                    "] to cell at index " + cellIndex + " because the cell does not exist.\n");
        }
    }

    private String getCellsIpynbString() {
        // cells
        StringBuilder cellsIpynbStringBuilder = new StringBuilder();
        for (MalpicIpynbCell ipynbCell : this.ipynbCells) {
            String ipynbCellString = ipynbCell.toIpynbString();
            cellsIpynbStringBuilder.append(ipynbCellString);
            cellsIpynbStringBuilder.append(",\n");
        }
        int length = cellsIpynbStringBuilder.length();
        cellsIpynbStringBuilder.delete(length - 2,  length - 1);
        return cellsIpynbStringBuilder.toString();
    }

    @Override
    public String toIpynbString() {
        String cellsIpynbString = getCellsIpynbString();
        String ipynbString =
                "{\n" +
                " \"cells\": [\n" +
                    cellsIpynbString +
                    "],\n" +
                " \"metadata\": {\n" +
                "  \"kernelspec\": {\n" +
                "   \"display_name\": \"Python 3\",\n" +
                "   \"language\": \"python\",\n" +
                "   \"name\": \"python3\"\n" +
                "  },\n" +
                "  \"language_info\": {\n" +
                "   \"codemirror_mode\": {\n" +
                "    \"name\": \"ipython\",\n" +
                "    \"version\": 2\n" +
                "   },\n" +
                "   \"file_extension\": \".py\",\n" +
                "   \"mimetype\": \"text/x-python\",\n" +
                "   \"name\": \"python\",\n" +
                "   \"nbconvert_exporter\": \"python\",\n" +
                "   \"pygments_lexer\": \"ipython2\",\n" +
                "   \"version\": \"2.7.6\"\n" +
                "  }\n" +
                " },\n" +
                " \"nbformat\": 4,\n" +
                " \"nbformat_minor\": 0\n" +
                "}\n";
        return ipynbString;
    }
}
