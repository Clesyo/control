package com.app.control.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.User;
import com.app.control.api.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> all() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return findOrFail(id);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(Long id, User user) {
		User u = findOrFail(id);
		BeanUtils.copyProperties(user, u, "id");
		return userRepository.save(u);
	}

	public void destroy(Long id) {
		User user = findOrFail(id);
		userRepository.delete(user);
	}

	public User findByEmail(String username) {
		return userRepository.findByEmail(username).orElseThrow(() -> new EntityNotExist("Usuário não encontrado."));
	}
	
	private User findOrFail(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist("Usuário não encontrado."));
	}
	
	
}
