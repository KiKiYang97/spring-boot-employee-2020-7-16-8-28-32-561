package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
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
    private List<Employee> employees;
    private Company company;
    private Company companyInfo;

    @BeforeAll
    static void init() {
        companyRepository = mock(CompanyRepository.class);
        companyService = new CompanyService(companyRepository);
    }

    @BeforeEach
    void initData() {
        employees = new ArrayList<>();
        Employee employee = new Employee(0, "kiki", 18, "female", 99999d);
        Employee employee1 = new Employee(1, "kiki", 80, "male", 100d);
        employees.add(employee);
        employees.add(employee1);
        company = new Company(0, "oocl", 2, employees);
        companyInfo = new Company(1, "ali", 2, employees);
        companies = new ArrayList<>();
        companies.add(company);
        companies.add(companyInfo);
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

    @Test
    void should_return_specify_company_when_get_company_given_company_id() {
//        given
        Integer companyId = 1;
        Company filterCompany = companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
        given(companyRepository.findById(companyId)).willReturn(filterCompany);
//        when
        Company specifyCompany = companyService.getCompanyByCompanyId(companyId);
//        then
        assertEquals(filterCompany, specifyCompany);
    }

    @Test
    void should_get_all_employees_when_get_employees_given_company_id() {
//        given
        Integer companyId = 1;
        given(companyRepository.findEmployeesById(companyId)).willReturn(employees);
//        when
        List<Employee> foundEmployees = companyService.getEmployeesByCompanyId(companyId);
//        then
        assertIterableEquals(employees, foundEmployees);
    }

    @Test
    void should_return_companies_when_get_companies_given_page_and_page_size() {
//        given
        Integer page = 1;
        Integer pageSize = 2;
        given(companyRepository.findAll(page, pageSize)).willReturn(companies);
//        when
        List<Company> foundCompanies = companyService.getCompaniesByPageAndPageSize(page, pageSize);
//        then
        assertEquals(companies.size(), foundCompanies.size());
        assertIterableEquals(companies, foundCompanies);
    }

    @Test
    void should_return_company_when_add_company_given_company() {
//        given
        given(companyRepository.save(company)).willReturn(company);
//        when
        Company createdCompany = companyService.addCompany(company);
//        then
        assertEquals(company, createdCompany);
    }

    @Test
    void should_return_updated_company_when_update_company_given_company_id_and_company_info() {
//        given
        Integer companyId = 1;
        given(companyRepository.save(company)).willReturn(companyInfo);
        given(companyRepository.findById(companyId)).willReturn(company);
//        when
        Company updatedCompany = companyService.updateCompany(companyId, companyInfo);
//        then
        assertEquals(companyInfo, updatedCompany);
    }

    @Test
    void should_return_company_when_delete_company_given_company_id() {
//        given
        Integer companyID = 1;
        doAnswer(invocation -> null).when(companyRepository).deleteById(companyID);
//        when
        given(companyRepository.findById(companyID)).willReturn(companyInfo);
        Company company = companyService.deleteCompanyByCompanyID(companyID);
//        then
        assertEquals(companyInfo, company);
    }
}
