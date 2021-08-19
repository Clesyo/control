package com.app.control.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.models.Address;
import com.app.control.api.models.Contact;
import com.app.control.api.models.dto.AddressDTO;
import com.app.control.api.service.AddressService;
import com.app.control.api.service.ContactService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(params = "/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;
	private final ContactService contactService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um endereço")
	@ApiResponses({ @ApiResponse(code = 201, message = "Endereço salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public AddressDTO create(@RequestBody @Valid AddressDTO endereco) {
		Contact contato = contactService.findById(endereco.getContact());
		Address address = Address.builder().zipCode(endereco.getZipCode()).andress(endereco.getAndress())
				.numberHouse(endereco.getNumberHouse()).complement(endereco.getComplement())
				.district(endereco.getDistrict()).city(endereco.getCity()).uf(endereco.getUf()).contact(contato)
				.build();
		return AddressDTO.convertToDto(addressService.create(address));
	}
}
