package com.app.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.control.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
