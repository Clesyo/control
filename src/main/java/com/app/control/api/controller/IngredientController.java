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

import com.app.control.api.models.Company;
import com.app.control.api.models.Ingredient;
import com.app.control.api.models.dto.IngredientDTO;
import com.app.control.api.service.CompanyService;
import com.app.control.api.service.IngredientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/ingrediente", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IngredientController {

	private final IngredientService ingredientService;
	private final CompanyService companyService;

	@GetMapping
	@ApiOperation("Lista todos os ingredientes.")
	public List<Ingredient> show() {
		return ingredientService.all();
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@ApiOperation("Busca um ingrediente pelo codigo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ingrediente encontrado."),
			@ApiResponse(code = 404, message = "Ingrediente não existe.") })
	public Ingredient findById(@PathVariable Long id) {
		return ingredientService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva ingrediente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Ingrediente salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação.") })
	public IngredientDTO store(@RequestBody IngredientDTO ingrediente) {
		Ingredient ingredient = Ingredient.builder().name(ingrediente.getName()).build();
		return IngredientDTO.convertToDto(ingredientService.create(ingredient));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um ingrediente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Ingrediente salvo com sucesso."),
			@ApiResponse(code = 404, message = "Ingrediente não encontrado.") })
	public IngredientDTO edit(@PathVariable Long id, @RequestBody IngredientDTO ingrediente) {
		Ingredient ingredient = Ingredient.builder().name(ingrediente.getName()).build();
		return IngredientDTO.convertToDto(ingredientService.update(id, ingredient));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um ingrediente")
	public void delete(@PathVariable Long id) {
		ingredientService.destroy(id);
	}
}
