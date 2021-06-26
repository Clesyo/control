package com.app.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.models.User;
import com.app.control.repository.UserRepository;

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
		BeanUtils.copyProperties(user, u);
		return userRepository.save(u);
	}

	public void destroy(Long id) {
		User user = findOrFail(id);
		userRepository.delete(user);
	}

	private User findOrFail(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist(getClass().getName() + " não encontrado."));
	}
}
