package com.company.lineardatastructures;

import com.company.lineardatastructures.customexceptions.EmptyListException;
import com.company.lineardatastructures.customexceptions.InvalidPositionException;
import com.company.lineardatastructures.utils.Node;

public class MyLinkedList<T> {

    public static final String EMPTY_LIST = "List is empty.";
    private Node<T> head;

    public void insert(T val) {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtPosition(T val, int pos) throws InvalidPositionException {
        Node<T> newNode = new Node<>(val, null);
        Node<T> node = head;
        int len = this.size();
        if (len < pos) {
            throw new InvalidPositionException("Position greater than size.");
        } else {
            int i = 1;
            while (++i != pos) {
                node = node.next;
            }
            Node<T> temp = node.next;
            node.next = newNode;
            newNode.next = temp;
        }
    }

    public void insertAtEnd(T val) {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
        }
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }

    public void delete() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else {
            head = head.next;
        }
    }

    public void deleteAtPosition(int pos) {
        int len = this.size();
        if (len < pos) {
            try {
                throw new InvalidPositionException("Position greater than size.");
            } catch (InvalidPositionException e) {
                System.err.println(e.getMessage());
            }
        } else {
            Node<T> node = head;
            int i = 1;
            while (++i != pos) {
                node = node.next;
            }
            node.next = node.next.next;
        }
    }

    public void deleteAtEnd() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else if (head.next == null) {
            head = null;
        } else {
            Node<T> node = head;
            Node<T> temp = node;
            while (node.next != null) {
                temp = node;
                node = node.next;
            }
            temp.next = null;
        }
    }

    public T center() throws EmptyListException {
        T mid;
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else {
           Node<T> slow;
           Node<T> fast;
           slow = fast = head;
           while (fast.next != null && fast.next.next != null){
               fast = fast.next.next;
               slow = slow.next;
           }
           mid = slow.data;
        }
        return mid;
    }

    public void reverse() throws EmptyListException {
        if (head == null){
            throw new EmptyListException(EMPTY_LIST);
        } else if (head.next == null){
            return;
        }
        Node<T> nextNode;
        Node<T> node = head;
        Node<T> prevNode = null;
        while (node != null){
            nextNode = node.next;
            node.next = prevNode;
            prevNode = node;
            node = nextNode;
        }
        head = prevNode;
    }

    public int size() {
        int len = 0;
        Node<T> node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    public void print() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }
}
