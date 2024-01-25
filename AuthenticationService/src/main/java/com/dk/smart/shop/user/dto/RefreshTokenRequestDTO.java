package com.dk.smart.shop.user.dto;



public class RefreshTokenRequestDTO {
	
	private String refreshToken;
	

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	public RefreshTokenRequestDTO() {
		
	}
	
	public RefreshTokenRequestDTO(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	

	@Override
	public String toString() {
		return "RefreshTokenRequestDTO [refreshToken=" + refreshToken + "]";
	}
}
