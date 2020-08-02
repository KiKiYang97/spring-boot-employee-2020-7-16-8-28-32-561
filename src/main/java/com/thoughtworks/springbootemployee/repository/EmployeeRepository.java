package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Dunka
 * @Description EmployeeRepository
 * @Date 18:06   2020/8/2
 * @ClassName EmployeeRepository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
