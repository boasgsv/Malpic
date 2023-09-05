package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.MalpicIpynbCellPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.MalpicIpynbCodeCellPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.MalpicIpynbPresenter;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbNotebookPresenter implements MalpicIpynbPresenter {
    List<MalpicIpynbCodeCellPresenter> ipynbCodeCells;
    List<MalpicIpynbCellPresenter> ipynbCells;
     public MalpicIpynbNotebookPresenter() {
         this.ipynbCodeCells = new ArrayList<>();
         this.ipynbCells = new ArrayList<>();
    }

    private int nextCell(MalpicIpynbCellPresenter ipynbCell) {
         this.ipynbCells.add(ipynbCell);
         return this.ipynbCells.size() - 1;
    }

    public int nextCodeCell() {
         MalpicIpynbCodeCellPresenter ipynbCodeCell = new MalpicIpynbCodeCellPresenter();
         this.ipynbCodeCells.add(ipynbCodeCell);
         int cellIndexInCells = this.nextCell(ipynbCodeCell);
         int cellIndexInCodeCells = this.ipynbCodeCells.size() - 1;
         if (cellIndexInCodeCells != cellIndexInCells)
             throw new RuntimeException(
                     "Notebook Error: Cell index " +
                     "mismatch in cells and codeCells");
         return cellIndexInCells;
    }


    public void appendIpynbSnippetToCell(
            MalpicIpynbSnippetPresenter pythonSnippet,
            int cellIndex) {
        try {
            this.ipynbCodeCells.get(cellIndex).appendPythonSnippet(pythonSnippet);
        } catch(IndexOutOfBoundsException e) {
            throw new RuntimeException(
                    "Notebook Error: Cannot append pythonSnippet[" + pythonSnippet +
                    "] to cell at index " + cellIndex + " because the cell does not exist.\n");
        }
    }

    private String getCellsContent() {
        // cells
        StringBuilder cellsContentBuilder = new StringBuilder();
        for (MalpicIpynbCellPresenter ipynbCell : this.ipynbCells) {
            String ipynbCellString = ipynbCell.toIpynbCell();
            cellsContentBuilder.append(ipynbCellString);
            cellsContentBuilder.append(",\n");
        }
        int length = cellsContentBuilder.length();
        cellsContentBuilder.delete(length - 2,  length - 1);
        return cellsContentBuilder.toString();
    }

    @Override
    public String toIpynb() {
        String cellsIpynbString = getCellsContent();
        return
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
    }
}
