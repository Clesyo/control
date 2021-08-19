package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.api.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
