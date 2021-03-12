package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.customexceptions.StackOverflowException;
import com.company.lineardatastructures.utils.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;

public class MyQueue<T> implements Iterable<T> {

    private Node<T> rear;
    private Node<T> front;
    private int maxValue = Integer.MAX_VALUE;
    private int size = 0;

    public MyQueue(int maxValue) {
        this.maxValue = maxValue;
    }

    public MyQueue() {
    }

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

    public T dequeue() throws QueueIsEmptyException {
        T val;
        if (front == null){
            throw new QueueIsEmptyException("There are no elements in the queue.");
        }
        val = front.data;
        front = front.next;
        size--;
        return val;
    }

    public T peek(){
        if (front == null){
            return null;
        }
        return front.data;
    }

    public boolean contains(T val){
        Node<T> node = front;
        while (node != null){
            if (node.data.equals(val)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void reverse() throws StackOverflowException, EmptyListException, QueueIsFullException, QueueIsEmptyException {
        MyStack<T> stack = new MyStack<>();
        while (this.isEmpty()){
            stack.push(this.dequeue());
        }
        while (stack.isEmpty()){
            this.enqueue(stack.pop());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(front);
    }

    public void print(){
        Node<T> node = front;
        while (node != null) {
            System.out.print(node.data + ", ");
            node = node.next;
        }
        System.out.println();
    }

    private boolean isFull() {
        return size() >= maxValue;
    }

    public boolean isEmpty(){
        return size() != 0;
    }

}
