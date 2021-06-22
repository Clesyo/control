package com.app.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @RequestMapping("/categorias")
    public String show() {
        return "category/category_index";
    }
}
