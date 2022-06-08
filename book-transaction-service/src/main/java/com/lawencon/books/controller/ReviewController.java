package com.lawencon.books.controller;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.review.GetReviewsDtoRes;
import com.lawencon.books.dto.review.InsertReviewDtoReq;
import com.lawencon.books.service.ReviewService;

@RestController
@RequestMapping("book/transaction/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> save(@RequestBody InsertReviewDtoReq data) throws Exception {
		InsertDtoRes datas = reviewService.insert(data);
		return new ResponseEntity<InsertDtoRes>(datas, HttpStatus.CREATED);
	}
	
	@GetMapping("book/{idBook}")
	public ResponseEntity<GetReviewsDtoRes> getByIdBook(@PathVariable String idBook) throws Exception {
		GetReviewsDtoRes datas = reviewService.getByIdBook(idBook);
		return new ResponseEntity<GetReviewsDtoRes>(datas, HttpStatus.OK);
	}
	
	@GetMapping("avg/{idBook}")
	public ResponseEntity<BigDecimal> getAvgRating(@PathVariable String idBook) throws Exception {
		BigDecimal result = reviewService.getAvgRating(idBook);
		return new ResponseEntity<BigDecimal>(result, HttpStatus.OK);
	}
	
	@GetMapping("count/{idBook}")
	public ResponseEntity<BigInteger> getCountReview(@PathVariable String idBook) throws Exception {
		BigInteger result = reviewService.getCountReview(idBook);
		return new ResponseEntity<BigInteger>(result, HttpStatus.OK);
	}
}
