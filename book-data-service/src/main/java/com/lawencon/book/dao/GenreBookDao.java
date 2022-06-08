package com.lawencon.book.dao;

import java.util.List;

import com.lawencon.book.model.GenreBook;

public interface GenreBookDao extends BaseMasterDao<GenreBook> {
	List<String> getIdGenreByBook(String idBook);
	List<String> getIdBookByGenre(String idGenre);
}
