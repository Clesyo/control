package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.app.control.api.models.Order;
import com.app.control.api.models.Product;
import com.app.control.api.models.dto.OrderDTO;
import com.app.control.api.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	@ApiOperation("Busca todos os pedidos")
	public List<Order> all() {
		return orderService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um pedido pelo seu codifo ID")
	@ApiResponse(code = 404, message = "Pedido não encontrado para ID informado")
	public Order findById(@PathVariable Long id) {
		return orderService.findById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@ApiOperation("Salva um pedido")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido incluido com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public Long store(@RequestBody @Valid OrderDTO pedido) {
		Order order = orderService.create(pedido);
		return order.getId();
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido incluido com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Pedido não encontrado para o ID informado") })
	public Order edit(@PathVariable Long id, @RequestBody @Valid Order order) {
		return orderService.update(id, order);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponse(code = 404, message = "Pedido não encontrado para o ID informado")
	public void delete(@PathVariable Long id) {
		orderService.destroy(id);
	}
}
