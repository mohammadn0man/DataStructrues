package com.company.lineardatastructures.nodes;

public class PriorityNode<T> extends Node<T>{
    public int priority;
    public PriorityNode<T> next;

    public PriorityNode(T data, int priority, PriorityNode<T> next) {
        super(data);
        this.next = next;
        this.priority = priority;
    }

    public PriorityNode(T data, PriorityNode<T> next) {
        super(data);
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public PriorityNode<T> getNext() {
        return next;
    }

    public void setNext(PriorityNode<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }
}
