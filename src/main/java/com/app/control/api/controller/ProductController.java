package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.app.control.api.models.Category;
import com.app.control.api.models.Company;
import com.app.control.api.models.Product;
import com.app.control.api.models.dto.ProductDTO;
import com.app.control.api.service.CategoryService;
import com.app.control.api.service.CompanyService;
import com.app.control.api.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final CompanyService companyService;

	@GetMapping
	@ApiOperation("Lista todos os produtos.")
	public List<Product> all() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um produto pelo codigo ID.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto encontrado."),
			@ApiResponse(code = 404, message = "Produto não encontrado.") })
	public Product findById(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um produto")
	@ApiResponses({ @ApiResponse(code = 201, message = "Produco cadastrado com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação.") })
	public ProductDTO store(@RequestBody @Valid ProductDTO produto) {
		Category category = categoryService.findById(produto.getCategory());
		Company company = companyService.findById(produto.getCompany());

		Product product = Product.builder().name(produto.getName()).description(produto.getDescription())
				.price(produto.getPrice()).category(category).company(company).image(produto.getImage()).menuOp(false)
				.type(produto.getType()).build();

		return ProductDTO.convertToDto(productService.create(product));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto alterado com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Produto não encontrado.") })
	public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody @Valid Product product) {
		return ResponseEntity.ok(productService.update(id, product));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 204, message = "Produto deletado com sucesso."),
		@ApiResponse(code = 404, message = "Produto não encontrado.") })
	public void delete(Long id) {
		productService.destroy(id);
	}
}
