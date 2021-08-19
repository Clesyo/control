package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.api.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
