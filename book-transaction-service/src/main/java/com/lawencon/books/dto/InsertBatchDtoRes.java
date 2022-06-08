package com.lawencon.books.dto;

import java.util.List;

public class InsertBatchDtoRes {

	private String message;
	private List<InsertDataDtoRes> data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<InsertDataDtoRes> getData() {
		return data;
	}

	public void setData(List<InsertDataDtoRes> data) {
		this.data = data;
	}

}
