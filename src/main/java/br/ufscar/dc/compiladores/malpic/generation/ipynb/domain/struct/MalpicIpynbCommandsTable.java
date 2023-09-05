package br.ufscar.dc.compiladores.malpic.generation.ipynb.domain.struct;

import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.MalpicIpynbSnippetPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands.AbstractMalpicIpynbCommandPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands.MalpicIpynbReadCsvCommandPresenter;
import br.ufscar.dc.compiladores.malpic.generation.ipynb.presentation.notebook.cells.code.snippets.commands.MalpicIpynbRemoveColumnCommandPresenter;
import br.ufscar.dc.compiladores.malpic.struct.impl.AbstractMalpicSymbolTable;

public class MalpicIpynbCommandsTable extends AbstractMalpicSymbolTable<String, AbstractMalpicIpynbCommandPresenter> {
    static private final String removeColumnCmd = "remove_column";
    static private final String readCsvCmd = "read_csv";
    static private final String normalizeCmd = "normalize";

    static private final String oneHotEncodeCmd = "one_hot_encode";


    public MalpicIpynbCommandsTable() {
        super();
        super.put(removeColumnCmd, new MalpicIpynbRemoveColumnCommandPresenter());
        super.put(readCsvCmd, new MalpicIpynbReadCsvCommandPresenter());
    }

    public MalpicIpynbSnippetPresenter getRemoveColumnCmdCallSnippet(
            String rhsDataframe,
            String lhsDataframe,
            String columnName) {
        MalpicIpynbRemoveColumnCommandPresenter cmd =
                (MalpicIpynbRemoveColumnCommandPresenter) super.get(removeColumnCmd);
        cmd.setLhsDataframeParam(rhsDataframe);
        cmd.setRhsDataframeParam(lhsDataframe);
        cmd.setColumnNameParam(columnName);
        cmd.run();
        return cmd;
    }

    public MalpicIpynbSnippetPresenter getReadCsvCallSnippet(
            String dataframe, String path
    ) {
        MalpicIpynbReadCsvCommandPresenter cmd =
                (MalpicIpynbReadCsvCommandPresenter) super.get(readCsvCmd);
        cmd.setDataframeParam(dataframe);
        cmd.setPathParam(path);
        cmd.run();
        return cmd;
    }

}
