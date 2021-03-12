package com.company;

import com.company.customexceptions.EmptyListException;
import com.company.customexceptions.StackOverflowException;
import com.company.lineardatastructures.MyStack;

public class MyStackDemo {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        try {
            stack.push(1312);
            System.out.println(stack.peek());
            stack.push(1313);
            System.out.println(stack.peek());
            stack.push(1314);
            System.out.println(stack.peek());
            stack.push(1315);
            System.out.println(stack.peek());
            stack.push(1316);
            System.out.println(stack.peek());
            stack.push(1317);
            System.out.println(stack.peek());
            stack.push(1318);
            System.out.println(stack.peek());
            System.out.println(stack.contains(1312));
            System.out.println(stack.size());
            System.out.println(stack.pop());
            stack.reverse();
            for (Integer i : stack) {
                System.out.print(i + ", ");
            }

            stack.print();

        } catch (EmptyListException | StackOverflowException e) {
            System.err.println("Error : " + e.getMessage());
        }

    }
}
