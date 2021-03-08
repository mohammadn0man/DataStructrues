package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.InvalidPositionException;
import com.company.lineardatastructures.utils.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;


/*
    using synchronized to make thread-safe list
    Methods:
    1 insert
    2 insertAtPosition
    3 insertAtEnd
    4 delete
    5 deleteAtPosition
    6 deleteAtEnd
    7 center
    8 reverse
    9 size
    10 print
    11 iterator
*/

public class MyLinkedList<T> implements Iterable<T> {

    public static final String EMPTY_LIST = "List is empty.";
    private Node<T> head;

    public synchronized void insert(T val) {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public synchronized void insertAtPosition(T val, int pos) throws InvalidPositionException {
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

    public synchronized void insertAtEnd(T val) {
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

    public synchronized void delete() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else {
            head = head.next;
        }
    }

    public synchronized void deleteAtPosition(int pos) throws InvalidPositionException {
        int len = this.size();
        if (len < pos) {
            throw new InvalidPositionException("Position greater than size.");
        } else {
            Node<T> node = head;
            int i = 1;
            while (++i != pos) {
                node = node.next;
            }
            node.next = node.next.next;
        }
    }

    public synchronized void deleteAtEnd() throws EmptyListException {
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
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            mid = slow.data;
        }
        return mid;
    }

    public synchronized void reverse() throws EmptyListException {
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else if (head.next == null) {
            return;
        }
        Node<T> nextNode;
        Node<T> node = head;
        Node<T> prevNode = null;
        while (node != null) {
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

    public Node<T> getHead() {
        return head;
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }

}
