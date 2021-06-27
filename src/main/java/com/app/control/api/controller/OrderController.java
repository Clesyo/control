package com.app.control.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.models.Order;
import com.app.control.service.OrderService;

@RestController
@RequestMapping(path = "/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public List<Order> all() {
		return orderService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(Long id) {
		return ResponseEntity.ok(orderService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Order> store(Order order) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> edit(@PathVariable(name = "id") Long id,@RequestBody Order order) {
		return ResponseEntity.ok(orderService.update(id, order));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		orderService.destroy(id);
	}
}
