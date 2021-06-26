package com.app.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.models.Ingredient;
import com.app.control.repository.IngredientRepository;

@Service
public class IngredientService {

	private IngredientRepository ingredientRepository;

	@Autowired
	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	public List<Ingredient> all() {
		return ingredientRepository.findAll();
	}
	
	public Ingredient findById(Long id) {
		return findOrFail(id);
	}
	
	public Ingredient create(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	public Ingredient update(Long id, Ingredient ingredient) {
		Ingredient i = findOrFail(id);
		BeanUtils.copyProperties(ingredient, i);
		
		return ingredientRepository.save(i);
	}
	
	public void destroy(Long id) {
		Ingredient ingredient = findOrFail(id);
		ingredientRepository.delete(ingredient);
	}
	
	private Ingredient findOrFail(Long id) {
		return ingredientRepository.findById(id).orElseThrow(() -> new EntityNotExist(getClass().getName()+" não encontrada."));
	}
}
