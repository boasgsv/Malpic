package br.ufscar.dc.compiladores.malpic.visitors;

import br.ufscar.dc.compiladores.malpic.MalpicParser;
import br.ufscar.dc.compiladores.malpic.MalpicParserBaseVisitor;

public class Evaluator extends MalpicParserBaseVisitor<Double> {
    @Override
    public Double visitHeader(MalpicParser.HeaderContext ctx) {
        return super.visitHeader(ctx);
    }

    @Override
    public Double visitProgram(MalpicParser.ProgramContext ctx) {
        return super.visitProgram(ctx);
    }
}
