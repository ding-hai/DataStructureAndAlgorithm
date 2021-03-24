package com.dinghai.list;

import com.dinghai.util.PerformanceTest;

import java.util.Random;

public class SkiplistApp {
    public static void main(String[] args) {
        int N = 102400;
        Random random = new Random(1021);
        Skiplist skiplist = new Skiplist();
        PerformanceTest.test(()->{
            for (int i = 0; i < N; i++) {
                int v = random.nextInt(N*2);
                skiplist.add(v);
            }
        },5);

        PerformanceTest.test(()->{
            for (int i = 0; i < N; i++) {
                int v = random.nextInt(N*2);
                skiplist.search(v);
            }
        },5);

        PerformanceTest.test(()->{
            for (int i = 0; i < N; i++) {
                int v = random.nextInt(N*2);
                skiplist.erase(v);
            }
        },5);


    }
}
