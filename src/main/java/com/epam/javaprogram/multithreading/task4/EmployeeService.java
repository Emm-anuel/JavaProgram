package com.epam.javaprogram.multithreading.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class EmployeeService {

    private final RestEndpoint restEndpoint;
    private final SalaryService salaryService;

    public EmployeeService(RestEndpoint restEndpoint, SalaryService salaryService) {
        this.restEndpoint = restEndpoint;
        this.salaryService = salaryService;
    }

    public CompletionStage<List<Employee>> getHiredEmployeesWithSalaries() {
        return CompletableFuture.supplyAsync(() -> restEndpoint.getHiredEmployees())
                .thenCompose(hiredEmployees -> {
                    List<CompletionStage<Employee>> employeeStages = new ArrayList<>();
                    for (Employee employee : hiredEmployees) {
                        CompletionStage<Salary> salaryStage = salaryService.getSalary(employee.getId());
                        CompletionStage<Employee> employeeStage = salaryStage.thenApply(salary -> {
                            employee.setSalary(salary);
                            return employee;
                        });
                        employeeStages.add(employeeStage);
                    }
                    return CompletableFuture.allOf(employeeStages.toArray(new CompletableFuture[0]))
                            .thenApply(employeeStage -> employeeStages.stream()
                                    .map(CompletionStage::toCompletableFuture)
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.toList()));
                });
    }
}
