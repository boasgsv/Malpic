package br.ufscar.dc.compiladores.malpic.struct;

public interface IMalpicSymbolTable <K, V> {
    public void insert(K key, V value);

    public V putIfAbsent(K key, V value);
}
