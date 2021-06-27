package com.app.control.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.models.Address;
import com.app.control.models.Contact;
import com.app.control.service.ContactService;

@RestController
@RequestMapping(path = "/contato", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	@GetMapping
	public List<Contact> all() {
		return contactService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(contactService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Contact> store(Contact contact) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
	}
	
	@PostMapping("/endereco")
	public ResponseEntity<Contact> storeWithAddress(Contact contact, List<Address> addresses) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.saveWithAddress(contact,addresses));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity< Contact> edit(@PathVariable(name = "id") Long id,@RequestBody Contact contact) {
		return ResponseEntity.ok(contactService.update(id, contact));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		contactService.destroy(id);
	}
}
