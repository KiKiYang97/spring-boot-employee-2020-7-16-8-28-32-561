package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @Author Dunka
 * @Description EmployeeServiceTest
 * @Date 17:34   2020/8/2
 * @ClassName EmployeeServiceTest
 */
@Slf4j
public class EmployeeServiceTest {
    private static EmployeeRepository employeeRepository;
    private static EmployeeService employeeService;
    private List<Employee> employees;
    private Employee employee;

    @BeforeAll
    static void init() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @BeforeEach
    void initData() {
        employees = new ArrayList<>();
        employee = new Employee(1, "kiki", 80, "male", 100d);
        employees.add(employee);
    }

    @Test
    void should_return_all_employees_when_get_all_employees() {
//        given
//        when
        given(employeeRepository.findAll()).willReturn(employees);
        List<Employee> findEmployees = employeeService.getAllEmployees();
//        then
        assertIterableEquals(employees, findEmployees);

    }

    @Test
    void should_return_employee_when_get_employee_given_employee_id() {
//        given
        given(employeeRepository.findById(employee.getId())).willReturn(employee);
//        when
        Employee findEmployee = employeeService.getEmployeeByEmployeeId(employee.getId());
//        then
        assertEquals(employee, findEmployee);
    }
}
