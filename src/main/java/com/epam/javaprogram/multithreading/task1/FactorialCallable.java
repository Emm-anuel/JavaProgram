package com.epam.javaprogram.multithreading.task1;

import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.*;

public class FactorialCallable {

    private static class Task implements Callable<Long> {

        private final BigInteger number;
        private final ExecutorService executor;

        public Task(int number, final ExecutorService executor) {
            this.number = new BigInteger(String.valueOf(number));
            this.executor = Objects.requireNonNull(executor);
        }

        @Override
        public Long call() throws Exception {
            if (this.number.intValue() <= 1) {
                return 1L;
            }

            Future<Long> future = executor.submit(new Task(this.number.intValue() - 1, executor));
            return this.number.intValue() * future.get();
        }
    }

    public static Long factorialOf(final int number) {
        final int threads = Runtime.getRuntime().availableProcessors();
        final ExecutorService executor = Executors.newFixedThreadPool(threads);
        try {
            return executor.submit(new Task(number, executor)).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

    private FactorialCallable() {}

}