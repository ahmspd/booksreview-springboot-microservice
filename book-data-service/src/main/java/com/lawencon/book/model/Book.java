package com.lawencon.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_book")
public class Book extends BaseModel {
	@Column(name = "title", length = 100, nullable = false)
	private String title;

	@Column(name = "year_published")
	private Integer yearPublished;

	@Column(name = "author", length = 100, nullable = false)
	private String author;

	@Column(name = "publisher", length = 100)
	private String publisher;

	@Column(nullable = false, columnDefinition = "text")
	private String synopsis;

	@Column(length = 100)
	private String isbn;

	@Column(name = "num_pages")
	private Integer numPages;

	@Column(name = "book_language", length = 100)
	private String bookLanguage;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public String getBookLanguage() {
		return bookLanguage;
	}

	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

}
