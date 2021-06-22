package com.app.control.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {
    
    @RequestMapping("ingredientes")
    public String index() {
        return "ingredient/ingredient_index";
    }
}
