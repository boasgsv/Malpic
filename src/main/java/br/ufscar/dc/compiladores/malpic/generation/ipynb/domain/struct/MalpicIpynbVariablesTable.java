package br.ufscar.dc.compiladores.malpic.generation.ipynb.domain.struct;

import br.ufscar.dc.compiladores.malpic.struct.impl.AbstractMalpicSymbolTable;


public class MalpicIpynbVariablesTable extends AbstractMalpicSymbolTable <String, String> {
    static private final String dataset = "dataset";
    static private final String target = "target";
    static private final String features = "features";

    public MalpicIpynbVariablesTable() {
        super.put(dataset, "df");
    }




    public void setDatasetAlias(String value) {
        super.put(dataset, value);
    }

    public void setTargetAlias(String value) {
        super.put(target, value);
    }

    public void setFeaturesAlias(String value) {
        super.put(features, value);
    }

    public String getDatasetAlias() {
        return super.get(dataset);
    }

    public String getTargetAlias() {
        return super.get(target);
    }

    public String getFeaturesAlias() {
        return super.get(features);
    }


}
