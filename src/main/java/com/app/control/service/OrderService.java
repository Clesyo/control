package com.app.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.models.Order;
import com.app.control.models.Product;
import com.app.control.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		return findOrFail(id);
	}

	private Order create(Order order) {
		return orderRepository.save(order);
	}
	
	public Order createOrderWithItem(Order order, List<Product> products) {
		Order o = create(order);
		o.getProducts().addAll(products);
		
		return orderRepository.save(o);
	}

	public Order update(Long id, Order order) {
		Order o = findOrFail(id);
		BeanUtils.copyProperties(order, o);
		return orderRepository.save(o);
	}

	public void destroy(Long id) {
		Order o = findOrFail(id);
		orderRepository.delete(o);
	}

	private Order findOrFail(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrado."));
	}

}
