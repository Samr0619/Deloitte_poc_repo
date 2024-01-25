package com.dk.smart.shop.user.security;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dk.smart.shop.user.entities.RefreshToken;
import com.dk.smart.shop.user.entities.User;
import com.dk.smart.shop.user.repositories.RefreshTokenRepository;
import com.dk.smart.shop.user.repositories.UserRepository;

@Service
public class RefreshTokenService {
	
	private final UserRepository userRepository;
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	public RefreshTokenService(UserRepository userRepository,  RefreshTokenRepository refreshTokenRepository) {
		this.userRepository = userRepository;
		this.refreshTokenRepository = refreshTokenRepository;
	}
	
	public RefreshToken createRefreshToken(String userName) {
		User user = userRepository.findByEmail(userName)
				.orElseThrow(() -> new RuntimeException("User not found with mail : " + userName));
		
		RefreshToken refreshToken = user.getRefreshToken();
		
		if(refreshToken == null) {
			refreshToken = new RefreshToken(UUID.randomUUID().toString(), Instant.now().plusMillis(5*60*60*10000), user);
			
			refreshTokenRepository.save(refreshToken);
		}
		
		return refreshToken;
	}
	
	public RefreshToken verifyRefreshToken(String refreshToken) {
		RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new RuntimeException("RefreshToken not found!"));
		
		if(refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refToken);
			throw new  RuntimeException("RefreshToken expired!");
		}
				
		return refToken;
	}
}
