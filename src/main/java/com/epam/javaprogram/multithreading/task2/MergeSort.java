package com.epam.javaprogram.multithreading.task2;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MergeSort {

    private static class Task extends RecursiveTask<int[]> {

        private int[] array;

        public Task(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            if (array.length <= 1) {
                return array;
            }
            int midpoint = array.length / 2;
            Task leftArray = new Task(Arrays.copyOfRange(array, 0, midpoint));
            Task rightArray = new Task(Arrays.copyOfRange(array, midpoint, array.length));
            leftArray.fork();
            rightArray.fork();
            int[] left = leftArray.join();
            int[] right = rightArray.join();
            return merge(left, right);
        }

        private int[] merge(int[] left, int[] right) {
            int[] result = new int[left.length + right.length];
            int ind = 0, ind2 = 0, ind3 = 0;
            while (ind < left.length && ind2 < right.length) {
                if (left[ind] < right[ind2]) {
                    result[ind3++] = left[ind++];
                } else {
                    result[ind3++] = right[ind2++];
                }
            }
            while (ind < left.length) {
                result[ind3++] = left[ind++];
            }
            while (ind2 < right.length) {
                result[ind3++] = right[ind2++];
            }
            return result;
        }
    }

    public static int[] sortOf(final int[] arrayInt) {
        final ForkJoinPool pool = new ForkJoinPool();
        try {
            return pool.invoke(new MergeSort.Task(arrayInt));
        } finally {
            pool.shutdown();
        }
    }

    private MergeSort() {}
}




