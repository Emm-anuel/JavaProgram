package com.epam.javaprogram.multithreading.task4;

public class Salary {

    private long idEmpleado;

    private long salary;

    public Salary(long idEmpleado, long salary) {
        this.idEmpleado = idEmpleado;
        this.salary = salary;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salary=" + salary +
                '}';
    }
}
