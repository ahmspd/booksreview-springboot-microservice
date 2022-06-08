package com.lawencon.book.dto.book;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.lawencon.book.model.Genre;

public class BookDataDtoRes {
	private String id;
	private String title;
	private BigDecimal rating;
	private String createdBy;
	private Integer yearPublished;
	private String author;
	private String publisher;
	private String synopsis;
	private String isbn;
	private Integer numPages;
	private String bookLanguage;
	private BigInteger reviewCount;
	private BigInteger bookmarkCount;
	private List<Genre> genreData;

	public BigInteger getBookmarkCount() {
		return bookmarkCount;
	}

	public void setBookmarkCount(BigInteger bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}

	public BigInteger getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(BigInteger reviewCount) {
		this.reviewCount = reviewCount;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public List<Genre> getGenreData() {
		return genreData;
	}

	public void setGenreData(List<Genre> genreData) {
		this.genreData = genreData;
	}

}
