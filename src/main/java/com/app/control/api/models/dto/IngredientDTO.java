package com.app.control.api.models.dto;

import com.app.control.api.models.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO {
	
	private long id;
	private String name;

	
	public static IngredientDTO convertToDto(Ingredient ingredient) {
		return new IngredientDTO(ingredient.getId(), ingredient.getName());
	}
}
