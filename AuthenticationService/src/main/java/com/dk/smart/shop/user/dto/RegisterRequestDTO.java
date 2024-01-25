package com.dk.smart.shop.user.dto;



public class RegisterRequestDTO {
	
	private String name;
	
	private String email;
	
	private String userName;
	
	private String password;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public RegisterRequestDTO() {
		
	}
	
	public RegisterRequestDTO(String name, String email, String userName, String password) {
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterRequestDTO [name=" + name + ", email=" + email + ", userName=" + userName + ", password="
				+ password + "]";
	}
}
