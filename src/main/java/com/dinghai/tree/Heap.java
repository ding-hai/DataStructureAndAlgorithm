package com.dinghai.tree;

import java.util.Comparator;

/**
 * 数组下标从1开始
 *
 * @param <T>
 */
public class Heap<T> {
    private final static int DEFAULT_CAPACITY = 16;
    private final Comparator<T> DEFAULT_COMPARATOR = new Comparator<T>() {
        @Override
        @SuppressWarnings("unchecked")
        public int compare(T o1, T o2) {
            if (o1 instanceof Comparable) {
                Comparable<T> c1 = (Comparable<T>) o1;
                return c1.compareTo(o2);
            }
            return 0;
        }
    };
    private Comparator<T> comparator = DEFAULT_COMPARATOR;
    private T[] nodes;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<T> comparator) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        nodes = (T[]) new Object[this.capacity + 1];
        if (comparator != null) {
            this.comparator = comparator;
        }
    }

    public Heap() {
        this(0, null);
    }

    @SuppressWarnings("unchecked")
    public void push(T val) {
        if (size >= capacity) {
            //扩容
            int newCapacity = capacity * 2;
            T[] newNodes = (T[]) new Object[newCapacity + 1];
            System.arraycopy(nodes, 1, newNodes, 1, capacity);
            nodes = newNodes;
            capacity = newCapacity;
        }

        nodes[++size] = val;
        //调整
        int i = size;
        int p = i / 2;
        while (p > 0) {
            if (comparator.compare(nodes[i], nodes[p]) < 0) {
                swap(i, p);
            }
            i = p;
            p = i / 2;
        }
    }

    public T top() {
        if (size <= 0) return null;
        return nodes[1];
    }

    public T pop() {
        if (size <= 0) return null;
        T tmp = nodes[1];
        nodes[1] = nodes[size];
        nodes[size] = null;
        int p = 1;
        int left = p * 2;
        int right = left + 1;
        while (left < size) {
            int target = left;
            if (right <= size && nodes[right] != null && comparator.compare(nodes[left], nodes[right]) > 0) {
                target = right;
            }
            if (comparator.compare(nodes[p], nodes[target]) > 0) {
                swap(p, target);
                p = target;
            } else {
                break;
            }
            left = p * 2;
            right = left + 1;
        }
        size--;
        return tmp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        T tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }
}
