package com.lawencon.user.dto.role;

import java.util.List;

public class GetRolesDtoRes {

	private List<RoleDataDtoRes> data;

	public List<RoleDataDtoRes> getData() {
		return data;
	}

	public void setData(List<RoleDataDtoRes> data) {
		this.data = data;
	}
}