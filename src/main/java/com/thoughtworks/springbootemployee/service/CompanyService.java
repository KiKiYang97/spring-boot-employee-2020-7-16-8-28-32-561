package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author Dunka
 * @Description //TODO
 * @Date 20:10   2020/8/2
 * @ClassName CompanyService
 */
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company getCompanyByCompanyId(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(Integer companyId) {
        return companyRepository.findEmployeesById(companyId);
    }

    public Page<Company> getCompaniesByPageAndPageSize(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer companyId, Company companyInfo) {
        Company company = companyRepository.findById(companyId).orElse(null);
        BeanUtils.copyProperties(company, companyInfo);
        return companyRepository.save(company);
    }

    public Company deleteCompanyByCompanyID(Integer companyID) {
//        final Company company = companyRepository.findById(companyID);
//        companyRepository.deleteById(companyID);
//        return company;
        return null;
    }
}
