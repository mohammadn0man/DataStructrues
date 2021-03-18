package com.company.lineardatastructures;

import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.lineardatastructures.nodes.PriorityNode;
import com.company.lineardatastructures.utils.PriorityNodeIterator;

import java.util.Iterator;

public class MyPriorityQueue<T> implements Iterable<T> {

    private PriorityNode<T> head;
    private int maxValue = Integer.MAX_VALUE;
    private int defaultPriority = 5;
    private int size = 0;

    public MyPriorityQueue(int maxValue, int defaultPriority) {
        this.maxValue = maxValue;
        this.defaultPriority = defaultPriority;
    }

    public MyPriorityQueue(int maxValue) {
        this.maxValue = maxValue;
    }

    public MyPriorityQueue() {
    }

    public void enqueue(T val) throws QueueIsFullException {
        this.enqueue(val, defaultPriority);
    }

    public void enqueue(T val, int priority) throws QueueIsFullException {
        PriorityNode<T> node = new PriorityNode<>(val, priority, null);
        this.enqueue(node);
    }

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

    public T dequeue() throws QueueIsEmptyException {
        if (head == null) {
            throw new QueueIsEmptyException("Priority List is Empty");
        }
        PriorityNode<T> deletedNode = head;
        head = head.getNext();
        size--;
        return deletedNode.getData();
    }

    public T peek() throws QueueIsEmptyException {
        if (head == null) {
            throw new QueueIsEmptyException("Priority List is Empty");
        }
        return head.getData();
    }

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

    public void print() {
        PriorityNode<T> node = this.head;
        while (node != null) {
            System.out.print("(" + node.data + ", " + node.priority + "), ");
            node = node.next;
        }
        System.out.println();
    }

    private boolean isFull() {
        return size() >= maxValue;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public int size() {
        return size;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new PriorityNodeIterator<>(head);
    }
}
