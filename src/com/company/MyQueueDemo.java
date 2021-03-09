package com.company;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.customexceptions.StackOverflowException;
import com.company.lineardatastructures.MyQueue;

public class MyQueueDemo {
    public static void main(String[] args) {
        try {
            MyQueue<Integer> q = new MyQueue<>();
            q.peek();
            q.enqueue(989);
            q.dequeue();
            q.enqueue(1);
            q.enqueue(2);
            q.enqueue(3);
            q.enqueue(4);
            q.enqueue(5);
            q.enqueue(6);
            q.enqueue(7);
            q.enqueue(8);
            q.dequeue();
            q.dequeue();
            System.out.println(q.contains(2));
            q.dequeue();
            q.dequeue();
            q.enqueue(23);
            for (int i : q) {
                System.out.print(i + " ");
            }
            System.out.println();
            q.reverse();
            q.print();
        } catch (QueueIsEmptyException | QueueIsFullException | EmptyListException | StackOverflowException e) {
            System.err.println(e.getMessage());
        }
    }
}
