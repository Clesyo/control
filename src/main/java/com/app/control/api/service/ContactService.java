package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Address;
import com.app.control.api.models.Contact;
import com.app.control.api.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository contactRepository;

	@Autowired
	public ContactService(ContactRepository orderRepository) {
		this.contactRepository = orderRepository;
	}

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	public Contact findById(Long id) {
		return findOrFail(id);
	}

	public Contact create(Contact contac) {
		return contactRepository.save(contac);
	}
	
	public Contact createWithAddress(Contact contact, List<Address> addresses) {
		Contact c = create(contact);
		c.getAddresses().addAll(addresses);
		return contactRepository.save(c);
	}
	
	public Contact update(Long id, Contact contact ) {
		Contact c = findOrFail(id);
		BeanUtils.copyProperties(contact, c);
		return contactRepository.save(contact);
	}
	
	public void destroy(Long id) {
		Contact c = findOrFail(id);
		contactRepository.delete(c);
	}
	
	private Contact findOrFail(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrado."));
	}
}
