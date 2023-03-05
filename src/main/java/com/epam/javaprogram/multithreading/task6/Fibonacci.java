package com.epam.javaprogram.multithreading.task6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci {
    private static final int GRANULARITY = 10;
    private static class Task extends RecursiveTask<Integer> {
        final int n;
        public Task(int n) { this.n = n; }

        protected Integer compute() {
            if (n <= 1){
                return n;
            } else if (n <= GRANULARITY) {
                return fibonacci(n);
            } else {
                Task f1 = new Task(n - 1);//3 - 2 - 1 - 1
                f1.fork();
                Task f2 = new Task(n - 2);//2 - 1 - 0 - 0
                return f2.compute() + f1.join();
            }
        }

        private int fibonacci(int number) {
            int a = 0, b = 1;
            for (int ind = 0; ind < number; ind++) {
                int c = a + b;
                a = b;
                b = c;
            }
            return a;
        }
    }

    public static int of(int number) {
        final ForkJoinPool pool = new ForkJoinPool();
        try {
            return pool.invoke(new Fibonacci.Task(number));
        } finally {
            pool.shutdown();
        }
    }

    private Fibonacci() {}
}
