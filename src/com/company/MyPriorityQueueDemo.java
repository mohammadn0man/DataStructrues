package com.company;

import com.company.customexceptions.InvalidPositionException;
import com.company.customexceptions.QueueIsEmptyException;
import com.company.customexceptions.QueueIsFullException;
import com.company.lineardatastructures.MyPriorityQueue;

public class MyPriorityQueueDemo {
    public static void main(String[] args) {
        try {
            MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
            q.enqueue(989, 1);
            q.enqueue(1, 3);
            q.enqueue(2, 4);
            q.enqueue(3);
            q.enqueue(4, 100);
            q.enqueue(5, 23);
            q.enqueue(6, 32);
            q.enqueue(7, 23);
            q.enqueue(8);
            q.enqueue(23);
            for (int i : q) {
                System.out.print(i + " ");
            }
            System.out.println("size : " + q.size());
            q.print();
            System.out.println("contains : " + q.contains(23));
            System.out.println( q.dequeue());
            System.out.println( q.dequeue());
            System.out.println( q.dequeue());
            System.out.println( q.dequeue());
            System.out.println( q.dequeue());
            System.out.println( q.dequeue());
            System.out.println("size : " + q.size());
            System.out.println("peek : " + q.peek());
            q.reverse();
            q.print();
        } catch (QueueIsFullException | QueueIsEmptyException | InvalidPositionException e) {
            System.err.println(e.getMessage());
        }
    }
}
