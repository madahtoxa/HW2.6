package com.collection;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap();
    final static private int MAX_SIZE = 4;

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName), employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(createKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) {
        if (!employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(createKey(firstName, lastName));
    }

    public List<Employee> getAll() {
        return List.copyOf(employees.values());
    }

    private String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
}