package com.lawencon.book.service;

import com.lawencon.book.dto.InsertDtoRes;
import com.lawencon.book.dto.book.GetBookDtoRes;
import com.lawencon.book.dto.book.GetBooksDtoRes;
import com.lawencon.book.dto.book.InsertBookDtoReq;

public interface BookService extends BaseMasterService{

	InsertDtoRes insert(InsertBookDtoReq data);
	
	GetBookDtoRes getById(String id);
	
	GetBooksDtoRes getAll();
	
	GetBooksDtoRes getByGenre(String idGenre);
	
	GetBooksDtoRes getByBookmark(String idUser);
}
