package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.customexceptions.StackOverflowException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queue Test (implemented as separate class)")
class MyQueueTest {

    MyQueue<Integer> queue;

    @BeforeEach
    void init() throws QueueIsFullException {
        queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
    }

    @Test
    @DisplayName("Enqueue element")
    void enqueue() throws QueueIsFullException {
        queue.enqueue(1);
        assertEquals(1, queue.peek());
        assertEquals(5, queue.size());
    }

    @Test
    void dequeue() throws QueueIsEmptyException {
        assertEquals(1, queue.dequeue());
    }

    @Test
    void peek() throws QueueIsEmptyException {
        assertEquals(1, queue.peek());
        queue.dequeue();
        queue.dequeue();
        assertEquals(3, queue.peek());
    }

    @Test
    void contains() {
        assertTrue(queue.contains(1));
        assertFalse(queue.contains(5));
    }

    @Test
    void size() throws QueueIsEmptyException {
        assertEquals(4, queue.size());
        queue.dequeue();
        assertEquals(3, queue.size());
    }

    @Test
    void reverse() throws QueueIsEmptyException, EmptyListException, StackOverflowException, QueueIsFullException {
        queue.reverse();
        assertEquals(4, queue.peek());
    }

    @AfterAll
    static void end() {
        System.out.println("Test Completed.");
    }

}