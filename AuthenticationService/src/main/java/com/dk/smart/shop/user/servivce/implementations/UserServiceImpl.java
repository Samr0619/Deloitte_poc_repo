package com.dk.smart.shop.user.servivce.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dk.smart.shop.user.dto.AuthResponseDTO;
import com.dk.smart.shop.user.dto.LoginRequestDTO;
import com.dk.smart.shop.user.dto.RegisterRequestDTO;
import com.dk.smart.shop.user.entities.RefreshToken;
import com.dk.smart.shop.user.entities.User;
import com.dk.smart.shop.user.entities.UserRole;
import com.dk.smart.shop.user.repositories.UserRepository;
import com.dk.smart.shop.user.security.JWTService;
import com.dk.smart.shop.user.security.RefreshTokenService;
import com.dk.smart.shop.user.services.UserService;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
@Service
//@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	private final AuthenticationManager authenticationManager;
	
	public UserServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	

	@Override
	public AuthResponseDTO registerUser(RegisterRequestDTO registerRequest) {
		
		User registeredUser = new User(registerRequest.getName(), registerRequest.getEmail(), 
				registerRequest.getUserName(), passwordEncoder.encode(registerRequest.getPassword()), UserRole.USER);
		
		User savedUser = userRepository.save(registeredUser);
		String accessToken = jwtService.generateToken(savedUser);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());
		
		return new AuthResponseDTO(accessToken, refreshToken.getRefreshToken());
	}
	
	@Override
	public AuthResponseDTO login(LoginRequestDTO loginRequest) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
							loginRequest.getEmail(),
							loginRequest.getPassword()
						)
				);
		
		User user = userRepository.findByEmail(loginRequest.getEmail()).get();
		String accessToken = jwtService.generateToken(user);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getEmail());
		
		return  new AuthResponseDTO(accessToken, refreshToken.getRefreshToken());
	}
	

}
