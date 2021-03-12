package com.company.lineardatastructures.utils;

public class HashNode<K, V> {
    public K key;
    public V value;
    public HashNode<K, V> next;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}