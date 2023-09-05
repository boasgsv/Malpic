package br.ufscar.dc.compiladores.malpic.generation.ipynb.builders;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.MalpicIpynbNotebookPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;


public class MalpicIpynbBuilder {
    MalpicIpynbNotebookPresenter ipynbNotebook;
    public MalpicIpynbBuilder() {
        this.ipynbNotebook = new MalpicIpynbNotebookPresenter();
    }

    public int nextCodeCell() {
        return this.ipynbNotebook.nextCodeCell();
    }

    public void appendIpynbSnippetToCell(MalpicIpynbSnippetPresenter pythonSnippet, int cellIndex) {
        this.ipynbNotebook.appendIpynbSnippetToCell(pythonSnippet, cellIndex);
    }

    public String build() {
        return ipynbNotebook.toIpynb();
    }

    public void reset() {

    }
}
