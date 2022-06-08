package com.lawencon.books.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.review.GetReviewsDtoRes;
import com.lawencon.books.dto.review.InsertReviewDtoReq;

public interface ReviewService extends BaseMasterService{
	InsertDtoRes insert(InsertReviewDtoReq data);
	
	GetReviewsDtoRes getByIdBook(String idBook);
	
	BigDecimal getAvgRating(String idBook);
	
	BigInteger getCountReview(String idBook);
}
