package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
        Employee employee = new Employee();
        employee.setGender("gender");
        log.info(employee.toString());
//        when
//        then

    }
}
