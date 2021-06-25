package com.app.control.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.control.models.Product;
import com.app.control.repository.ProductRepository;

@Controller
public class ProductController {
    
/*
	@Autowired
	private ProductRepository pr;
	
	
    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public String show() {
        return "product/product_index";
    }

    @RequestMapping(value = "/produto/novo", method = RequestMethod.GET)
    public String create() {
        return "product/product_create";
    }
    @RequestMapping(value = "/produto/novo", method = RequestMethod.GET)
    public String store(Product product) {
    	pr.save(product);
    	return "redirect:/produtos";
    }
    */
}
