package com.dk.smart.shop.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dk.smart.shop.user.dto.AuthResponseDTO;
import com.dk.smart.shop.user.dto.LoginRequestDTO;
import com.dk.smart.shop.user.dto.RegisterRequestDTO;
import com.dk.smart.shop.user.services.UserService;

@RestController
@RequestMapping("/user")
public class RegisterAndLoginController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> createUser(@RequestBody RegisterRequestDTO registerRequest){
		
		AuthResponseDTO authResponse = userService.registerUser(registerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest){
		
		AuthResponseDTO authResponse = userService.login(loginRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
	}

}
