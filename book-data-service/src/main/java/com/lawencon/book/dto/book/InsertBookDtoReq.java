package com.lawencon.book.dto.book;

import java.util.List;

import com.lawencon.book.dto.genre.GenreDataDtoRes;

public class InsertBookDtoReq {
	private String createdBy;
	private String title;
	private Integer yearPublished;
	private String author;
	private String publisher;
	private String synopsis;
	private String isbn;
	private Integer numPages;
	private String bookLanguage;
	private List<GenreDataDtoRes> genreData;

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

	public List<GenreDataDtoRes> getGenreData() {
		return genreData;
	}

	public void setGenreData(List<GenreDataDtoRes> genreData) {
		this.genreData = genreData;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
}
