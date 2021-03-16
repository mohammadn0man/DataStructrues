package com.company.lineardatastructures;

import com.company.lineardatastructures.nodes.HashNode;
import com.company.lineardatastructures.utils.HashNodeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyHashTable<K, V> implements Iterable<HashNode<K, V>> {

    List<HashNode<K, V>> bucket = new ArrayList<>();
    int numBuckets = 10;
    int size;

    public MyHashTable() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getBucketIndex(K key) {
        int hashCod = key.hashCode();
        return Math.abs(hashCod % numBuckets);
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(index);
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            V val = head.value;
            head = head.next;
            bucket.set(index, head);
            size--;
            return val;
        } else {
            HashNode<K, V> prev = head;
            while (head != null) {
                if (head.key.equals(key)) {
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }
            return null;
        }
    }

    public void add(K key, V value) {
        int index = getBucketIndex(key);
        System.out.println(index);
        HashNode<K, V> head = bucket.get(index);
        HashNode<K, V> toAdd = new HashNode<>(key, value);
        if (head == null) {
            bucket.set(index, toAdd);
            size++;
        } else {
            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
            if (head == null) {
                head = bucket.get(index);
                toAdd.next = head;
                bucket.set(index, toAdd);
                size++;
            }
        }
        if ((1.0 * size) / numBuckets > 0.7) {
            //do something
            List<HashNode<K, V>> tmp = bucket;
            bucket = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            for (int i = 0; i < numBuckets; i++) {
                bucket.add(null);
            }
            for (HashNode<K, V> headNode : tmp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }

        }

    }

    public V getValueByKey(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucket.get(bucketIndex);
        boolean isAvailable = false;
        while (head != null) {
            if (head.key.equals(key)) {
                isAvailable = true;
                break;
            }
            head = head.next;
        }
        return isAvailable;
    }

    public void print() {
        for (int index = 0; index < numBuckets; index++) {
            HashNode<K, V> head = bucket.get(index);
            while (head != null) {
                System.out.println(head.toString());
                head = head.next;
            }
        }
    }

    @Override
    public Iterator<HashNode<K, V>> iterator() {
        return new HashNodeIterator<>(bucket, numBuckets);
    }
}
