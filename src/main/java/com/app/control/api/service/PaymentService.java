package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Payment;
import com.app.control.api.repository.PaymentRepository;

@Service
public class PaymentService {

	private PaymentRepository paymentRepository;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	public Payment findById(Long id) {
		return findOrfail(id);
	}
	
	public Payment create(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public Payment update(Long id, Payment payment) {
		Payment p = findOrfail(id);
		BeanUtils.copyProperties(payment, p);
		return paymentRepository.save(p);
	}
	
	public void destroy(Long id) {
		Payment p = findOrfail(id);
		paymentRepository.delete(p);
	}
	
	private Payment findOrfail(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrada."));
	}

}
