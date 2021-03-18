package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.lineardatastructures.nodes.PriorityNode;
import com.company.lineardatastructures.utils.PriorityNodeIterator;

import java.util.Iterator;

/**
 * This is the simple implementation of priority queue with basic operaitions
 * Internally it holds data in the form of Linked-List.
 * While inserting it arrange the elements according to their priority.
 *
 * @param <T> the type of elements held in this collection
 * @author mohammadnoman
 */
public class MyPriorityQueue<T> implements Iterable<T> {

    private PriorityNode<T> head;
    private int maxValue = Integer.MAX_VALUE;
    private int defaultPriority = 5;
    private int size = 0;

    /**
     * Constructor to call when max capacity and dafault priority is to be
     * specify.
     *
     * @param maxValue        max size of the queue
     * @param defaultPriority priority value to be used when nothing is provided
     */
    public MyPriorityQueue(int maxValue, int defaultPriority) {
        this.maxValue = maxValue;
        this.defaultPriority = defaultPriority;
    }

    /**
     * Constructor to call when max capacity is to be specify.
     *
     * @param maxValue max size of the queue
     */
    public MyPriorityQueue(int maxValue) {
        this.maxValue = maxValue;
    }


    /**
     * Default constructor
     */
    public MyPriorityQueue() {
    }

    /**
     * Adds the element to its desired position according to default priority
     *
     * @param val the element to be inserted
     * @throws QueueIsFullException
     */
    public void enqueue(T val) throws QueueIsFullException {
        this.enqueue(val, defaultPriority);
    }

    /**
     * Adds the element to its desired position according to given priority
     *
     * @param val      the element to be inserted
     * @param priority specify priority
     * @throws QueueIsFullException
     */
    public void enqueue(T val, int priority) throws QueueIsFullException {
        PriorityNode<T> node = new PriorityNode<>(val, priority, null);
        this.enqueue(node);
    }

    /**
     * Adds the node to its desired position according to given priority
     *
     * @param newNode Node to be added
     * @throws QueueIsFullException
     */
    public void enqueue(PriorityNode<T> newNode) throws QueueIsFullException {
        if (isFull()) {
            throw new QueueIsFullException("Priority Queue is Full.");
        }
        if (head == null)
            head = newNode;
        else {
            PriorityNode<T> currentPtr = head;
            while (currentPtr.getNext() != null && currentPtr.getNext().getPriority() <= newNode.getPriority()) {
                currentPtr = currentPtr.getNext();
            }
            newNode.setNext(currentPtr.getNext());
            currentPtr.setNext(newNode);
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
        if (head == null) {
            throw new QueueIsEmptyException("Priority List is Empty");
        }
        PriorityNode<T> deletedNode = head;
        head = head.getNext();
        size--;
        return deletedNode.getData();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     */
    public T peek() throws QueueIsEmptyException {
        if (head == null) {
            throw new QueueIsEmptyException("Priority List is Empty");
        }
        return head.getData();
    }

    /**
     * Check if give element exist in the list
     *
     * @param val the element to check
     * @return true if element is found, else false
     */
    public boolean contains(T val) {
        PriorityNode<T> node = head;
        while (node != null) {
            if (node.data.equals(val)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }


    /**
     * Print the list from head to tail
     */
    public void print() {
        PriorityNode<T> node = this.head;
        while (node != null) {
            System.out.print("(" + node.data + ", " + node.priority + "), ");
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
        return size() <= 0;
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
    public void reverse() throws QueueIsEmptyException {
        if (head == null) {
            throw new QueueIsEmptyException("Queue is Empty.");
        } else if (head.next == null) {
            return;
        }
        PriorityNode<T> nextNode;
        PriorityNode<T> node = head;
        PriorityNode<T> prevNode = null;
        while (node != null) {
            nextNode = node.next;
            node.next = prevNode;
            prevNode = node;
            node = nextNode;
        }
        head = prevNode;
    }

    /**
     * Make the list compatible to use with foreach loop
     *
     * @return list {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new PriorityNodeIterator<>(head);
    }
}
