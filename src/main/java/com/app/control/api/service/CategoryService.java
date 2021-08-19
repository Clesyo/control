package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Category;
import com.app.control.api.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> all() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		return findOrFail(id);
	}

	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	public Category update(Long id, Category category) {
		Category aux = findOrFail(id);

		BeanUtils.copyProperties(category, aux);
		return categoryRepository.save(aux);
	}

	public void destroy(Long id) {
		Category category = findOrFail(id);
		categoryRepository.delete(category);
	}

	private Category findOrFail(Long id) {
		return categoryRepository.findById(id).orElseThrow(()-> new EntityNotExist("Categoria não encontrada."));
	}
}
