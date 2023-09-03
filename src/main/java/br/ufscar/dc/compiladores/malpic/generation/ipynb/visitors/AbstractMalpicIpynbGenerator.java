package br.ufscar.dc.compiladores.malpic.generation.ipynb.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicBaseVisitor;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.builders.MalpicIpynbBuilder;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.struct.MalpicIpynbGenerationAtlas;

public abstract class AbstractMalpicIpynbGenerator extends MalpicBaseVisitor<String> {
    static protected MalpicIpynbBuilder ipynbBuilder;
    protected MalpicIpynbGenerationAtlas ipynbGenerationAtlas;
    public AbstractMalpicIpynbGenerator() {
        this.ipynbBuilder = new MalpicIpynbBuilder();
        this.ipynbGenerationAtlas = new MalpicIpynbGenerationAtlas();

    }

}
