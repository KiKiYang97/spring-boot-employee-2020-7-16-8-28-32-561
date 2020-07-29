package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        employees = new ArrayList<>();
        this.employees.add(new Employee(0, "alibaba3", 19, "male", 8000));
        this.employees.add(new Employee(1, "Kiki", 18, "female", 1000d));
        this.employees.add(new Employee(2, "Eason", 25, "male", 2000d));
        this.employees.add(new Employee(3, "Eva", 18, "female", 3000d));
        this.employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        this.employees.add(new Employee(5, "tengxun2", 19, "female", 7000));
    }

    public List<Employee> getAllEmployees(){
        return employees;
    }
}
