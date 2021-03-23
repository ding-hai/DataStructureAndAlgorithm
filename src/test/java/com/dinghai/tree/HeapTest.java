package com.dinghai.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;


public class HeapTest {
    private Heap<Integer> maxHeap;
    private Random random;
    private static final int N = 6;

    @Before
    public void setUp() {
        maxHeap = new Heap<>(N, (o1, o2) -> o1 - o2);
        random = new Random(System.currentTimeMillis());
    }

    @Test
    public void testHeap() {
        Integer[] vals = new Integer[N];
        for (int i = 0; i < N; i++) {
            int val = random.nextInt(N);
            vals[i] = val;
            maxHeap.push(val);
        }
        Arrays.sort(vals, (o1, o2) -> o1 - o2);

        Integer[] results = new Integer[N];
        for (int i = 0; i < N; i++) {
            int top = maxHeap.top();
            int pop = maxHeap.pop();
            Assert.assertEquals(top, pop);
            results[i] = top;
        }
        Assert.assertArrayEquals(vals, results);
    }
}
