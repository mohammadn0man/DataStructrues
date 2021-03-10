package com.company.lineardatastructures.utils;

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
}
