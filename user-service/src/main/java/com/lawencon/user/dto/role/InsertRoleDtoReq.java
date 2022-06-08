package com.lawencon.user.dto.role;

import javax.validation.constraints.NotEmpty;

public class InsertRoleDtoReq {

	@NotEmpty(message = "Role code can't empty")
	private String roleCode;
	
	@NotEmpty(message = "Role name can't empty")
	private String roleName;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
