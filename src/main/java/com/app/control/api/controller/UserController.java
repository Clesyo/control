package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.control.api.configuration.security.auth.ApiUserDetailsService;
import com.app.control.api.configuration.security.auth.jwt.HttpServletRequestCustom;
import com.app.control.api.configuration.security.auth.jwt.JwtService;
import com.app.control.api.exception.PasswordInvalidException;
import com.app.control.api.models.User;
import com.app.control.api.models.dto.CredentialDTO;
import com.app.control.api.models.dto.TokenDTO;
import com.app.control.api.models.dto.UserDTO;
import com.app.control.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Api("Api de usuário")
public class UserController {

	private final UserService userService;
	private final PasswordEncoder encoder;
	private final ApiUserDetailsService userDatailService;
	private final JwtService jwtService;

	@GetMapping
	public List<User> show() {
		return userService.all();
	}

	@GetMapping("/{id}")
	@ApiOperation("Obter dados de um usuário pelo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário encontrado"),
			@ApiResponse(code = 404, message = "Usuário não encontrado para ID informado."), })
	public User findById(@PathVariable @ApiParam("Id do usuário") Long id) {
		return userService.findById(id);
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um usuário")
	@ApiResponses({ @ApiResponse(code = 201, message = "Usuário salvo com sucesso."),
			@ApiResponse(code = 400, message = "Erro de validação."), })
	public UserDTO store(@RequestBody @Valid User user) {
		String passwordEncrypted = encoder.encode(user.getPassword());
		user.setPassword(passwordEncrypted);
		return UserDTO.convertToDto(userService.create(user));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public User edit(@PathVariable @ApiParam("Id de usuário") Long id, @RequestBody User user) {
		return userService.update(id, user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.destroy(id);
	}

	@PostMapping("/auth")
	public TokenDTO authentication(@RequestBody CredentialDTO credential) {
		try {
			User user = User.builder().email(credential.getLogin()).password(credential.getPassword()).build();
			UserDetails UserAuthenticate = userDatailService.authenticate(user);
			String token = jwtService.generateToken(user);
			jwtService.obtainTokenValid(new HttpServletRequestCustom());
			return new TokenDTO(user.getEmail(), token);
		} catch (UsernameNotFoundException | PasswordInvalidException e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
}
