package com.company;

import com.company.customexceptions.EmptyListException;
import com.company.lineardatastructures.MyLinkedList;

public class MyLinkedListDemo {

    public static void main(String[] args) {
        try {
            MyLinkedList<Integer> ll = new MyLinkedList<>();
            ll.insert(6);
            ll.insert(5);
            ll.insert(4);
            ll.insert(3);
            ll.insert(2);
            ll.insert(1);
            ll.insert(0);
            ll.print();
            System.out.println("len : " + ll.size());
            System.out.println("mid ele : " + ll.center());
            ll.reverse();
            System.out.println("for each test");

            for (int integerNode : ll) {
                System.out.println(integerNode);
            }

            ll.deleteAtEnd();
        } catch (EmptyListException e) {
            System.err.println(e.getMessage());
        }
    }
}
