package com.devsuperior.movieflix.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value ="/users")
public class UserController {

	@Autowired
	private UserService service;
	
	 @GetMapping(value ="/{id}")
	    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
	       UserDTO dto = service.findById(id);
	       
		   return ResponseEntity.ok().body(dto);
		  
	 }
	 
	 @PostMapping
	    public ResponseEntity<UserDTO> insert (@Valid @RequestBody UserDTO dto) {
	    	UserDTO newDTO = service.insert(dto);
	    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	               .buildAndExpand(newDTO.getId()).toUri();
	    	return ResponseEntity.created(uri).body(newDTO);
	    }
}
		
	
