package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Dunka
 * @Description EmployIntegrationTest
 * @Date 23:48   2020/8/2
 * @ClassName EmployIntegrationTest
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EmployIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    private List<Employee> employees;
    private List<Company> companies;
    private Employee employee;
    private Company company;

    @AfterEach
    void deleteData() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @BeforeEach
    void initData() {
        employee = new Employee(1, "kiki", 80, "female", 1000d);
        Employee employee1 = new Employee(2, "lili", 80, "female", 1000d);
        employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee);
        company = new Company(1, "oocl", 2888, employees);
        String employeeInfo = " {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"kiki\",\n" +
                "            \"age\": 81,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 1,\n" +
                "            \"companyId\": " + company.getId() + "\n" +
                "}";
    }

    @Test
    void should_get_employee_when_hit_get_employee_end_point_given_nothing() throws Exception {
//        given
        companyRepository.save(company);
        employee.setCompanyId(company.getId());
        employeeRepository.save(employee);
//        when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("kiki"))
                .andExpect(jsonPath("$[0].age").value(80))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").isNumber());
//        then
    }

    @Test
    void should_return_employee_when_hit_post_employee_end_point_given_employee() {
        companyRepository.save(company);

    }
}
