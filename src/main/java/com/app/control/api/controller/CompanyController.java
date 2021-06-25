package com.app.control.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.control.models.Company;
import com.app.control.repository.CompanyRepository;

@Controller
public class CompanyController {
    
     @Autowired
     private CompanyRepository cr;

    @RequestMapping(value = "/empresa", method = RequestMethod.GET)
    public String index() {
        return "company/company_index";
    }
    
    @RequestMapping(value = "/empresas", method = RequestMethod.POST)
	public String store(Company company) {
    	cr.save(company);
		return "redirect:/empresas";
	}
}
