package com.lawencon.user.dto.role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateRoleDtoReq {

	@NotNull(message = "ID cant null")
	private String id;
	
	@NotEmpty(message = "Role code can't empty")
	private String roleName;
	
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
