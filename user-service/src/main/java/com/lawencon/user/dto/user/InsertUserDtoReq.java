package com.lawencon.user.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InsertUserDtoReq {

	@NotEmpty(message = "Email can't empty")
	private String email;

	@NotEmpty(message = "Passwrod can't empty")
	private String password;

	@NotNull(message = "Role ID can't null")
	private String roleId;
	
	@NotNull(message = "Profile Name can't null")
	private String profileName;
	
	@NotNull(message = "Profile Phone can't null")
	private String profilePhone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}
	
}
