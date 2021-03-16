package com.company.lineardatastructures.nodes;

import com.company.customexceptions.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;

    public TreeNode() {
        children = new ArrayList<>();
    }

    public TreeNode(T data) {
        this();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public void add(TreeNode<T> node) {
        children.add(node);
    }


    public TreeNode<T> getChildAt(int index) throws InvalidIndexException {
        if (index < children.size() && index > -1)
            return children.get(index);
        else
            throw new InvalidIndexException("Index out of Bound : \nSize : " + children.size() + "\nIndex : " + index);
    }

    public int getNoOfChildren() {
        return children.size();
    }

    public boolean hasChildren() {
        return getNoOfChildren() > 0;
    }

}
