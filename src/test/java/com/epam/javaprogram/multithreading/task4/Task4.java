package com.epam.javaprogram.multithreading.task4;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class Task4 {

	@Test
	public void test_employee() throws ExecutionException, InterruptedException {
		EmployeeService service = new EmployeeService(new RestEndpoint(), new SalaryService());
		CompletionStage<List<Employee>> stage = service.getHiredEmployeesWithSalaries();
		CompletableFuture<List<Employee>> future = stage.toCompletableFuture();
		List<Employee> list = future.get();

		assertNotNull(list);
		assertEquals(list.get(0).getSalary().getSalary(), 10_000);
		assertEquals(list.get(1).getSalary().getSalary(), 15_000);
		assertEquals(list.get(2).getSalary().getSalary(), 500);
		list.stream().forEach(System.out::println);
	}
}
