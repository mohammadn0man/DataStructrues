package com.company.lineardatastructures;

import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.lineardatastructures.nodes.PriorityNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Priority Queue Test")
class MyPriorityQueueTest {
    MyPriorityQueue<Integer> queue;

    @BeforeEach
    void init() throws QueueIsFullException {
        queue = new MyPriorityQueue<>();
        queue.enqueue(10, 1);
        queue.enqueue(1, 3);
        queue.enqueue(2, 4);
        queue.enqueue(3);
        queue.enqueue(4, 100);
        queue.enqueue(5, 23);
        queue.enqueue(6, 32);
        queue.enqueue(7, 23);
        queue.enqueue(8);
        queue.enqueue(9);
    }

    @Nested
    @DisplayName("Enqueue ")
    class enqueueTest {
        @Test
        @DisplayName("with value only ")
        void enqueue() throws QueueIsFullException, QueueIsEmptyException {
            queue.enqueue(10);
            assertEquals(11, queue.size());
            queue.reverse();
            assertEquals(4, queue.peek());
        }

        @Test
        @DisplayName("with value and priority")
        void testEnqueue() throws QueueIsFullException, QueueIsEmptyException {
            queue.enqueue(10, 3);
            assertEquals(11, queue.size());
            queue.reverse();
            assertEquals(4, queue.peek());
        }

        @Test
        @DisplayName("with node type")
        void testEnqueue1() throws QueueIsFullException, QueueIsEmptyException {
            queue.enqueue(new PriorityNode<>(10, 10, null));
            assertEquals(11, queue.size());
            queue.reverse();
            assertEquals(4, queue.peek());
        }

    }

    @Test
    @DisplayName("Dequeue element")
    void dequeue() throws QueueIsEmptyException {
        assertEquals(10, queue.dequeue());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    @DisplayName("Peek element ")
    void peek() throws QueueIsEmptyException {
        assertEquals(10, queue.peek());
        queue.dequeue();
        assertEquals(1, queue.peek());
        queue.dequeue();
        assertEquals(2, queue.peek());
        queue.dequeue();
        assertEquals(3, queue.peek());
    }

    @Test
    @DisplayName("Contains elements ")
    void contains() {
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));
        assertFalse(queue.contains(11));
        assertFalse(queue.contains(12));
    }

    @Test
    @DisplayName("Queue is empty ")
    void isEmpty() throws QueueIsEmptyException {
        assertFalse(queue.isEmpty());
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    @DisplayName("Queue size ")
    void size() throws QueueIsEmptyException {
        assertEquals(10, queue.size());
        queue.dequeue();
        assertEquals(9, queue.size());
    }

    @Test
    @DisplayName("Reverse of queue ")
    void reverse() throws QueueIsEmptyException {
        queue.reverse();
        assertEquals(4, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(7, queue.dequeue());
        assertEquals(5, queue.dequeue());
    }
}