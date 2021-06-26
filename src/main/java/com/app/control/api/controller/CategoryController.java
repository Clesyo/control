package com.app.control.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.models.Category;
import com.app.control.service.CategoryService;

@RestController
@RequestMapping(path = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public List<Category> show() {
		return categoryService.all();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Category> store(@Validated @RequestBody Category category) {
		Category c = categoryService.create(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(c);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> edit(@PathVariable(name = "id") Long id, @RequestBody Category category) {
		return ResponseEntity.ok(categoryService.update(id, category));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		categoryService.destroy(id);
	}
}
