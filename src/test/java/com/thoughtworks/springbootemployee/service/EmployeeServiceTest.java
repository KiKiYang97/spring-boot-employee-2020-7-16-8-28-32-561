package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void should_return_all_employees_when_get_all_employees() {
//        given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
//        when
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        given(employeeRepository.findAll()).willReturn(employees);
        List<Employee> findEmployees = employeeService.getAllEmployees();
//        then
        assertIterableEquals(employees, findEmployees);

    }
}
