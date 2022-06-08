package com.lawencon.books.service;

import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.books.pojo.PojoBooks;

public interface BookmarkService extends BaseMasterService {
	InsertDtoRes insert(InsertBookmarkDtoReq data);
	
	PojoBooks getIdBookFromBookmark(String idUser);
}
