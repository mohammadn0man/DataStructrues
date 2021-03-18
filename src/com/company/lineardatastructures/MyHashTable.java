package com.company.lineardatastructures;

import com.company.lineardatastructures.nodes.HashNode;
import com.company.lineardatastructures.utils.HashNodeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is Hash Table implementation with essential methods, it is not
 * sure that the order of the insertion of the items is preserved and it
 * may also change during the time when size of bucket increases depending
 * upon the load factor.
 *
 * @param <K> the type of Keys held in this collection
 * @param <V> the type of Values held in this collection
 */
public class MyHashTable<K, V> implements Iterable<HashNode<K, V>> {

    private List<HashNode<K, V>> bucket = new ArrayList<>();
    private int numBuckets = 10;
    private int size;

    /**
     * Initialises the bucked with null elements.
     */
    public MyHashTable() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    /**
     * Total no of entries present in the table.
     * @return size of the table
     */
    public int getSize() {
        return size;
    }

    /**
     * @return true if stack is empty, else false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * calculate the hashcode of the key using object class method and
     * compress it accoring to bucket size.
     *
     * @param key the key of element
     * @return hash of the input key
     */
    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % numBuckets);
    }

    /**
     * Removes the element whose key is passed.
     *
     * @param key key of the element
     * @return value of removed element {@code null} if not found
     */
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

    /**
     * Insert element after calculating hashcode if load factor increases
     * certain limit, double the size of bucket and re-add all the elements
     * by again calculating hash code.
     *
     * @param key key for the record
     * @param value value for the record
     */
    public void add(K key, V value) {
        int index = getBucketIndex(key);
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
            size = 1;
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

    /**
     * Retrieve values according to key passed by calculating hash code
     * for index in the bucket.
     *
     * @param key the key of the value
     * @return value associated with the given key
     */
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

    /**
     * Checks if given key exists.
     *
     * @param key key to find
     * @return true if found, else false.
     */
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


    /**
     * Print the entire table
     */
    public void print() {
        for (int index = 0; index < numBuckets; index++) {
            HashNode<K, V> head = bucket.get(index);
            while (head != null) {
                System.out.println(head.toString());
                head = head.next;
            }
        }
    }

    /**
     * Make the table compatible to use with foreach loop
     *
     * @return list {@code Iterator}
     */
    @Override
    public Iterator<HashNode<K, V>> iterator() {
        return new HashNodeIterator<>(bucket, numBuckets);
    }
}
