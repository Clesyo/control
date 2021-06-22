package com.app.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {
    
    // @Autowired
    // private CompanyRepository cr;

    @RequestMapping("/empresa")
    public String index() {
        return "company/company_index";
    }
}
