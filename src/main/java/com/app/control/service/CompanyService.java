package com.app.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.models.Company;
import com.app.control.repository.CompanyRepository;

@Service
public class CompanyService {

	private CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public Company findById(Long id) {
		return findOrFail(id);
	}
	
	public Company create(Company company) {
		return companyRepository.save(company);
	}
	
	public Company update(Long id, Company company) {
		Company c = findOrFail(id);
		
		BeanUtils.copyProperties(company, c);
		
		return companyRepository.save(c);
	}
	
	public void destroy(Long id) {
		Company c = findOrFail(id);
		companyRepository.delete(c);
	}

	private Company findOrFail(Long id) {
		return companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrada"));
	}
}
