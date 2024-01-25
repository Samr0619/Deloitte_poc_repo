package com.dk.smart.shop.user.dto;



public class AuthResponseDTO {
	

	private String accessToken;

    private String refreshToken;

    

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	public AuthResponseDTO() {
		
	}

	public AuthResponseDTO(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "AuthResponseDTO [accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}
}
