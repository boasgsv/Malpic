package br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMalpicIpynbCommandPresenter extends MalpicIpynbSnippetPresenter implements Runnable {
    public AbstractMalpicIpynbCommandPresenter() {
        super(new ArrayList<>());
    }
    public abstract void parseAndRun(List<String> args);

}
