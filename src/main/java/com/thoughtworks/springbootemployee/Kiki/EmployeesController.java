package com.thoughtworks.springbootemployee.Kiki;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst().orElse(null);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeesByPageAndPageSize(int page, int pageSize) {
        List<Employee> employees = employeeService.getAllEmployees();
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
        List<Employee> employees = employeeService.getAllEmployees();

        return employees
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return new Employee(employee.getId(), employee.getName(), employee.getAge(),
                employee.getGender(), employee.getSalary());
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee newEmployee){
        List<Employee> employees = employeeService.getAllEmployees();
        Employee oldEmployee = employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst().orElse(null);
        oldEmployee.setName(newEmployee.getName());
        oldEmployee.setAge(newEmployee.getAge());
        oldEmployee.setGender(newEmployee.getGender());
        return oldEmployee;
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id){
        List<Employee> employees = employeeService.getAllEmployees();
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employees::remove);
         return employees;
    }
}
