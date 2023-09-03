package br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.notebook.cells;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.elements.MalpicIpynbConvertible;

public interface MalpicIpynbCell extends MalpicIpynbConvertible {
    boolean isEmpty();
    void clear();

}
