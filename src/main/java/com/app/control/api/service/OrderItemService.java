package com.app.control.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.control.api.models.OrderItem;
import com.app.control.api.repository.OrderItemRepository;

@Service
public class OrderItemService {

	private OrderItemRepository itemRepository;
	

	public OrderItem create(OrderItem item) {
		return itemRepository.save(item);
	}
	
	public List<OrderItem> createAll(List<OrderItem> items){
		return itemRepository.saveAll(items);
	}
}
