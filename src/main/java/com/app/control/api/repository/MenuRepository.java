package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.api.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
