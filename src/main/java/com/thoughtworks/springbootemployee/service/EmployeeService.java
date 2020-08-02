package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.util.ResponseMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        return employeeRepository.findById(id).orElse(null);
    }

    public Page<Employee> getEmployeeByPageAndPageSize(int page, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findEmployeesByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employeeInfo) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(employee, employeeInfo);
        return employeeRepository.save(employeeInfo);
    }

    public String deleteEmployeeByemployeeID(Integer employeeID) {
        employeeRepository.deleteById(employeeID);
        if (employeeRepository.findById(employeeID) == null) {
            return ResponseMsg.SUCCESS_MESSAGE;
        } else {
            return ResponseMsg.FAIL_MESSAGE;
        }

    }
}
