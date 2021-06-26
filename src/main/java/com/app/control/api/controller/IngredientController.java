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

import com.app.control.models.Ingredient;
import com.app.control.service.IngredientService;

@RestController
@RequestMapping(path = "/ingrediente", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {

	private IngredientService ingredientService;

	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}
	
	@GetMapping
	public List<Ingredient> show() {
		return ingredientService.all();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> findById(@PathVariable(name = "id")Long id) {
		return ResponseEntity.ok(ingredientService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Ingredient> store(@Validated @RequestBody Ingredient ingredient) {
		Ingredient i = ingredientService.create(ingredient);
		return ResponseEntity.status(HttpStatus.CREATED).body(i);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ingredient> edit(@PathVariable(name = "id") Long id, @RequestBody Ingredient ingredient) {
		return ResponseEntity.ok(ingredientService.update(id, ingredient));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id")Long id) {
		ingredientService.destroy(id);
	}
}
