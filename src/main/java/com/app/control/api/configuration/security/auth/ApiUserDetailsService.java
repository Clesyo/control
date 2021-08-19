package com.app.control.api.configuration.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.PasswordInvalidException;
import com.app.control.api.models.User;
import com.app.control.api.service.UserService;

@Service
public class ApiUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;
	
	public UserDetails authenticate(User user) {
		
		UserDetails userLogin = loadUserByUsername(user.getEmail());
		boolean isValidPassword = encoder.matches(user.getPassword(), userLogin.getPassword());
		
		if(isValidPassword) {
			return userLogin;
		}
		throw new PasswordInvalidException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.findByEmail(username);
		String[] roles = user.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };
		return UserCustom.builder().username(user.getEmail()).password(user.getPassword()).roles(roles).build();
	}

}
