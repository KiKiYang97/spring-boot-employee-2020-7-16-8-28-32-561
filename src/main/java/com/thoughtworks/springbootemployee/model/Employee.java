package com.thoughtworks.springbootemployee.model;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Double salary;

}
