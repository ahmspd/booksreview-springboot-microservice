package com.lawencon.book.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_genre_book")
public class GenreBook extends BaseModel{

	@ManyToOne
	@JoinColumn(name = "id_book", referencedColumnName = "id", nullable = false)
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "id_genre", referencedColumnName = "id", nullable = false)
	private Genre genre;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
}
