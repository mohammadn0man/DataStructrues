package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.StackOverflowException;
import com.company.customexceptions.StackUnderflowException;
import com.company.lineardatastructures.utils.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {

    private Node<T> top;
    private int maxValue = Integer.MAX_VALUE;

    public MyStack(int maxValue) {
        this.maxValue = maxValue;
    }

    public MyStack() {
    }

    public void push(T val) throws StackOverflowException {
        Node<T> newNode = new Node<>(val, null);
        if (top == null) {
            top = newNode;
        } else if (isFull()) {
            newNode.next = top;
            top = newNode;
        } else {
            throw new StackOverflowException("Stackoverflow ");
        }
    }

    public T pop() throws EmptyListException {
        T val;
        if (top == null) {
            throw new StackUnderflowException("Underflow of Stack");
        }
        val = top.data;
        top = top.next;
        return val;
    }

    public T peek() {
        return top.data;
    }

    public boolean contains(T val){
        Node<T> node = top;
        while (node != null){
            if (node.data.equals(val)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public int size() {
        int len = 0;
        Node<T> node = top;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
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
            System.out.println(node.data);
            node = node.next;
        }
    }

    private boolean isFull() {
        return size() != maxValue;
    }

}
