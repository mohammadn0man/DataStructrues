package com.company.lineardatastructures.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityNodeIterator<T> implements Iterator<T> {
    private PriorityNode<T> current;

    public PriorityNodeIterator(PriorityNode<T> head) {
        current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("Empty List");
        }
        T data = current.data;
        current = current.next;
        return data;
    }
}