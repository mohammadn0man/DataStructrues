package com.company.lineardatastructures;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.InvalidPositionException;
import com.company.lineardatastructures.nodes.Node;
import com.company.lineardatastructures.utils.NodeIterator;

import java.util.Iterator;


/**
 * This LinkedList is the implementation of the {@code Iterable} and {@code MyQueueInterface}
 * interfaces. Implements all the methods of {@code MyQueueInterface} and necessory methods
 * of {@code Iterable} to make it {@code foreach} loop compatible.
 * <p>It has implemented methods below:
 * <p>{@code insert()}
 * <p>{@code insertAtPosition()}
 * <p>{@code insertAtEnd()}
 * <p>{@code delete()}
 * <p>{@code deleteAtPosition()}
 * <p>{@code deleteAtEnd()}
 * <p>{@code center()}
 * <p>{@code reverse()}
 * <p>{@code size()}
 * <p>{@code print()}
 * <p>{@code iterator()}
 *
 * @param <T> the type of elements held in this collection
 * @author Mohammad Noman
 */

public class MyLinkedList<T> implements Iterable<T>, MyQueueInterface<T> {

    public static final String EMPTY_LIST = "List is empty.";
    private Node<T> head;
    private int size = 0;

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param val the element to add
     */
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

    /**
     * Inserts the specified element at the given position of this list.
     *
     * @param val the element to add
     * @param pos the position at which it will be added
     * @throws InvalidPositionException
     */
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

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param val the element to add
     */
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

    /**
     * Deletes the element from the start of this list.
     *
     * @return the deleted element
     * @throws EmptyListException
     */
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

    /**
     * Deletes the element from the given position of this list.
     *
     * @param pos the position of element you want to delete
     * @throws InvalidPositionException
     * @throws EmptyListException
     */
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

    /**
     * Deletes the element from the end of this list.
     *
     * @throws EmptyListException
     */
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

    /**
     * Find center of the list
     *
     * @return the element at the center of the list
     * @throws EmptyListException
     */
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

    /**
     * Retrieve the {@code Node<T>} at the given position.
     * Used mostly in testing class.
     *
     * @param index the position of element
     * @return the element at given index
     * @throws EmptyListException
     * @throws InvalidPositionException
     */
    public Node<T> getNodeAt(int index) throws EmptyListException, InvalidPositionException {
        if (head == null) {
            throw new EmptyListException(EMPTY_LIST);
        }
        if (index > size) {
            throw new InvalidPositionException("index value more than size of list.");
        }
        Node<T> node = head;
        while (index-- > 0) {
            node = node.next;
        }
        return node;
    }

    /**
     * Reverse the existing list
     *
     * @throws EmptyListException
     */
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

    /**
     * Adds the specified element as the tail (last element) of this list.
     *
     * @param val the element to insert
     */
    @Override
    public void enqueue(T val) {
        insertAtEnd(val);
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     * @throws EmptyListException
     */
    @Override
    public T dequeue() throws EmptyListException {
        return delete();
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list, or {@code null} if this list is empty
     */
    @Override
    public T peek() {
        final Node<T> node = head;
        return (node == null) ? null : node.data;
    }

    /**
     * Check if give element exist in the list
     *
     * @param val the element to check
     * @return true if element is found, else false
     */
    @Override
    public boolean contains(T val) {
        Node<T> node = head;
        while (node != null) {
            if (node.data.equals(val)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * @return size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Print the list from head to tail
     */
    public void print() {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    /**
     * @return head of the list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Make the list compatible to use with foreach loop
     *
     * @return list {@code Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(head);
    }

}
