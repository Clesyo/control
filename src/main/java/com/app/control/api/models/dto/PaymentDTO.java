package com.app.control.api.models.dto;

import com.app.control.api.models.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentDTO {

	private Long id;
	private String name;
	private boolean actived;
	
	public static PaymentDTO conertToDto(Payment payment) {
		return new PaymentDTO(payment.getId(), payment.getName(), true);
	}
}
