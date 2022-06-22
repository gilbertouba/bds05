package com.devsuperior.movieflix.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDto;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repository.RoleRepository;
import com.devsuperior.movieflix.repository.UserRepository;
import com.devsuperior.movieflix.service.exceptions.ForbiddenException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired	
	private UserRepository userRepository;

	@Autowired	
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			logger.error("Usuário não encontratdo "+username); 
			throw new UsernameNotFoundException("Email não encontrado.");
		}
		logger.info("Usuário encontratdo "+username);
		return user;
	}
	
	@Transactional(readOnly=true)
	public UserDto loadUserByUsername() {
		User user = authService.authenticated();
		
		if (user==null) {
			throw new ForbiddenException("Access denied");
		}
		
		return new UserDto(user);
	}


}
