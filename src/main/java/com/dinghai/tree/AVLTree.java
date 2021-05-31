package com.dinghai.tree;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class AVLTree<K, V> {
    private Node root;
    private int size;
    private Comparator<? super K> comparator;

    public AVLTree() {
        comparator = null;
    }

    public AVLTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private int compare(Object k1, Object k2) {
        return comparator == null ? ((Comparable<? super K>) k1).compareTo((K) k2) : comparator.compare((K) k1, (K) k2);
    }

    public V put(K k, V v) {
        if (k == null)
            throw new InvalidParameterException("key can not be null");

        Node node = root;
        Node parent = null;

        V oldValue = null;
        while (node != null) {
            int diff = compare(node.key, k);
            parent = node;
            if (diff == 0) {
                oldValue = node.value;
                node.value = v;
                return oldValue;
            } else if (diff > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (parent == null) {
            root = new Node(k, v, null, 1);
            return null;
        }

        Node newNode = new Node(k, v, parent, 1);

        if (compare(parent.key, k) > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        rebalanced(newNode);
        return null;
    }

    private int factor(Node node) {
        if (node == null) return 0;
        int leftHeight = node.left == null ? 0 : node.left.height;
        int rightHeight = node.right == null ? 0 : node.right.height;
        return leftHeight - rightHeight;
    }

    private void rebalanced(Node node) {
        if (node == null) return;
        // adjust the height
        adjustHeight(node);

        // adjust the factor
        Node tmp = node.parent;
        while (tmp != null) {
            int factor = factor(tmp);
            //not balanced
            if (factor > 1) {
                Node l = tmp.left;
                int lf = factor(l);
                if (lf >= 0) {
                    tmp = rotateRight(tmp);
                } else {
                    rotateLeft(l);
                    tmp = rotateRight(tmp);
                }
            } else if (factor < -1) {
                Node r = tmp.right;
                int rf = factor(r);
                if (rf >= 0) {
                    rotateRight(r);
                    tmp = rotateLeft(tmp);
                } else {
                    tmp = rotateLeft(tmp);
                }
            }
            tmp = tmp.parent;
        }
    }

    public V get(K k) {
        if (k == null) return null;
        Node node = root;
        while (node != null) {
            int diff = compare(node.key, k);
            if (diff == 0) {
                return node.value;
            } else if (diff > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private void adjustHeight(Node node) {
        while (node != null) {
            int leftHeight = node.left == null ? 0 : node.left.height;
            int rightHeight = node.right == null ? 0 : node.right.height;
            node.height = Math.max(leftHeight, rightHeight) + 1;
            node = node.parent;
        }
    }

    private Node rotateLeft(Node node) {
        if (node == null || node.right == null)
            return null;
        Node parent = node.parent;
        Node right = node.right;
        Node rightLeft = right.left;
        node.parent = right;
        node.right = rightLeft;
        if (rightLeft != null) {
            rightLeft.parent = node;
        }
        right.left = node;
        right.parent = parent;

        adjustHeight(node);
        if (parent == null)
            root = right;
        return right;
    }



    private Node rotateRight(Node node) {
        if (node == null || node.left == null)
            return null;
        Node parent = node.parent;
        Node left = node.left;
        Node leftRight = left.right;
        node.parent = left;
        node.left = leftRight;
        if (leftRight != null) {
            leftRight.parent = node;
        }
        left.parent = parent;
        left.right = node;
        if (parent == null)
            root = left;
        return left;
    }

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right, parent;

        public Node(K key, V value, int height, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public Node(K key, V value, Node parent, int height) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.height = height;
        }

        public Node() {
        }
    }
}
