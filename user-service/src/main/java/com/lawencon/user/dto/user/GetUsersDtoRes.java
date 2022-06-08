package com.lawencon.user.dto.user;

import java.util.List;

public class GetUsersDtoRes {

	private List<UserDataDtoRes> data;

	public List<UserDataDtoRes> getData() {
		return data;
	}

	public void setData(List<UserDataDtoRes> data) {
		this.data = data;
	}
}
