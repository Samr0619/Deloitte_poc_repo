package com.dk.smart.shop.user.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;

	private String refreshToken;

	@Column(nullable = false)
	private Instant expirationTime;

	@OneToOne
	private User user;

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Instant getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Instant expirationTime) {
		this.expirationTime = expirationTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public RefreshToken() {
		
	}

	public RefreshToken(Integer tokenId, String refreshToken, Instant expirationTime, User user) {
		this.tokenId = tokenId;
		this.refreshToken = refreshToken;
		this.expirationTime = expirationTime;
		this.user = user;
	}

	public RefreshToken(String refreshToken, Instant expirationTime, User user) {
		this.refreshToken = refreshToken;
		this.expirationTime = expirationTime;
		this.user = user;
	}

	@Override
	public String toString() {
		return "RefreshToken [tokenId=" + tokenId + ", refreshToken=" + refreshToken + ", expirationTime="
				+ expirationTime + ", user=" + user + "]";
	}

}