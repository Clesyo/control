package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Menu;
import com.app.control.api.repository.MenuRepository;

@Service
public class MenuService {

	private MenuRepository menuRepository;

	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public List<Menu> findAll() {
		return menuRepository.findAll();
	}

	public Menu findById(Long id) {
		return findOrFail(id);
	}

	public Menu create(Menu menu) {
		return menuRepository.save(menu);
	}
	
	public Menu update(Long id, Menu menu) {
		Menu m = findOrFail(id);
		BeanUtils.copyProperties(menu, m);
		return menuRepository.save(m);
	}
	
	public void destroy(Long id) {
		Menu m = findOrFail(id);
		menuRepository.delete(m);
	}
	
	private Menu findOrFail(Long id) {
		return menuRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrado."));
	}
	
}
