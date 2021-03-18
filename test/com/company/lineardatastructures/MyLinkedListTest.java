package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.InvalidPositionException;
import com.company.lineardatastructures.nodes.Node;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Linked List Test")
class MyLinkedListTest {

    private static MyLinkedList<Integer> list;

    @BeforeAll
    static void init() {
        list = new MyLinkedList<>();
    }

    @BeforeEach
    void tearDown(){
        list = new MyLinkedList<>();
    }

    @Nested
    @DisplayName("Insertion Test")
    class InsertionTest {
        @Test
        @DisplayName("Default insert")
        void insertItem() {
            list.insert(21);
            assertNotNull(list);
            assertEquals(1, list.size());
            list.insert(321);
            assertEquals(2, list.size());
        }

        @Test
        @DisplayName("At given index")
        void insertAtIndex() throws InvalidPositionException {
            list.insertAtPosition(1000, 0);
            assertEquals(1000, list.getHead().data);
        }

        @Test
        @DisplayName("At End")
        void insertAtEnd() throws EmptyListException, InvalidPositionException {
            list.insertAtEnd(2000);
            assertEquals(2000, list.getNodeAt(list.size()).data);
        }

    }

    @Nested
    @DisplayName("Deletion Test")
    class DeletionTest {
        @BeforeEach
        void init(){
            list.insert(1);
            list.insert(2);
            list.insert(3);
            list.insert(4);
        }

        @Test
        @DisplayName("Default deletion")
        void delete() throws EmptyListException {
            list.delete();
            assertEquals(3, list.size());
            list.delete();
            assertEquals(2, list.size());
            assertEquals(2, list.getHead().data);
        }

        @Test
        @DisplayName("Deletion at given Index")
        void deleteAtIndex() throws InvalidPositionException, EmptyListException {
            list.insert(5);
            list.deleteAtPosition(4);
            assertEquals(4, list.size());
            Node<Integer> node = list.getHead();
            assertNotEquals(2, list.getNodeAt(3).data);
            assertEquals(5, list.getHead().data);
        }

        @Test
        @DisplayName("Deletion at end")
        void deleteFromEnd() throws EmptyListException, InvalidPositionException {
            list.insert(5);
            list.deleteAtEnd();
            assertEquals(2, list.getNodeAt(list.size() - 1).data);
        }

    }

    @Test
    @DisplayName("Center element test")
    void centerElement() throws EmptyListException {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        assertEquals(3, list.center());
        list.insert(6);
        assertEquals(4, list.center());
    }

    @Test
    @DisplayName("Reverse the list test")
    void reverseList() throws EmptyListException, InvalidPositionException {
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.reverse();
        assertEquals(1, list.getHead().data);
        assertEquals(4, list.getNodeAt(list.size() - 1).data);
    }

    @AfterAll
    static void end(){
        System.out.println("Test completed");
    }

}