package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.customexceptions.StackOverflowException;

public interface MyNewQueue<T> {

    void enqueue(T val) throws QueueIsFullException;

    T dequeue() throws QueueIsEmptyException, EmptyListException;

    T peek();

    boolean contains(T val);

    int size();

    void reverse() throws StackOverflowException, EmptyListException, QueueIsFullException, QueueIsEmptyException;
}
