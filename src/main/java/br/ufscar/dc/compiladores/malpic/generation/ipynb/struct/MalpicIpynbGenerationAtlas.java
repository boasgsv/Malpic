package br.ufscar.dc.compiladores.malpic.generation.ipynb.struct;

public class MalpicIpynbGenerationAtlas {
    MalpicIpynbImportsTable importsTable;

    public MalpicIpynbGenerationAtlas() {
        importsTable = new MalpicIpynbImportsTable();
    }

    public MalpicIpynbImportsTable getImportsTable() {
        return this.importsTable;
    }
}
