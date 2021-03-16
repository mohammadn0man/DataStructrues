package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.StackOverflowException;
import com.company.customexceptions.StackUnderflowException;
import com.company.lineardatastructures.nodes.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {

    private Node<T> top;
    private int maxValue = Integer.MAX_VALUE;
    private int size = 0;

    public MyStack(int maxValue) {
        this.maxValue = maxValue;
    }

    public MyStack() {
    }

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

    public T peek() {
        if (top == null) {
            return null;
        }
        return top.data;
    }

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

    public int size() {
        return size;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(top);
    }

    public void print() {
        Node<T> node = top;
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
    }

    private boolean isFull() {
        return size() >= maxValue;
    }

    public boolean isEmpty() {
        return size() != 0;
    }
}
