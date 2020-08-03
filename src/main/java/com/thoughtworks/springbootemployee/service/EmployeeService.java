package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NoSuchDataException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.util.ResponseMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Dunka
 * @Description EmployeeService
 * @Date 18:06   2020/8/2
 * @ClassName EmployeeService
 */
@Service
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

    public Employee updateEmployee(Integer id, Employee employeeInfo) throws NoSuchDataException {
        Employee oldEmployee = null;
        employeeInfo.setId(id);
        if (employeeRepository.findById(id).isPresent()) {
            oldEmployee = employeeRepository.findById(id).get();
            BeanUtils.copyProperties(employeeInfo, oldEmployee);
        } else {
            throw new NoSuchDataException();
        }
        return employeeRepository.save(oldEmployee);
    }

    public String deleteEmployeeByEmployeeID(Integer employeeID) throws IllegalOperationException {
        if (employeeRepository.findById(employeeID).isPresent()) {
            employeeRepository.deleteById(employeeID);
        } else {
            throw new IllegalOperationException();
        }
        if (!employeeRepository.findById(employeeID).isPresent()) {
            return ResponseMsg.SUCCESS_MESSAGE;
        } else {
            return ResponseMsg.FAIL_MESSAGE;
        }

    }
}
