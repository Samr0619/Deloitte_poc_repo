package com.dk.smart.shop.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dk.smart.shop.user.dto.AuthResponseDTO;
import com.dk.smart.shop.user.dto.RefreshTokenRequestDTO;
import com.dk.smart.shop.user.entities.RefreshToken;
import com.dk.smart.shop.user.entities.User;
import com.dk.smart.shop.user.exception.handler.AuthorizationException;
import com.dk.smart.shop.user.security.JWTService;
import com.dk.smart.shop.user.security.RefreshTokenService;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/smart/shop/user")
public class UserController {

	@Autowired
	private RefreshTokenService refreshTokenservice;
	
	@Autowired
	private JWTService jwtService;
//	@Autowired
//	private JwtHelper jwtService;	

	@PostMapping("/refresh/token")
	public ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequest){
		
		RefreshToken refreshToken = refreshTokenservice.createRefreshToken(refreshTokenRequest.getRefreshToken());
		User user = refreshToken.getUser();
		
		String accessToken = jwtService.generateToken(user);

		AuthResponseDTO authResponse = new AuthResponseDTO(accessToken, refreshToken.getRefreshToken());
		return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
	}
	
	@GetMapping("/test")
	public String testJWT() {
		return "Successfully authenticated and authorized";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/test/admin")
	public String testAdminJWT() {
		try {
			return "Successfully authenticated and authorized as admin";
		}
		catch (RuntimeException e) {
			new AuthorizationException(e.getMessage());
		}
		return null;
	}
	
	
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam String token) {
		System.out.println("/Validate Called");
		jwtService.validate(token);
		return "SUCCESS";
	}
}