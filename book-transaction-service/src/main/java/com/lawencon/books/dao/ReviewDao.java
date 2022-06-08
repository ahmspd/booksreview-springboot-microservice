package com.lawencon.books.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.lawencon.books.dto.review.GetReviewDtoData;
import com.lawencon.books.model.Review;

public interface ReviewDao extends BaseMasterDao<Review> {
	List<GetReviewDtoData> getReviewByIdBook(String idBook);
	
	BigDecimal getAvgRarting(String idBook);
	
	BigInteger getCountReview(String idBook);
}
