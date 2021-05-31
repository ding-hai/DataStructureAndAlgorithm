package com.dinghai.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;


public class AVLTreeTest {
    private AVLTree<Integer,String> avlTree;
    private Random random;
    final static int N = 100;

    @Before
    public void setUp() {
        avlTree = new AVLTree<>();
        random = new Random(102);
    }

    @Test
    public void testAVL() {
        Integer[] keys = new Integer[N];
        String[] vals = new String[N];
        for (int i = 0; i < N; i++) {
            keys[i] = random.nextInt(N);
            vals[i] = "val_" + keys[i] ;
            avlTree.put(keys[i],vals[i]);
        }
        String[] results = new String[N];
        for (int i = 0; i < N; i++) {
            System.out.println(i);
            String val = avlTree.get(keys[i]);
            Assert.assertEquals(val, vals[i]);
            results[i] = val;
        }
        Assert.assertArrayEquals(vals, results);
    }
}
