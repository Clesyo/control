package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.api.models.Order;
import com.app.control.api.models.dto.OrderDTO;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
