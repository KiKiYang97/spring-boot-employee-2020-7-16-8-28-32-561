package com.thoughtworks.springbootemployee.model;

import lombok.Data;

import java.util.List;

@Data
public class Company {
    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

}
