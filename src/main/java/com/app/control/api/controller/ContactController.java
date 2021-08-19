package com.app.control.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.app.control.api.models.Address;
import com.app.control.api.models.Contact;
import com.app.control.api.models.dto.AddressDTO;
import com.app.control.api.models.dto.ContactDTO;
import com.app.control.api.service.AddressService;
import com.app.control.api.service.ContactService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/contato", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;
	private final AddressService addressService;

	@GetMapping
	@ApiOperation("Lista todos os contatos")
	public List<Contact> all() {
		return contactService.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um contato pelo seu código ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Contato encontrado"),
			@ApiResponse(code = 404, message = "Contato não encontrado") })
	public ContactDTO findById(@PathVariable Long id) {
		return ContactDTO.convertToDto(contactService.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um contato")
	@ApiResponses({ @ApiResponse(code = 201, message = "Contato salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public ContactDTO store(@RequestBody @Valid ContactDTO contato) {
		Contact contact = contactService.create(
				Contact.builder().name(contato.getName()).email(contato.getEmail()).cell(contato.getCell()).build());

		if (!contato.getAddresses().isEmpty()) {
			List<Address> addresss = saveAddress(contact, contato.getAddresses());
			contact.setAddresses(addresss);
		}

		return ContactDTO.convertToDto(contact);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um contato")
	@ApiResponses({ @ApiResponse(code = 201, message = "Contato alterado com sucesso"),
			@ApiResponse(code = 404, message = "Contato não encontrado"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public ContactDTO edit(@PathVariable Long id, @RequestBody @Valid ContactDTO contato) {
		Contact contact = contactService.create(
				Contact.builder().name(contato.getName()).email(contato.getEmail()).cell(contato.getCell()).build());
		return ContactDTO.convertToDto(contactService.update(id, contact));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 201, message = "Contato deletado com sucesso"),
			@ApiResponse(code = 404, message = "Contato não encontrado")})
	public void delete(@PathVariable Long id) {
		contactService.destroy(id);
	}

	private List<Address> saveAddress(Contact contact, List<AddressDTO> addresses) {
		List<Address> endereco = new ArrayList<Address>();
		for (AddressDTO dto : addresses) {

			Address address = addressService.create(Address.builder().andress(dto.getAndress())
					.numberHouse(dto.getNumberHouse()).complement(dto.getComplement()).district(dto.getDistrict())
					.city(dto.getCity()).zipCode(dto.getZipCode()).uf(dto.getUf()).contact(contact).build());
			endereco.add(address);
		}
		return endereco;
	}
}
