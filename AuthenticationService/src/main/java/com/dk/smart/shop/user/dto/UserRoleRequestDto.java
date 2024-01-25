package com.dk.smart.shop.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author DurgaKalyan
 * @version 1.0
 *
 */
public class UserRoleRequestDto {

	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("role_name")
	private String roleName;

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	public UserRoleRequestDto() {
		
	}
	
	public UserRoleRequestDto(String userName, String roleName) {
		this.userName = userName;
		this.roleName = roleName;
	}
	
	
	@Override
	public String toString() {
		return "UserRoleRequestDto [userName=" + userName + ", roleName=" + roleName + "]";
	}

	

}