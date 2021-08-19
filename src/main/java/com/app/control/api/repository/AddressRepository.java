package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.control.api.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
