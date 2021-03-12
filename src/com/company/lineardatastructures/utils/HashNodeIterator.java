package com.company.lineardatastructures.utils;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class HashNodeIterator<K, V> implements Iterator<HashNode<K, V>> {

    private List<HashNode<K, V>> bucket;
    private int currentIndex = 0;
    private final int bucketSize;
    private HashNode<K, V> currentNode;


    public HashNodeIterator(List<HashNode<K, V>> bucket, int bucketSize) {
        this.bucket = bucket;
        this.bucketSize = bucketSize;
        this.currentNode = bucket.get(currentIndex);
    }

    @Override
    public boolean hasNext() {
        while (currentNode == null) {
            currentIndex++;
            if (currentIndex < bucketSize) {
                currentNode = bucket.get(currentIndex);
            } else {
                break;
            }
        }
        return currentNode != null;
    }

    @Override
    public HashNode<K, V> next() throws NoSuchElementException {
        HashNode<K, V> ret = currentNode;
        if (currentNode != null) {
            currentNode = currentNode.next;
        }
        if (ret == null){
            throw new NoSuchElementException("element not found.");
        }
        return ret;
    }
}
