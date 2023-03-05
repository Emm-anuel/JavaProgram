package com.epam.javaprogram.multithreading.task1;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Factorial {
    private static class Task extends RecursiveTask<Long> {

        private static final long serialVersionUID = -196522408291343951L;

        private final BigInteger number;

        public Task(int number) {
            this.number = new BigInteger(String.valueOf(number));
        }

        @Override
        protected Long compute() {
            if (this.number.intValue() <= 1) {
                return 1L;
            }

            Task factorial = new Task(this.number.intValue() - 1);
            factorial.fork();
            return this.number.intValue() * factorial.join();
        }
    }

    public static Long factorialOf(final int number) {
        final ForkJoinPool pool = new ForkJoinPool();
        try {
            return pool.invoke(new Task(number));
        } finally {
            pool.shutdown();
        }
    }

    private Factorial() {}
}
