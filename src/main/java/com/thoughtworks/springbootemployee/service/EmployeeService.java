package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Author Dunka
 * @Description //TODO
 * @Date 18:06   2020/8/2
 * @ClassName EmployeeService
 */
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByEmployeeId(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getEmployeeByPageAndPageSize(int page, int pageSize) {
        return employeeRepository.getEmployeeByPageAndPageSize(page, pageSize);
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findEmployeesByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employeeInfo) {
        Employee employee = employeeRepository.findById(id);
        BeanUtils.copyProperties(employee, employeeInfo);
        return employeeRepository.save(employeeInfo);
    }

    public Employee deleteEmployeeByemployeeID(int employeeID) {
        return employeeRepository.deleteById(employeeID);
    }
}
