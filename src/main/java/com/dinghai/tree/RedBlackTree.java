package com.dinghai.tree;

import java.util.Comparator;

public class RedBlackTree<K, V> {
    private final Comparator<? super K> comparator;
    private Node root;

    public RedBlackTree() {
        this.comparator = null;
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public V put(K k, V v) {
        if (root == null) {
            root = new Node(k, v, null);
            root.color = Color.BLACK;
            return null;
        }

        Node parent, cur = root;
        int diff = 0;

        do {
            parent = cur;
            diff = compare(k, cur.k);
            if (diff > 0) {
                cur = cur.right;
            } else if (diff < 0) {
                cur = cur.left;
            } else {
                return cur.updateValue(v);
            }
        } while (cur != null);

        Node newNode = new Node(k, v, parent);

        if (diff > 0)
            parent.right = newNode;
        else
            parent.left = newNode;

        fixAfterInsertion(newNode);
        return null;
    }

    private void fixAfterInsertion(Node node) {
        node.color = Color.RED;
    }
    private void fixAfterDeletion(Node node) {

    }



    private void rotateLeft(Node node) {

    }

    private void rotateRight(Node node) {

    }


    public V get(K k) {
        Node node = root;
        int diff = 0;
        while (node != null) {
            diff = compare(k, node.k);
            if (diff > 0) {
                node = node.right;
            } else if (diff < 0) {
                node = node.left;
            } else {
                return node.v;
            }
        }

        return null;
    }

    public V remove(K k) {
        //TODO
        return null;
    }

    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2) {
        return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2)
                : comparator.compare((K) k1, (K) k2);
    }


    private enum Color {
        RED,
        BLACK
    }

    private class Node {
        Color color;
        K k;
        V v;
        Node left;
        Node right;
        Node parent;

        Node(K k, V v, Node parent) {
            this.k = k;
            this.v = v;
            this.parent = parent;
        }

        Node(K k, V v) {
            this(k, v, null);
        }

        V updateValue(V v) {
            V tmp = this.v;
            this.v = v;
            return tmp;
        }
    }
}
