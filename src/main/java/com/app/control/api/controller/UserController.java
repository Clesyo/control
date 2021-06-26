package com.app.control.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.app.control.models.User;
import com.app.control.service.UserService;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> show() {
		return userService.all();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable(name = "id")Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> store(@RequestBody User user ) {
		User u = userService.create(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User user ) {
		return ResponseEntity.ok(userService.update(id, user));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.destroy(id); 
	}
}
