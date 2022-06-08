package com.lawencon.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.book.dto.InsertDtoRes;
import com.lawencon.book.dto.book.GetBookDtoRes;
import com.lawencon.book.dto.book.GetBooksDtoRes;
import com.lawencon.book.dto.book.InsertBookDtoReq;
import com.lawencon.book.service.BookService;

@RestController
@RequestMapping("book/data/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> save(@RequestBody InsertBookDtoReq data) throws Exception {
		InsertDtoRes book = bookService.insert(data);
		return new ResponseEntity<InsertDtoRes>(book, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetBookDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetBookDtoRes data = bookService.getById(id);
		return new ResponseEntity<GetBookDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetBooksDtoRes> getAll() throws Exception {
		GetBooksDtoRes data = bookService.getAll();
		return new ResponseEntity<GetBooksDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("genre/{id}")
	public ResponseEntity<GetBooksDtoRes> getByIdGenre(@PathVariable("id") String id) throws Exception {
		GetBooksDtoRes data = bookService.getByGenre(id);
		return new ResponseEntity<GetBooksDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("bookmark/{id}")
	public ResponseEntity<GetBooksDtoRes> getByBookmark(@PathVariable("id") String id) throws Exception {
		GetBooksDtoRes data = bookService.getByBookmark(id);
		return new ResponseEntity<GetBooksDtoRes>(data, HttpStatus.OK);
	}
}
