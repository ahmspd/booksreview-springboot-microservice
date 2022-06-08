package com.lawencon.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_bookmark")
public class Bookmark extends BaseModel {
	@Column(name = "id_book", nullable = false)
	private String idBook;

	public String getIdBook() {
		return idBook;
	}

	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

}
