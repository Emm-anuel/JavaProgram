package com.epam.javaprogram.multithreading.task1;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class Task1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(Task1.class);

	@Test
	public void test_factorial() {
		int number = 5;
		long start = System.nanoTime();
		long result = Factorial.factorialOf(number);
		long taken = System.nanoTime() - start;

		LOGGER.debug("Factorial of '{}' using FJP : {} (in {} nano)", number, result, taken);

		start = System.nanoTime();
		result = FactorialCallable.factorialOf(number);
		taken = System.nanoTime() - start;

		assertEquals(120, result);

		LOGGER.debug("Factorial of '{}' using Callable : {} (in {} nano)", number, result, taken);
	}
}
