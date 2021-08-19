package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.models.Payment;
import com.app.control.api.models.dto.PaymentDTO;
import com.app.control.api.service.PaymentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@GetMapping
	@ApiOperation("Busca todas as formas de pagamento")
	public List<Payment> all() {
		return paymentService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca uma forma de pagamento pelo codigo ID")
	public PaymentDTO findById(@PathVariable Long id) {
		return PaymentDTO.conertToDto(paymentService.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva uma forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 201, message = "Forma de pagamento salva com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public PaymentDTO store(@RequestBody @Valid PaymentDTO pagamento) {
		Payment payment = Payment.builder().name(pagamento.getName()).actived(pagamento.isActived()).build();
		return PaymentDTO.conertToDto(payment);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera uma forma de pagamento")
	@ApiResponses({ @ApiResponse(code = 201, message = "Forma de pagamento alterada com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Forma de pagamento não encontrada") })
	public PaymentDTO edit(@PathVariable Long id, @RequestBody @Valid PaymentDTO pagamento) {
		Payment payment = Payment.builder().name(pagamento.getName()).actived(pagamento.isActived()).build();
		return PaymentDTO.conertToDto(payment);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 201, message = "Forma de pagamento deletada com sucesso"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada") })
	public void delete(@PathVariable Long id) {
		paymentService.destroy(id);
	}
}
