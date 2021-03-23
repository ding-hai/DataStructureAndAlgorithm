package com.dinghai.util;

public class PerformanceTest {

    @FunctionalInterface
    public interface PureFunction {
        void apply();
    }

    public static void test(PureFunction function, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            function.apply();
        }
        long end = System.currentTimeMillis();
        double avgCost = (1.0 * (end - start)) / count;
        System.out.println("cost avg time: " + avgCost + "(ms)");

    }
}
