package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.util.ResponseMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Dunka
 * @Description CompanyService
 * @Date 20:10   2020/8/2
 * @ClassName CompanyService
 */
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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
        return employeeRepository.findByCompanyId(companyId);
    }

    public Page<Company> getCompaniesByPageAndPageSize(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {
        Integer count = company.getEmployees().size();
        company.setEmployeeNumber(count);
        companyRepository.save(company);
        company.getEmployees().forEach(employee -> {
            employee.setCompanyId(company.getId());
            employeeRepository.save(employee);
        });
        return companyRepository.findById(company.getId()).orElse(null);
    }

    public Company updateCompany(Integer companyId, Company companyInfo) {
        Company oldCompany = null;
        companyInfo.setId(companyId);
        if (companyRepository.findById(companyId).isPresent()) {
            oldCompany = companyRepository.findById(companyId).get();
        }
        BeanUtils.copyProperties(companyInfo, oldCompany);
        return companyRepository.save(oldCompany);
    }

    public String deleteCompanyByCompanyID(Integer companyID) {
        companyRepository.deleteById(companyID);
        if (!companyRepository.findById(companyID).isPresent()) {
            return ResponseMsg.SUCCESS_MESSAGE;
        } else {
            return ResponseMsg.FAIL_MESSAGE;
        }
    }
}
