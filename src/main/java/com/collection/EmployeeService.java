package com.collection;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;


@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private static final int MAX_SIZE = 5;

    public EmployeeService() {
        Employee e1 = new Employee("Ivan", "Noname", 1, 111111);
        Employee e2 = new Employee("Masha", "Noname1", 1, 511111);
        Employee e3 = new Employee("Petya", "Noname2", 1, 211111);
        employees.put(createKey(e1),e1);
        employees.put(createKey(e2),e2);
        employees.put(createKey(e3),e3);

    }


    public Employee add(String firstName, String lastName, int department, double salary) {
        if(!org.apache.commons.lang3.StringUtils.isAlpha(firstName)
                ||!org.apache.commons.lang3.StringUtils.isAlpha(lastName)){
            throw new InvalidDateException();
        }
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeNotFoundException();
        }
        Employee employeeToAdd = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }

        employees.put(createKey(firstName, lastName), employeeToAdd);
        correctCase(employeeToAdd);
        return employeeToAdd;
    }

    public Employee remove(String firstName, String lastName) {
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
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }

    private static String createKey(Employee employee) {
        return createKey(employee.getFirstName(), employee.getLastName());
    }

    private static String createKey(String firstName, String lastName) {
        return (firstName+lastName).toLowerCase();
    }

    private static void correctCase (Employee employee){
        String correctedFirstName = StringUtils.capitalize(employee.getFirstName().toLowerCase());
        employee.setFirstName(correctedFirstName);
        String correctedLastName = StringUtils.capitalize(employee.getLastName().toLowerCase());
        employee.setLastName(correctedLastName);
    }
}