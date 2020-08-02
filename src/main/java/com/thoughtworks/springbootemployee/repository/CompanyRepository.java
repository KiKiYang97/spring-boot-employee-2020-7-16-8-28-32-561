package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Dunka
 * @Description CompanyRepository
 * @Date 20:10   2020/8/2
 * @ClassName CompanyRepository
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
