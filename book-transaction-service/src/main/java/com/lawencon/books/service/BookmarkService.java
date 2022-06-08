package com.lawencon.books.service;

import java.math.BigInteger;

import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.books.pojo.PojoBooks;

public interface BookmarkService extends BaseMasterService {
	InsertDtoRes insert(InsertBookmarkDtoReq data);
	
	PojoBooks getIdBookFromBookmark(String idUser);
	
	BigInteger getCountBookmark(String idBook);
	
	String GetIdBookmark(String idBook, String idUser);
}
