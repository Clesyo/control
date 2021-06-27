package com.app.control.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.models.Company;
import com.app.control.repository.CompanyRepository;
import com.app.control.service.CompanyService;

@RestController
@RequestMapping(path = "/empresa", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

	private CompanyService companyService;

	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping
	public List<Company> all() {
		return companyService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable Long id) {
		return ResponseEntity.ok(companyService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Company> store(Company company) {
		Company c = companyService.create(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Company> edit(@PathVariable(name = "id") Long id, @RequestBody Company company) {
		return ResponseEntity.ok(companyService.update(id, company));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		companyService.destroy(id);
	}
}
