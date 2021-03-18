package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.StackOverflowException;
import com.company.customexceptions.StackUnderflowException;
import com.company.lineardatastructures.nodes.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;

/**
 * This Stack is the implementation some basic stack operations.
 * Internally it maintain a linkedlist to store data, top always
 * holds the reference to the node add the top (recently added).
 * <p>
 * It implements {@code Iterable} interface to make it iterable
 * and can be used with foreach.
 *
 * @param <T> the type of elements held in this collection
 */
public class MyStack<T> implements Iterable<T> {

    private Node<T> top;
    private int maxValue = Integer.MAX_VALUE;
    private int size = 0;

    /**
     * This constructor can be used when size is to be fixed of stack.
     *
     * @param maxValue the size of the stack
     */
    public MyStack(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Default constructor
     */
    public MyStack() {
    }

    /**
     * Push element at the top of stack
     *
     * @param val the element to be push
     * @throws StackOverflowException
     */
    public void push(T val) throws StackOverflowException {
        Node<T> newNode = new Node<>(val, null);
        if (isFull()) {
            throw new StackOverflowException("Stack Overflow");
        } else if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    /**
     * Remove top element from stack
     *
     * @return top element of stack
     * @throws EmptyListException
     */
    public T pop() throws EmptyListException {
        T val;
        if (top == null) {
            throw new StackUnderflowException("Underflow of Stack");
        }
        val = top.data;
        top = top.next;
        size--;
        return val;
    }

    /**
     * Retrieves, but does not remove, the top of stack.
     *
     * @return top element of stack
     */
    public T peek() {
        if (top == null) {
            return null;
        }
        return top.data;
    }

    /**
     * Check if give element exist in the list
     *
     * @param val the element to check
     * @return true if element is found, else false
     */
    public boolean contains(T val) {
        Node<T> node = top;
        while (node != null) {
            if (node.data.equals(val)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * @return size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Reverse the existing list
     *
     * @throws StackUnderflowException
     */
    public synchronized void reverse() throws StackUnderflowException {
        if (top == null) {
            throw new StackUnderflowException("Stack is Empty.");
        } else if (top.next == null) {
            return;
        }
        Node<T> nextNode;
        Node<T> node = top;
        Node<T> prevNode = null;
        while (node != null) {
            nextNode = node.next;
            node.next = prevNode;
            prevNode = node;
            node = nextNode;
        }
        top = prevNode;
    }

    /**
     * Make the stack compatible to use with foreach loop
     *
     * @return list {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(top);
    }

    /**
     * Print the list from head to tail
     */
    public void print() {
        Node<T> node = top;
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
    }

    /**
     * @return true if stack is full, else false
     */
    private boolean isFull() {
        return size() >= maxValue;
    }

    /**
     * @return true if stack is empty, else false
     */
    public boolean isEmpty() {
        return size() == 0;
    }
}
