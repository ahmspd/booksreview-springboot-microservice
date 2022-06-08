package com.lawencon.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_genre")
public class Genre extends BaseModel {
	@Column(name = "genre_code", length = 5, unique = true, nullable = false)
	private String genreCode;

	@Column(name = "genre_name", length = 100, nullable = false)
	private String genreName;

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
	
}
