package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.StackOverflowException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    public static MyStack<Integer> stack;

    @BeforeAll
    static void init() throws StackOverflowException {
        stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }

    @Test
    void push() {
        assertEquals(4, stack.peek());
    }

    @Test
    void pop() throws EmptyListException {
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void peek() throws EmptyListException {
        assertEquals(4, stack.peek());
        stack.pop();
        assertEquals(3, stack.peek());
    }

    @Test
    void contains() {
        assertTrue(stack.contains(1));
        assertFalse(stack.contains(5));
    }

    @Test
    void size() throws EmptyListException {
        assertEquals(4, stack.size());
        stack.pop();
        assertEquals(3, stack.size());
    }

    @Test
    void reverse() throws EmptyListException {
        stack.reverse();
        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
    }

    @Test
    void isEmpty() throws EmptyListException {
        assertTrue(stack.isEmpty());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        assertFalse(stack.isEmpty());
    }
}