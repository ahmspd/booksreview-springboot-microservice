package com.lawencon.book.dto.book;

import java.util.List;

public class GetBooksDtoRes {
	private List<BookDataDtoRes> data;

	public List<BookDataDtoRes> getData() {
		return data;
	}

	public void setData(List<BookDataDtoRes> data) {
		this.data = data;
	}
}
