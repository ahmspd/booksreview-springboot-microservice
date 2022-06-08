package com.lawencon.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_review")
public class Review extends BaseModel {
	@Column(nullable = false)
	private Integer rating;

	@Column(name = "id_book", nullable = false)
	private String idBook;

	@Column(name = "book_comment", columnDefinition = "text")
	private String bookComment;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getIdBook() {
		return idBook;
	}

	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}

	public String getBookComment() {
		return bookComment;
	}

	public void setBookComment(String bookComment) {
		this.bookComment = bookComment;
	}

}
