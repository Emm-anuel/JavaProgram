package com.epam.javaprogram.multithreading.task4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RestEndpoint {

    public List<Employee> getHiredEmployees() {
        Employee employee1 = new Employee(1);
        Employee employee2 = new Employee(2);
        Employee employee3 = new Employee(3);

        return Arrays.asList(employee1,employee2,employee3);
    }
}
