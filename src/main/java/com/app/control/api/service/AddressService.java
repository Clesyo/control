package com.app.control.api.service;

import org.springframework.stereotype.Service;

import com.app.control.api.models.Address;
import com.app.control.api.repository.AddressRepository;

@Service
public class AddressService {

	private AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	
	public Address create(Address address) {
		
		return addressRepository.save(address);
	}
	
	
}
