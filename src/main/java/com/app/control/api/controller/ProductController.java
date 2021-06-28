package com.app.control.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.app.control.models.Product;
import com.app.control.service.ProductService;

@RestController
@RequestMapping(path = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping
	public List<Product> all() {
		return productService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable(name = "id")Long id ) {
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Product> store(@RequestBody Product product, List<Ingredient> ingredients) {
		Product p =  productService.create(product, ingredients);
		return ResponseEntity.status(HttpStatus.CREATED).body(p);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> edit(@PathVariable(name= "id") Long id,@RequestBody Product product) {
		return ResponseEntity.ok(productService.update(id, product));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(Long id) {
	productService.destroy(id);
	}
}
