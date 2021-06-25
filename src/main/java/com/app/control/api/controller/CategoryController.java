package com.app.control.api.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.models.Category;
import com.app.control.repository.CategoryRepository;
import com.app.control.service.CategoryService;

@RestController
@RequestMapping(path = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
	
	
	  private CategoryService categoryService;
	  
	  
	  @Autowired public CategoryController(CategoryService categoryService) {
	  this.categoryService = categoryService; }
	  
	  @GetMapping public List<Category> show() { return categoryService.all(); }
	  
	  @GetMapping("/{id}") public ResponseEntity<Category>
	  findById(@PathVariable(name = "id")Long id) { return
	  ResponseEntity.ok(categoryService.findById(id)); }
	 
}
