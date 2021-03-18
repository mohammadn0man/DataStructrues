package com.company.nonlineardatastructures.utils;

import com.company.nonlineardatastructures.nodes.TreeNode;

import java.util.*;

public class NaryTreeIterator<T> implements Iterator<T> {
    private List<T> list;

    public NaryTreeIterator(TreeNode<T> root) {
        list = new ArrayList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode<T> node = queue.poll();
                assert node != null;
                list.add(node.getData());
                node.getChildren().forEach(queue::offer);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public T next() {
        if (list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.remove(0);
    }
}
