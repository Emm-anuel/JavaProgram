package com.epam.javaprogram.multithreading.task4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SalaryService {
    public static CompletionStage<Salary> getSalary(long  hiredEmployeeId) {
        CompletableFuture<List<Salary>> completableSalary = CompletableFuture.supplyAsync(() -> {
            Salary salary1 = new Salary(1, 10_000);
            Salary salary2 = new Salary(2, 15_000);
            Salary salary3 = new Salary(3, 500);

            return Arrays.asList(salary1,salary2,salary3);
        });

        return completableSalary.thenApply(list -> list.stream().filter(item -> item.getIdEmpleado() == hiredEmployeeId).findAny().get());
    }
}
