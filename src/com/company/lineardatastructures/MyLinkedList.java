package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.InvalidPositionException;
import com.company.lineardatastructures.nodes.Node;
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

public class MyLinkedList<T> implements Iterable<T>, MyQueueInterface<T> {

    public static final String EMPTY_LIST = "List is empty.";
    private Node<T> head;
    private int size = 0;

    public void insert(T val) {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void insertAtPosition(T val, int pos) throws InvalidPositionException {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
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
        size++;
    }

    public void insertAtEnd(T val) {
        Node<T> newNode = new Node<>(val, null);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
        size++;
    }

    public T delete() throws EmptyListException {
        T data;
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        } else {
            data = head.data;
            head = head.next;
        }
        size--;
        return data;
    }

    public void deleteAtPosition(int pos) throws InvalidPositionException, EmptyListException {
        if (head == null) {
            throw new EmptyListException("No element to delete");
        }
        int len = this.size();
        if (len < pos) {
            throw new InvalidPositionException("Position greater than size.");
        } else if (pos == 0) {
            head = head.next;
        } else {
            Node<T> node = head;
            int i = 1;
            while (++i != pos) {
                node = node.next;
            }
            node.next = node.next.next;
        }
        size--;
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
        size--;
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

    public Node<T> getNodeAt(int index) throws EmptyListException, InvalidPositionException {
        if (head == null){
            throw new EmptyListException(EMPTY_LIST);
        }
        if (index > size){
            throw new InvalidPositionException("index value more than size of list.");
        }
        Node<T> node = head;
        while (index-- > 0){
            node = node.next;
        }
        return node;
    }

    @Override
    public void reverse() throws EmptyListException {
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

    @Override
    public void enqueue(T val) {
        insertAtEnd(val);
    }

    @Override
    public T dequeue() throws EmptyListException {
        return delete();
    }

    @Override
    public T peek() {
        final Node<T> node = head;
        return (node == null) ? null : node.data;
    }

    @Override
    public boolean contains(T val) {
        Node<T> node = head;
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
