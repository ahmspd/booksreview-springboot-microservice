package com.lawencon.books.controller;

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
import com.lawencon.books.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.books.pojo.PojoBooks;
import com.lawencon.books.service.BookmarkService;

@RestController
@RequestMapping("book/transaction/bookmark")
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@PostMapping
	public ResponseEntity<InsertDtoRes> save(@RequestBody InsertBookmarkDtoReq data) throws Exception {
		InsertDtoRes datas = bookmarkService.insert(data);
		return new ResponseEntity<InsertDtoRes>(datas, HttpStatus.CREATED);
	}
	
	@GetMapping("user/{idUser}")
	public ResponseEntity<PojoBooks> getIdBookFromBookmark(@PathVariable String idUser) throws Exception {
		PojoBooks result = bookmarkService.getIdBookFromBookmark(idUser);
		return new ResponseEntity<PojoBooks>(result, HttpStatus.OK);
	}
}
