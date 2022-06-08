package com.lawencon.user.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChangePasswordDtoReq {

	@NotNull(message = "ID cant null")
	private Long id;

	@NotEmpty(message = "Passwrod can't empty")
	private String password;

	@NotNull(message = "Version cant null")
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
