package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.customexceptions.StackOverflowException;
import com.company.lineardatastructures.nodes.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;

/**
 * This Queue is the implementation some basic queue operations.
 * Internally it maintain a linkedlist to store data, front and
 * rear pointers are used to point at the push and pop location
 * of the list.
 * <p>
 * It implements {@code Iterable} interface to make it iterable
 * and can be used with foreach.
 *
 * @author mohammadnoman
 * @param <T> the type of elements held in this collection
 */
public class MyQueue<T> implements Iterable<T> {

    private Node<T> rear;
    private Node<T> front;
    private int maxValue = Integer.MAX_VALUE;
    private int size = 0;

    /**
     * This constructor can be used when size is to be fixed of queue.
     *
     * @param maxValue the size of the stack
     */
    public MyQueue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Default constructor
     */
    public MyQueue() {
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param val the element to insert
     */
    public void enqueue(T val) throws QueueIsFullException {
        Node<T> node = new Node<>(val, null);
        if (isFull()) {
            throw new QueueIsFullException("Max capacity of queue is reached.");
        }
        if (front == null) {
            rear = node;
            front = rear;
        } else {
            rear.next = node;
            rear = rear.next;
        }
        size++;
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     * @throws QueueIsEmptyException
     */
    public T dequeue() throws QueueIsEmptyException {
        T val;
        if (front == null) {
            throw new QueueIsEmptyException("There are no elements in the queue.");
        }
        val = front.data;
        front = front.next;
        size--;
        return val;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     */
    public T peek() {
        return front == null ? null : front.data;
    }

    /**
     * Check if give element exist in the list
     *
     * @param val the element to check
     * @return true if element is found, else false
     */
    public boolean contains(T val) {
        Node<T> node = front;
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
     * @throws EmptyListException
     */
    public void reverse() throws StackOverflowException, EmptyListException, QueueIsFullException, QueueIsEmptyException {
        MyStack<T> stack = new MyStack<>();
        while (this.isEmpty()) {
            stack.push(this.dequeue());
        }
        while (!stack.isEmpty()) {
            this.enqueue(stack.pop());
        }
    }

    /**
     * Make the list compatible to use with foreach loop
     *
     * @return list {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(front);
    }

    /**
     * Print the list from head to tail
     */
    public void print() {
        Node<T> node = front;
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
        System.out.println();
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
        return size() != 0;
    }

}
