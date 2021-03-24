package com.dinghai.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class SkiplistTest {
    private static final int N = 102040;
    private Skiplist skiplist;
    private Random random1;
    private Random random2;
    private Random random3;

    @Before
    public void setUp() {
        skiplist = new Skiplist();
        random1 = new Random(System.currentTimeMillis());
        random2 = new Random(System.currentTimeMillis()+13);
        random3 = new Random(System.currentTimeMillis()+111);
    }

    @Test
    public void randomRW() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int val = random1.nextInt(N);
            if(set.contains(val)) continue;
            set.add(val);
            skiplist.add(val);
            int target = random2.nextInt(N);
            int erase = random3.nextInt(N);

            Assert.assertFalse(skiplist.search(target) ^ set.contains(target));
            skiplist.erase(erase);
            set.remove(erase);
        }

    }
}
