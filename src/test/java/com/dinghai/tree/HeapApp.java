package com.dinghai.tree;


import com.dinghai.util.PerformanceTest;

import java.util.PriorityQueue;
import java.util.Random;

public class HeapApp {
    public static void main(String[] args) {
        int N = 102400;
        Random random = new Random(N * 2);
        Integer[] vals = new Integer[N];
        for (int i = 0; i < N; i++) {
            vals[i] = random.nextInt();
        }
        performanceTestOfJDKHeap(vals);
        performanceTestOfHeap(vals);
    }

    public static void performanceTestOfHeap(Integer[] vals) {
        Heap<Integer> minHeap = new Heap<>();

        PerformanceTest.test(() -> {
            for (int val : vals) {
                minHeap.push(val);
            }
        }, 5);

        PerformanceTest.test(() -> {
            while (!minHeap.isEmpty()) {
                minHeap.pop();
            }
        }, 5);
    }

    public static void performanceTestOfJDKHeap(Integer[] vals) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PerformanceTest.test(() -> {
            for (int val : vals) {
                minHeap.add(val);
            }
        }, 5);

        PerformanceTest.test(() -> {
            while (!minHeap.isEmpty()) {
                minHeap.poll();
            }
        }, 5);
    }


}
