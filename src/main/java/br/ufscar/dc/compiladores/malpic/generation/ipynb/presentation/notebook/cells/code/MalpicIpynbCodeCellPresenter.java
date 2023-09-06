package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.MalpicIpynbCellPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;

import java.util.ArrayList;
import java.util.List;

public class MalpicIpynbCodeCellPresenter implements MalpicIpynbCellPresenter {
    List<MalpicIpynbSnippetPresenter> ipynbSnippets;

    public MalpicIpynbCodeCellPresenter() {
        this.ipynbSnippets = new ArrayList<>();
    }


    @Override
    public boolean isEmpty() {
        return this.ipynbSnippets.isEmpty();
    }

    public void appendPythonSnippet(MalpicIpynbSnippetPresenter pythonSnippet) {
        this.ipynbSnippets.add(pythonSnippet);
    }

    public String toIpynbCell() {
        String cellSourceContents = this.isEmpty() ? "" : getCellSourceContents();
        return "{\n" +
                "   \"cell_type\": \"code\",\n" +
                "   \"execution_count\": null,\n" +
                "   \"metadata\": {\n" +
                "    \"collapsed\": true,\n" +
                "    \"is_executing\": true\n" +
                "   },\n" +
                "   \"outputs\": [],\n" +
                "   \"source\": [" +
                cellSourceContents +
                "   ]\n" +
                "}";
    }

    private String getCellSourceContents() {
        StringBuilder cellSourceContentsBuilder = new StringBuilder();
        cellSourceContentsBuilder.append('\n'); // ipynb: source: [\n ...

        for (MalpicIpynbSnippetPresenter ipynbSnippet : this.ipynbSnippets) {
            String ipynbSnippetString = ipynbSnippet.toIpynbSnippet(); // first statement should start with "
            cellSourceContentsBuilder.append(ipynbSnippetString);
            cellSourceContentsBuilder.append("\"\\n\",\n"); // between each snippet we should have "\n",
        }

        return cellSourceContentsBuilder.substring(
                0,
                cellSourceContentsBuilder.length() - 11)
                + "\"\n";
    }

}