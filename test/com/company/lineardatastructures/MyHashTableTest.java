package com.company.lineardatastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hash Table Test")
class MyHashTableTest {
    MyHashTable<String, Integer> hashTable;

    @BeforeEach
    void init(){
        hashTable = new MyHashTable<>();
        hashTable.add("this", 1);
        hashTable.add("thisasd", 12);
        hashTable.add("thasdis", 13);
        hashTable.add("thasdais", 14);
        hashTable.add("thasdasis", 15);
        hashTable.add("thiadsas", 17);
        hashTable.add("thasdais", 19);
    }

    @Test
    @DisplayName("Size of hash table check")
    void getSize() {
        assertEquals(7, hashTable.getSize());
        hashTable.remove("thasdasis");
        assertEquals(6, hashTable.getSize());

    }

    @Test
    @DisplayName("Empty table test")
    void isEmpty() {
        assertFalse(hashTable.isEmpty());
        hashTable = new MyHashTable<>();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    @DisplayName("Remove element")
    void remove() {
        hashTable.remove("thasdasis");
        assertEquals(6, hashTable.getSize());
        assertNull(hashTable.getValueByKey("thasdasis"));
        assertEquals(1, hashTable.getValueByKey("this"));
    }

    @Test
    @DisplayName("Insert element")
    void add() {
        assertEquals(7, hashTable.getSize());
        hashTable.add("hello", 13);
        assertEquals(8, hashTable.getSize());
        assertEquals(13, hashTable.getValueByKey("hello"));
    }

    @Test
    @DisplayName("Retrieve value by key")
    void getValueByKey() {
        assertEquals(1, hashTable.getValueByKey("this"));
        assertNull(hashTable.getValueByKey("sdasis"));
    }

    @Test
    @DisplayName("Contains key")
    void containsKey() {
        assertFalse(hashTable.containsKey("tasdfas"));
        assertTrue(hashTable.containsKey("this"));
    }
}