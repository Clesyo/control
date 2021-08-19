package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.app.control.api.models.Category;
import com.app.control.api.models.Company;
import com.app.control.api.models.dto.CategoryDTO;
import com.app.control.api.service.CategoryService;
import com.app.control.api.service.CompanyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	private final CompanyService companyService;

	@GetMapping
	@ApiOperation("Lista todas as categorias")
	public List<Category> show() {
		return categoryService.all();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca uma categoria pelo código ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria encontrada."),
			@ApiResponse(code = 404, message = "Categoria não existe.") })
	public CategoryDTO findById(@PathVariable Long id) {
		return CategoryDTO.convertToDto(categoryService.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um categoria")
	@ApiResponses({ @ApiResponse(code = 201, message = "Categooria salva com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação.") })
	public CategoryDTO store(@Valid @RequestBody CategoryDTO categoria) {
		Company company = companyService.findById(categoria.getCompany());
		Category category = Category.builder().name(categoria.getName()).company(company).parent(categoria.getParent())
				.build();
		return CategoryDTO.convertToDto(categoryService.create(category));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera uma categoria")
	@ApiResponses({ @ApiResponse(code = 204, message = "Categoria alterada com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Categoria não encontrada.") })
	public CategoryDTO edit(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoria) {
		Company company = companyService.findById(categoria.getCompany());
		Category category = Category.builder().name(categoria.getName()).company(company).parent(categoria.getParent())
				.build();
		return CategoryDTO.convertToDto(categoryService.update(id, category));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta uma categoria")
	@ApiResponses({ @ApiResponse(code = 204, message = "Categoria deletada com sucesso."),
		@ApiResponse(code = 404, message = "Categoria não encontrada.") })
	public void delete(@PathVariable Long id) {
		categoryService.destroy(id);
	}
}
