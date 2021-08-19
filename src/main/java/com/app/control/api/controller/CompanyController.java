package com.app.control.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.configuration.security.auth.jwt.HttpServletRequestCustom;
import com.app.control.api.configuration.security.auth.jwt.JwtService;
import com.app.control.api.models.Company;
import com.app.control.api.models.User;
import com.app.control.api.models.dto.CompanyDTO;
import com.app.control.api.repository.CompanyRepository;
import com.app.control.api.service.CompanyService;
import com.app.control.api.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/empresa", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyController {

	private final CompanyService companyService;
	private final UserService userService;
	// private final JwtService jwtService;

	@GetMapping
	public List<Company> all() {
		return companyService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(companyService.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva uma empresa")
	@ApiResponses({ @ApiResponse(code = 201, message = "Empresa salva com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação."), })
	public CompanyDTO store(@RequestBody @Valid CompanyDTO empresa) {
		User user = userService.findById(empresa.getUser());
		String token = UUID.randomUUID().toString().toUpperCase();
		Company companyBuilder = Company.builder().name(empresa.getName()).email(empresa.getEmail())
				.identity(empresa.getIdentity()).telephone(empresa.getTelephone()).cellphone(empresa.getCellphone())
				.andress(empresa.getAndress()).zipCode(empresa.getZipCode()).numberHouse(empresa.getNumberHouse())
				.complement(empresa.getComplement()).district(empresa.getDistrict()).city(empresa.getCity())
				.uf(empresa.getUf()).user(user).token(token).build();
		Company company = companyService.create(companyBuilder);
		return CompanyDTO.convertToDto(company);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera uma empresa")
	@ApiResponses({ @ApiResponse(code = 204, message = "Empresa alterada com sucesso."),
			@ApiResponse(code = 404, message = "Empresa não encontrada.") })
	public CompanyDTO edit(@PathVariable  Long id, @RequestBody @Valid CompanyDTO empresa) {
		User user = userService.findById(empresa.getUser());
		Company companyBuilder = Company.builder().name(empresa.getName()).email(empresa.getEmail())
				.identity(empresa.getIdentity()).telephone(empresa.getTelephone()).cellphone(empresa.getCellphone())
				.andress(empresa.getAndress()).zipCode(empresa.getZipCode()).numberHouse(empresa.getNumberHouse())
				.complement(empresa.getComplement()).district(empresa.getDistrict()).city(empresa.getCity())
				.uf(empresa.getUf()).user(user).token(empresa.getToken()).build();
		return CompanyDTO.convertToDto(companyService.update(id, companyBuilder));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta uma empresa")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresa deletada com sucesso."),
		@ApiResponse(code = 404, message = "Empresa não encontrada.")
	})
	public void delete(@PathVariable(name = "id") Long id) {
		companyService.destroy(id);
	}
}
