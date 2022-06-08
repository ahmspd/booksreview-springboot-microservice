package com.lawencon.books.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.books.constant.ResponseMessageType;
import com.lawencon.books.dao.ReviewDao;
import com.lawencon.books.dto.DeleteDtoRes;
import com.lawencon.books.dto.InsertDataDtoRes;
import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.review.GetReviewDtoData;
import com.lawencon.books.dto.review.GetReviewsDtoRes;
import com.lawencon.books.dto.review.InsertReviewDtoReq;
import com.lawencon.books.model.Review;
import com.lawencon.books.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;
	
	@Override
	public DeleteDtoRes deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertReviewDtoReq data) {
		restTemplate.getForObject("http://BOOK-DATA-SERVICE/book/data/books/"+data.getIdBook(), Object.class);
		restTemplate.getForObject("http://USER-SERVICE/users/"+data.getCreatedBy(), Object.class);
		
		Review review = new Review();
		review.setBookComment(data.getBookComment());
		review.setCreatedBy(data.getCreatedBy());
		review.setIdBook(data.getIdBook());
		review.setRating(data.getRating());
		
		Review reviewData = reviewDao.insert(review);
		
		InsertDataDtoRes resData = new InsertDataDtoRes();
		resData.setId(reviewData.getId());
		
		InsertDtoRes response = new InsertDtoRes();
		response.setData(resData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());
		return response;
	}

	@Override
	public GetReviewsDtoRes getByIdBook(String idBook) {
		List<GetReviewDtoData> result = reviewDao.getReviewByIdBook(idBook);
		
		GetReviewsDtoRes response = new GetReviewsDtoRes();
		response.setData(result);
		
		return response;
	}
	
	@Override
	public BigDecimal getAvgRating(String idBook) {
		BigDecimal result = reviewDao.getAvgRarting(idBook);
		return result;
	}
	
	@Override
	public BigInteger getCountReview(String idBook) {
		BigInteger result = reviewDao.getCountReview(idBook);
		return result;
	}
}
