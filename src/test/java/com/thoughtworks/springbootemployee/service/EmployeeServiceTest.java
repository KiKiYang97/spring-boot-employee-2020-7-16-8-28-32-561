package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Employee newEmployee;

    @BeforeAll
    static void init() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @BeforeEach
    void initData() {
        employees = new ArrayList<>();
        newEmployee = new Employee(0, "kiki", 18, "female", 99999d);
        employee = new Employee(1, "kiki", 80, "male", 100d);
        employees.add(employee);
        employees.add(newEmployee);
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

    @Test
    void should_return_employees_when_get_employees_given_page_and_page_size() {
//        given
        int page = 1;
        int pageSize = 2;
        given(employeeRepository.getEmployeeByPageAndPageSize(page, pageSize)).willReturn(employees);
//        when
        List<Employee> pageEmployees = employeeService.getEmployeeByPageAndPageSize(page, pageSize);
//        then
        assertIterableEquals(employees, pageEmployees);
    }

    @Test
    void should_return_specify_gender_employees_when_get_employees_given_gender() {
//        given
        String gender = "male";
        List<Employee> filterEmployees = employees
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
        given(employeeRepository.findEmployeesByGender(gender)).willReturn(filterEmployees);
//        when
        List<Employee> maleEmployees = employeeService.getEmployeeByGender(gender);
//        then
        assertIterableEquals(filterEmployees, maleEmployees);
    }

    @Test
    void should_return_new_employee_when_add_employee_given_employee() {
//        given
        given(employeeRepository.save(employee)).willReturn(employee);
//        when
        Employee createdEmployee = employeeService.addEmployee(employee);
//        then
        assertEquals(employee, createdEmployee);
    }

    @Test
    void should_return_modify_employee_when_update_employee_given_update_employee() {
//        given
        int employeeID = 1;
        given(employeeRepository.save(employee)).willReturn(newEmployee);
        given(employeeRepository.findById(employeeID)).willReturn(employee);
//        when
        Employee updateEmployee = employeeService.updateEmployee(employeeID, newEmployee);
//        then
//        verify(employeeRepository).save(updateEmployee);
        assertEquals(newEmployee, updateEmployee);
    }
}
