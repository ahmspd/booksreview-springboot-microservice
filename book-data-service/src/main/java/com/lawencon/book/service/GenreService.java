package com.lawencon.book.service;

import com.lawencon.book.dto.InsertDtoRes;
import com.lawencon.book.dto.UpdateDtoRes;
import com.lawencon.book.dto.genre.GetGenreDtoRes;
import com.lawencon.book.dto.genre.GetGenresDtoRes;
import com.lawencon.book.dto.genre.InsertGenreDtoReq;
import com.lawencon.book.dto.genre.UpdateGenreDtoReq;

public interface GenreService extends BaseMasterService{

	InsertDtoRes insert(InsertGenreDtoReq data);
	
	GetGenreDtoRes getById(String id);
	
	GetGenresDtoRes getAll();
	
	UpdateDtoRes update(UpdateGenreDtoReq data);
}
