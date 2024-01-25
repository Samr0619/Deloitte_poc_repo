package com.dk.smart.shop.user.services;

import org.springframework.stereotype.Service;

import com.dk.smart.shop.user.dto.AuthResponseDTO;
import com.dk.smart.shop.user.dto.LoginRequestDTO;
import com.dk.smart.shop.user.dto.RegisterRequestDTO;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
@Service
public interface UserService {

	AuthResponseDTO registerUser(RegisterRequestDTO registerRequest);
	
	AuthResponseDTO login(LoginRequestDTO loginRequest);
}