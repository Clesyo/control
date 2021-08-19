package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.control.api.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
