package com.app.control.repository;

import com.app.control.models.Company;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String>{
    
}
