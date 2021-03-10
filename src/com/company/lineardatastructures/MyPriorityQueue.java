package com.company.lineardatastructures;

import com.company.customexceptions.InvalidPositionException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.lineardatastructures.utils.Node;
import com.company.lineardatastructures.utils.PriorityNode;
import com.company.lineardatastructures.utils.PriorityNodeIterator;

import java.util.Iterator;

public class MyPriorityQueue<T> implements Iterable<T> {

    private PriorityNode<T> head;
    private int maxValue = Integer.MAX_VALUE;
    private int defaultPriority = 5;

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
        if (isFull()) {
            throw new QueueIsFullException("Priority Queue is Full.");
        }
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            this.head = node;
        }
    }

    public T dequeue() throws QueueIsEmptyException, InvalidPositionException {
        if (head == null) {
            throw new QueueIsEmptyException("Priority Queue is empty");
        }
        PriorityNode<T> node = head;
        T val = head.data;
        int index = 0;
        int i = 0;
        int maxPriority = head.priority;
        while (node != null) {
            if (node.priority > maxPriority) {
                maxPriority = node.priority;
                val = node.data;
                index = i;
            }
            node = node.next;
            i++;
        }
        this.deleteAtPosition(index);
        return val;
    }

    public void deleteAtPosition(int pos) throws InvalidPositionException {
        int len = this.size();
        if (len < pos) {
            throw new InvalidPositionException("Position greater than size.");
        } else if (pos == 0) {
            head = head.next;
        } else {
            PriorityNode<T> node = head;
            int i = 0;
            while (++i < pos) {
                node = node.next;
            }
            node.next = node.next.next;
        }
    }

    public T peek() throws QueueIsEmptyException {
        if (head == null) {
            throw new QueueIsEmptyException("Priority Queue is empty");
        }
        PriorityNode<T> node = head;
        T val = head.data;
        int maxPriority = head.priority;
        while (node != null) {
            if (node.priority > maxPriority) {
                maxPriority = node.priority;
                val = node.data;
            }
            node = node.next;
        }
        return val;
    }

    public boolean contains(T val){
        Node<T> node = head;
        while (node != null){
            if (node.data.equals(val)){
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

    private int size() {
        int len = 0;
        PriorityNode<T> node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
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
