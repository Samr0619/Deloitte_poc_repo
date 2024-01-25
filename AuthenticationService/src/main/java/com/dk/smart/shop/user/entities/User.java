package com.dk.smart.shop.user.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
//@JsonPropertyOrder(
//	{
//		"user_name",
//		"password",
//		"first_name",
//		"last_name",
//		"email",
//		"moblie_number",
//		"role"
//	}
//)
@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "user")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@jakarta.persistence.Column(unique  =true)
	@jakarta.validation.constraints.Email
	private String email;
	
	private String userName;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@OneToOne(mappedBy = "user")
	private RefreshToken refreshToken;
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	
	
	public RefreshToken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(RefreshToken refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public User() {
		
	}

	public User(String name, @Email String email, String userName, String password, UserRole role) {
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", userName=" + userName + ", password="
				+ password + ", role=" + role + ", refreshToken=" + refreshToken + "]";
	}

	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return email;  // expecting unique mail for each user account.
	}

}