package com.company.lineardatastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    void insertItem(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insert(21);
        list.insert(321);
        assertNotNull(list);
//        assertIterableEquals(list, list);
        assertEquals(2, list.size());
    }
}