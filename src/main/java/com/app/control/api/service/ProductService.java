package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Ingredient;
import com.app.control.api.models.Product;
import com.app.control.api.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		return findOrFail(id);
	}

	public Product create(Product product) {
		return productRepository.save(product);
	}

	public Product update(Long id, Product product) {
		Product p = findOrFail(id);

		BeanUtils.copyProperties(product, p);
		return productRepository.save(p);
	}

	public void destroy(Long id) {
		Product p = findOrFail(id);
		productRepository.delete(p);
	}

	private Product findOrFail(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrado"));
	}
}
