package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Dunka
 * @Description CompanyController
 * @Date 23:17   2020/7/28
 * @ClassName CompanyController
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyByCompanyId(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getCompaniesByPageAndPageSize(Integer page, Integer pageSize) {
        return companyService.getCompaniesByPageAndPageSize(page, pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company newCompany) {
        return companyService.updateCompany(id, newCompany);
    }

    @DeleteMapping("/{id}")
    public String deleteAllEmployeesByCompanyId(@PathVariable Integer id) {
        return companyService.deleteCompanyByCompanyID(id);
    }
}
