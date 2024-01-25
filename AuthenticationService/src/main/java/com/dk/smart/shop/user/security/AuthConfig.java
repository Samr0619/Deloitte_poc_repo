package com.dk.smart.shop.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dk.smart.shop.user.repositories.UserRepository;


@Configuration
public class AuthConfig {

	private final UserRepository userRepository;

	public AuthConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public UserDetailsService getUserDetails() {
		return userName -> userRepository.findByEmail(userName)
				.orElseThrow(() -> new RuntimeException("User Not Found with name : " + userName));
	}

//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(getUserDetails());
//		authProvider.setPasswordEncoder(passwordEncoder());
//		return authProvider;
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
