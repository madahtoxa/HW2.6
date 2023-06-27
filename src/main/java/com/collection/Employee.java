package com.collection;

public class Employee {

    private String firstName;
    private String lastName;
    private int department;
    private double salary;

    public Employee(String firstName, String lastName,int department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department=department;
        this.salary=salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}