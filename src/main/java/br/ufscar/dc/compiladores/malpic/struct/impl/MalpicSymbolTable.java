package br.ufscar.dc.compiladores.malpic.struct.impl;

import br.ufscar.dc.compiladores.malpic.struct.IMalpicSymbolTable;

import java.util.HashMap;


public class MalpicSymbolTable <K, V> implements IMalpicSymbolTable<K, V> {
    HashMap<K, V> hashMap;

    public MalpicSymbolTable() {
        this.hashMap = new HashMap<>();
    }
    @Override
    public void insert(K key, V value) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return this.hashMap.putIfAbsent(key, value);
    }
}
