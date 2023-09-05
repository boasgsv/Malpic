package br.ufscar.dc.compiladores.malpic.struct.spec;

public interface MalpicSymbolTable<K, V> {

    V get(K key);

    V putIfAbsent(K key, V value);

    void put(K key, V value);
}
