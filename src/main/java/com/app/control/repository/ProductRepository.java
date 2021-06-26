package com.app.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.control.models.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{

}
