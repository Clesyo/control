package com.app.control.api.models.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private LocalDateTime dateTime;
	private String status;
	private String observation;
	private Double delivery;
	private Double total;
	private Long payment;
	private Long contact;
	private Long company;
	
	private List<OrderItemDTO> items;
}
