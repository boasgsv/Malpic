package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.builders.MalpicIpynbBuilder;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.domain.struct.MalpicIpynbCommandsTable;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.domain.struct.MalpicIpynbImportsTable;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.domain.struct.MalpicIpynbVariablesTable;

public abstract class AbstractMalpicIpynbGenerator extends MalpicBaseVisitor<String> {
    static protected MalpicIpynbBuilder ipynbBuilder = new MalpicIpynbBuilder();
    static protected MalpicIpynbImportsTable ipynbImportsTable = new MalpicIpynbImportsTable();
    static protected MalpicIpynbVariablesTable ipynbVariablesTable = new MalpicIpynbVariablesTable();
    static protected MalpicIpynbCommandsTable ipynbCommandsTable = new MalpicIpynbCommandsTable();
}
