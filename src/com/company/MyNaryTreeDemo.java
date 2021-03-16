package com.company;

import com.company.customexceptions.EmptyTreeException;
import com.company.customexceptions.InvalidIndexException;
import com.company.lineardatastructures.MyNaryTree;
import com.company.lineardatastructures.nodes.TreeNode;

public class MyNaryTreeDemo {
    public static void main(String[] args) {

        try {
            MyNaryTree<Integer> tree = new MyNaryTree<>();
            tree.add(new TreeNode<>(1));
            tree.add(new TreeNode<>(2));
            tree.add(new TreeNode<>(3));
            tree.add(new TreeNode<>(4));
            tree.getChildAt(0).add(new TreeNode<>(5));
            tree.getChildAt(0).add(new TreeNode<>(6));
            tree.getChildAt(0).add(new TreeNode<>(7));
            tree.getChildAt(1).add(new TreeNode<>(8));
            tree.getChildAt(2).add(new TreeNode<>(9));
            tree.getChildAt(2).add(new TreeNode<>(10));
            tree.getChildAt(2).add(new TreeNode<>(11));
            tree.printBFS();
            tree.remove(11);
            tree.printBFS();
            System.out.println("---------------");
            tree.getElementsByLevel(3);
            for (int i : tree) {
                System.out.print(i + " - ");
            }
            System.out.println();
            tree.printDFS();
        } catch (InvalidIndexException | EmptyTreeException e) {
            System.err.println(e.getMessage());
        }
    }
}
