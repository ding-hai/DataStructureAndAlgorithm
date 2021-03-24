package com.dinghai.list;

import java.util.Random;

public class Skiplist {

    private static final int MAX_LEVEL = 128;

    private final Node head;
    private final Random random;
    private int level = 0;

    private static class Node {
        public int value;
        public Node[] nexts;
    }

    public Skiplist() {
        head = new Node();
        head.nexts = new Node[MAX_LEVEL + 1];
        random = new Random();
    }

    public boolean search(int target) {
        Node tmp = head;
        for (int i = level; i >= 0; i--) {
            while (tmp.nexts[i] != null && tmp.nexts[i].value < target)
                tmp = tmp.nexts[i];
        }
        return tmp.nexts[0] != null && tmp.nexts[0].value == target;
    }

    private Node[] findPreviousNodes(int target) {
        return findPreviousNodes(target, null);
    }

    private Node[] findPreviousNodes(int target, boolean[] found) {
        Node[] previousNodes = new Node[level + 1];
        Node tmp = head;
        for (int i = level; i >= 0; i--) {
            while (tmp.nexts[i] != null && tmp.nexts[i].value < target)
                tmp = tmp.nexts[i];
            previousNodes[i] = tmp;
        }
        if (found != null && found.length > 0)
            found[0] = tmp.nexts[0] != null && tmp.nexts[0].value == target;
        return previousNodes;
    }


    public void add(int num) {
        Node[] previousNodes = findPreviousNodes(num);
        Node node = new Node();
        node.value = num;
        int newLevel = randomLevel();
//        System.out.println("\nnewLevel="+newLevel);
        if (newLevel > level) {
            Node[] tmpPreviousNodes = new Node[newLevel + 1];
            for (int i = 0; i <= level; i++) {
                tmpPreviousNodes[i] = previousNodes[i];
            }
            for (int i = level + 1; i <= newLevel; i++) {
                tmpPreviousNodes[i] = head;
            }
            previousNodes = tmpPreviousNodes;
            level = newLevel;
        }
        node.nexts = new Node[newLevel + 1];
        for (int i = 0; i <= newLevel; i++) {
            node.nexts[i] = previousNodes[i].nexts[i];
            previousNodes[i].nexts[i] = node;
        }
    }


    public boolean erase(int num) {
        boolean[] found = new boolean[1];
        Node[] previousNodes = findPreviousNodes(num, found);
        if (!found[0]) return false;
        for (int i = 0; i <= level; i++) {
            Node current = previousNodes[i].nexts[i];
            if (current != null) {
                Node next = current.nexts[i];
                previousNodes[i].nexts[i] = next;
            }
        }
        return true;
    }

    private int randomLevel() {
        int level = 0;
        double p = 0.25;
        while (random.nextDouble() < p && level < MAX_LEVEL)
            level++;
        return level;
    }
}
