package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Dunka
 * @Description EmployeeRepository
 * @Date 18:06   2020/8/2
 * @ClassName EmployeeRepository
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeesByGender(String gender);

    List<Employee> findByCompanyId(Integer companyId);
}
