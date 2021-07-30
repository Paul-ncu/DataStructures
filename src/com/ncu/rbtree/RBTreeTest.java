package com.ncu.rbtree;

public class RBTreeTest {
    public static void main(String[] args) {
        RBTree<Integer, String> tree = new RBTree<>();
        tree.insert(2, "world");
        tree.insert(1, "world");
        tree.insert(3, "world");
        tree.insert(4, "world");
        tree.insert(5, "world");
        tree.inOrderPrint();
    }
}
