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

import com.app.control.api.models.Menu;
import com.app.control.api.service.MenuService;

@RestController
@RequestMapping(path = "/cardapio", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

	private MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	public List<Menu> all() {
		return menuService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Menu> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(menuService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Menu> save(Menu menu) {
		return ResponseEntity.status(HttpStatus.CREATED).body(menuService.create(menu));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Menu> update(@PathVariable(name = "id") Long id, @RequestBody Menu menu) {
		return ResponseEntity.ok(menuService.update(id, menu));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		menuService.destroy(id);
	}
}
