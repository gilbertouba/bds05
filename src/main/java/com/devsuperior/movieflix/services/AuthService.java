package com.devsuperior.movieflix.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repository.UserRepository;
import com.devsuperior.movieflix.service.exceptions.ForbiddenException;
import com.devsuperior.movieflix.service.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly=true)
	public User authenticated() {
		try	{
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return repository.findByEmail(username);
		}
		catch (Exception e){
			throw new UnauthorizedException("Invalid User");
		}
		
	}
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if (! user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied ");
	}
		
		
	}
	

}
