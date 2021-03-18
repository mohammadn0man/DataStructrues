package com.company.nonlineardatastructures;

import com.company.customexceptions.EmptyTreeException;
import com.company.customexceptions.InvalidIndexException;
import com.company.nonlineardatastructures.nodes.TreeNode;
import com.company.nonlineardatastructures.utils.NaryTreeIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyNaryTree<T> implements Iterable<T> {
    private TreeNode<T> root;

    public MyNaryTree() {
    }

    public MyNaryTree(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void add(TreeNode<T> node) {
        if (root == null) {
            root = node;
        } else {
            root.getChildren().add(node);
        }
    }

    public void remove(T val) throws InvalidIndexException, EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Populate tree before remove.");
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                if (node.getData() == val && node.hasChildren()) {
                    node.setData(node.getChildAt(0).getData());
                    node.getChildren().remove(0);
                    return;
                }
                for (int j = 0; j < node.getNoOfChildren(); j++) {
                    if (node.getChildAt(j).getData() == val && !node.getChildAt(j).hasChildren()) {
                        node.getChildren().remove(j);
                        return;
                    }
                    queue.offer(node.getChildAt(j));
                }
            }
        }
    }

    public boolean contains(T value) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Tree is empty.");
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                if (node.getData() == value) {
                    return true;
                }
                node.getChildren().forEach(queue::offer);
            }
        }
        return false;
    }

    public void printBFS() throws EmptyTreeException {
        printBFSFrom(root);
        System.out.println();
    }

    public void printBFSFrom(TreeNode<T> root) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Given root is empty.");
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                System.out.print(node.getData() + " ");
                node.getChildren().forEach(queue::offer);
            }
        }
    }

    public void printDFS() throws InvalidIndexException, EmptyTreeException {
        printDFSFrom(root);
        System.out.println();
    }

    private void printDFSFrom(TreeNode<T> root) throws InvalidIndexException, EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Given root is empty.");
        }
        int total = root.getChildren().size();
        for (int i = 0; i < total - 1; i++) {
            printDFSFrom(root.getChildAt(i));
        }
        System.out.print(root.getData() + " ");
        if (root.hasChildren()) {
            printDFSFrom(root.getChildAt(total - 1));
        }
    }

    public void getElementsByLevel(int level) throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("Tree is empty.");
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            level--;
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                if (level == 0) {
                    System.out.print(node.getData() + " ");
                }
                node.getChildren().forEach(queue::offer);
            }
        }
        System.out.println();
    }

    public List<TreeNode<T>> getElementFromValue(TreeNode<T> root, T value) throws EmptyTreeException {
        if (root == null){
            throw new EmptyTreeException("Given root is empty.");
        }
        List<TreeNode<T>> listToReturn = new LinkedList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                if (node.getData() == value) {
                    listToReturn.add(node);
                }
                node.getChildren().forEach(queue::offer);
            }
        }
        return listToReturn;
    }

    public TreeNode<T> getChildAt(int index) throws InvalidIndexException {
        if (root.getChildren().size() > index)
            return root.getChildren().get(index);
        else
            throw new InvalidIndexException("Index out of Bound : \nSize : " + root.getChildren().size() + "\nIndex : " + index);
    }

    @Override
    public Iterator<T> iterator() {
        return new NaryTreeIterator<>(root);
    }
}
