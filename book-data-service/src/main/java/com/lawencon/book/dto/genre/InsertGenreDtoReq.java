package com.lawencon.book.dto.genre;

import javax.validation.constraints.NotEmpty;

public class InsertGenreDtoReq {

	@NotEmpty(message = "Genre Code can't empty")
	private String genreCode;

	@NotEmpty(message = "Genre Name can't empyt")
	private String genreName;
	
	@NotEmpty(message = "Created By can't empty")
	private String createdBy;

	public String getGenreCode() {
		return genreCode;
	}

	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
