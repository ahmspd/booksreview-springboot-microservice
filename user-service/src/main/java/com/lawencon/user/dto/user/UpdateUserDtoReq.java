package com.lawencon.user.dto.user;

import javax.validation.constraints.NotNull;

public class UpdateUserDtoReq {

	@NotNull(message = "ID cant null")
	private String id;

	@NotNull(message = "Role ID can't null")
	private String roleId;

	@NotNull(message = "Version cant null")
	private Integer version;

	@NotNull(message = "Is Active cant null")
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
