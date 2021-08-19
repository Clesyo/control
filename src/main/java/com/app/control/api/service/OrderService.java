package com.app.control.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Company;
import com.app.control.api.models.Contact;
import com.app.control.api.models.Order;
import com.app.control.api.models.OrderItem;
import com.app.control.api.models.Payment;
import com.app.control.api.models.Product;
import com.app.control.api.models.dto.OrderDTO;
import com.app.control.api.models.dto.OrderItemDTO;
import com.app.control.api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final CompanyService companyService;
	private final PaymentService paymentService;
	private final ContactService contactService;
	private final ProductService productService;
	private final OrderItemService itemService;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		return findOrFail(id);
	}

	public Order create(OrderDTO dto) {
		Company company = companyService.findById(dto.getCompany());
		Contact contact = contactService.findById(dto.getContact());
		Payment payment = paymentService.findById(dto.getPayment());
		Order orderBuilder = Order.builder().dateTime(LocalDateTime.now()).delivery(dto.getDelivery())
				.observation(dto.getObservation()).status(dto.getStatus()).total(dto.getTotal()).company(company)
				.contact(contact).payment(payment).build();
		
		Order order = orderRepository.save(orderBuilder);
		List<OrderItem> listItems = convertItems(order, dto.getItems());
		itemService.createAll(listItems);
		order.setItens(listItems);
		return order;
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
		return orderRepository.findById(id).orElseThrow(() -> new EntityNotExist("Pedido não encontrado."));
	}

	private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {

		if (items.isEmpty()) {
			throw new EntityNotExist("Não é possivel fazer pedido sem itens.");
		}

		return items.stream().map(dto -> {
			Product product = productService.findById(dto.getProduct());
			return OrderItem.builder().amount(dto.getAmount()).order(order).product(product).build();

		}).collect(Collectors.toList());

	}
}
