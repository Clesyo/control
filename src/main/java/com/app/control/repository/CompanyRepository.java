package com.app.control.repository;

import com.app.control.models.Company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

public interface CompanyRepository extends CrudRepository<Company, Long>{
    
	
}
