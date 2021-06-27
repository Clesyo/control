package com.app.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
