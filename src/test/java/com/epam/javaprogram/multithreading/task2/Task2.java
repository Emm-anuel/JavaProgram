package com.epam.javaprogram.multithreading.task2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class Task2 {

	@Test
	public void test_short() {
		int[] arrayInt = {5, 1, 9, 3, 7, 6, 8, 2, 4};
		int[] arrayExpected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] arrayProceeded = MergeSort.sortOf(arrayInt);
		System.out.println(Arrays.toString(arrayProceeded));
		assertArrayEquals(arrayExpected, arrayProceeded);
	}
}
