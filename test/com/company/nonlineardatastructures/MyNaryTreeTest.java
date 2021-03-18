package com.company.nonlineardatastructures;

import com.company.customexceptions.EmptyTreeException;
import com.company.customexceptions.InvalidIndexException;
import com.company.nonlineardatastructures.nodes.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("N-Ary Tree test")
class MyNaryTreeTest {
    MyNaryTree<Integer> tree;

    @BeforeEach
    void init() throws InvalidIndexException {
        tree = new MyNaryTree<>();
        tree.add(new TreeNode<>(1));
        tree.add(new TreeNode<>(2));
        tree.add(new TreeNode<>(3));
        tree.add(new TreeNode<>(4));
        tree.getChildAt(0).add(new TreeNode<>(5));
        tree.getChildAt(0).add(new TreeNode<>(5));
        tree.getChildAt(0).add(new TreeNode<>(5));
        tree.getChildAt(1).add(new TreeNode<>(8));
        tree.getChildAt(2).add(new TreeNode<>(9));
        tree.getChildAt(2).add(new TreeNode<>(10));
        tree.getChildAt(2).add(new TreeNode<>(11));
    }

    @Test
    @DisplayName("Insert to tree")
    void add() throws EmptyTreeException {
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(11));
    }

    @Test
    @DisplayName("Remove from tree")
    void remove() throws InvalidIndexException, EmptyTreeException {
        tree.remove(1);
        assertFalse(tree.contains(1));
    }

    @Test
    @DisplayName("Contains element")
    void contains() throws EmptyTreeException {
        assertTrue(tree.contains(1));
        assertFalse(tree.contains(22));
    }

    @Test
    @DisplayName("retrieve list of elements by value")
    void getElementFromValue() throws EmptyTreeException, InvalidIndexException {
        List<TreeNode<Integer>> list = new LinkedList<>();
        list.add(tree.getRoot());
        assertIterableEquals(tree.getElementFromValue(1), list);
        List<TreeNode<Integer>> arrayList = tree.getChildAt(0).getChildren();
        assertIterableEquals(tree.getElementFromValue(5), arrayList);
    }

    @Test
    @DisplayName("Get child of node at index")
    void getChildAt() throws InvalidIndexException {
        TreeNode<Integer> node = tree.getChildAt(0);
        assertSame(tree.getRoot().getChildren().get(0), node);
        node = tree.getChildAt(0).getChildAt(1);
        assertSame(
                tree.getRoot().getChildren().get(0).getChildren().get(1),
                node
        );
    }
}