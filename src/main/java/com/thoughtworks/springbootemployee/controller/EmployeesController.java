package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Dunka
 * @Description //TODO
 * @Date 23:17   2020/7/28
 * @ClassName EmployeeController
 */
@RestController
@RequestMapping("/Kiki/employees")
public class EmployeesController {
    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Kiki", 18, "female", 1000d));
        employees.add(new Employee(2, "Eason", 25, "male", 2000d));
        employees.add(new Employee(3, "Eva", 18, "female", 3000d));
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return new Employee(id, "Kiki", 18, "female", 1000d);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeesByPageAndPageSize(int page, int pageSize) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "alibaba3", 19, "male", 8000));
        employees.add(new Employee(1, "Kiki", 18, "female", 1000d));
        employees.add(new Employee(2, "Eason", 25, "male", 2000d));
        employees.add(new Employee(3, "Eva", 18, "female", 3000d));
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(5, "tengxun2", 19, "female", 7000));
        int beginNumber = (page - 1) * pageSize;
        int endNumber = page * pageSize;
        List<Employee> employeesResult = new ArrayList<>();
        for (int i = beginNumber; i < endNumber; i++) {
            employeesResult.add(employees.get(i));
        }
        return employeesResult;
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(String gender) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "alibaba3", 19, "male", 8000));
        employees.add(new Employee(1, "Kiki", 18, "female", 1000d));
        employees.add(new Employee(2, "Eason", 25, "male", 2000d));
        employees.add(new Employee(3, "Eva", 18, "female", 3000d));
        employees.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(5, "tengxun2", 19, "female", 7000));

        final List<Employee> collect = employees
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
        return collect;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return new Employee(employee.getId(), employee.getName(), employee.getAge(),
                employee.getGender(), employee.getSalary());
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee newEmployee){
        Employee oldEmployee = new Employee(id);
        oldEmployee.setName(newEmployee.getName());
        oldEmployee.setAge(newEmployee.getAge());
        oldEmployee.setGender(newEmployee.getGender());
        return oldEmployee;
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0, "alibaba3", 19, "male", 8000));
        employees.add(new Employee(1, "Kiki", 18, "female", 1000d));
        employees.add(new Employee(2, "Eason", 25, "male", 2000d));
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employees::remove);
         return employees;
    }
}
