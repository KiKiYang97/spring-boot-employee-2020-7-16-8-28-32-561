package com.thoughtworks.springbootemployee.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Dunka
 * @Description Company
 * @Date 20:10   2020/8/2
 * @ClassName Company
 */
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Employee> employees;

    public Company() {

    }

    public Company(Integer id, String companyName, Integer employeeNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }
}
