package com.lawencon.book.dto.genre;

import java.util.List;

public class GetGenresDtoRes {
	private List<GenreDataDtoRes> data;

	public List<GenreDataDtoRes> getData() {
		return data;
	}

	public void setData(List<GenreDataDtoRes> data) {
		this.data = data;
	}
}
