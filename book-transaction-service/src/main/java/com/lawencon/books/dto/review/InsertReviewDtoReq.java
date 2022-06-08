package com.lawencon.books.dto.review;

public class InsertReviewDtoReq {
	private String createdBy;
	private String idBook;
	private Integer rating;
	private String bookComment;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIdBook() {
		return idBook;
	}
	public void setIdBook(String idBook) {
		this.idBook = idBook;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getBookComment() {
		return bookComment;
	}
	public void setBookComment(String bookComment) {
		this.bookComment = bookComment;
	}
}
