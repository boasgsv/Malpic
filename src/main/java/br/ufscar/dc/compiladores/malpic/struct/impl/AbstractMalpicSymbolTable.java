package br.ufscar.dc.compiladores.malpic.struct.impl;

import br.ufscar.dc.compiladores.malpic.struct.spec.MalpicSymbolTable;

import java.util.HashMap;


public abstract class AbstractMalpicSymbolTable<K, V> implements MalpicSymbolTable<K, V> {
    private HashMap<K, V> hashMap;

    public AbstractMalpicSymbolTable() {
        this.hashMap = new HashMap<>();
    }

    public V get(K key) {
        return this.hashMap.get(key);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return this.hashMap.putIfAbsent(key, value);
    }

    public void put(K key, V value) {
        this.hashMap.put(key, value);
    }

}
