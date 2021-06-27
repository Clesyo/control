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

import com.app.control.models.Payment;
import com.app.control.service.PaymentService;

@RestController
@RequestMapping(path = "/pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping
	public List<Payment> all() {
		return paymentService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(paymentService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Payment> store(@RequestBody Payment payment) {
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(payment));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payment> edit(@PathVariable(name = "id") Long id,@RequestBody Payment payment) {
		return ResponseEntity.ok(paymentService.update(id, payment));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		paymentService.destroy(id);
	}
}
