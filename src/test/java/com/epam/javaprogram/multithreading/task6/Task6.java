package com.epam.javaprogram.multithreading.task6;
import com.epam.javaprogram.util.Results;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Task6 {

	@Test
	public void test_fibonacci() {
		assertEquals(6765, Fibonacci.of(20));
	}

	@Test
	public void test_squares() {
		int n = 500_000_000;
		double[] arr = new double[n];
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = rand.nextDouble();
		}

		Results results = new Results();
		results.startTime();
		System.out.println("Sum of the squares of the array using threads :: " + Squares.sumOf(arr));
		long taken = results.endTime();
		System.out.println(taken + " nano");

		double sum = 0;
		results.startTime();
		for (double v : arr) {
			sum += v * v;
		}
		System.out.println("Sum of the squares of the array using sequential progress :: " + sum);
		taken = results.endTime();
		System.out.println(taken + " nano");
	}

}
