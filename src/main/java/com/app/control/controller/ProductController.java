package com.app.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    
    @RequestMapping("/produtos")
    public String show() {
        return "product/product_index";
    }

    @RequestMapping("/produto/novo")
    public String create() {
        return "product/product_create";
    }
}
