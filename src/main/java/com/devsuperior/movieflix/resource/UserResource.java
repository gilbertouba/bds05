package com.devsuperior.movieflix.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDto;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value ="/users/profile")
public class UserResource {
	
	@Autowired
	private UserService UserService;

	
	@GetMapping
	public ResponseEntity<UserDto> loadUserByUsername(){
		UserDto dto = UserService.loadUserByUsername();		
		return ResponseEntity.ok().body(dto);				
	} 
	

}
