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
import com.lawencon.book.dto.genre.GetGenreDtoRes;
import com.lawencon.book.dto.genre.GetGenresDtoRes;
import com.lawencon.book.dto.genre.InsertGenreDtoReq;
import com.lawencon.book.service.GenreService;

@RestController
@RequestMapping("book/data/genres")
public class GenreController {

	@Autowired
	private GenreService genreService;
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> save(@RequestBody InsertGenreDtoReq data) throws Exception {
		InsertDtoRes genre = genreService.insert(data);
		return new ResponseEntity<InsertDtoRes>(genre, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetGenreDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetGenreDtoRes data = genreService.getById(id);
		return new ResponseEntity<GetGenreDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetGenresDtoRes> getAll() throws Exception {
		GetGenresDtoRes data = genreService.getAll();
		return new ResponseEntity<GetGenresDtoRes>(data, HttpStatus.OK);
	}
}
