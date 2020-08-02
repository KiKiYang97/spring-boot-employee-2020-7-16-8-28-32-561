package com.thoughtworks.springbootemployee.model;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Double salary;

    public Employee(Integer id, String name, Integer age, String gender, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }
}
