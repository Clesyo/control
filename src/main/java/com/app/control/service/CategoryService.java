package com.app.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.models.Category;
import com.app.control.repository.CategoryRepository;

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
		return categoryRepository.getById(id);
	}

	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	public Category update(Long id, Category category) {
		Category aux = categoryRepository.getById(id);

		BeanUtils.copyProperties(category, aux);
		return categoryRepository.save(aux);
	}

	public void destroy(Long id) {
		Category category = categoryRepository.getById(id);
		categoryRepository.delete(category);
	}

}
