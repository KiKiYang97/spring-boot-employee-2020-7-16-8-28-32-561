package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @Author Dunka
 * @Description //TODO
 * @Date 20:08   2020/8/2
 * @ClassName CompanyServiceTest
 */
public class CompanyServiceTest {
    private static CompanyRepository companyRepository;
    private static CompanyService companyService;
    private List<Company> companies;

    @BeforeAll
    static void init() {
        companyRepository = mock(CompanyRepository.class);
        companyService = new CompanyService(companyRepository);
    }

    @BeforeEach
    void initData() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(0, "kiki", 18, "female", 99999d);
        Employee employee1 = new Employee(1, "kiki", 80, "male", 100d);
        employees.add(employee);
        employees.add(employee1);
        Company company = new Company(0, "oocl", 2, employees);
        Company company1 = new Company(0, "ali", 2, employees);
        companies = new ArrayList<>();
        companies.add(company);
        companies.add(company1);
    }

    @Test
    void should_get_all_companies_when_get_all_given_no_paremeter() {
//        given
        given(companyRepository.findAll()).willReturn(companies);
//        when
        List<Company> foundCompany = companyService.findAll();
//        then
        assertIterableEquals(companies, foundCompany);
    }
}
