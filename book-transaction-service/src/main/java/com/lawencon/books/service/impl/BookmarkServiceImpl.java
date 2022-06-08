package com.lawencon.books.service.impl;

import java.math.BigInteger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.books.constant.ResponseMessageType;
import com.lawencon.books.dao.BookmarkDao;
import com.lawencon.books.dto.DeleteDtoRes;
import com.lawencon.books.dto.InsertDataDtoRes;
import com.lawencon.books.dto.InsertDtoRes;
import com.lawencon.books.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.books.model.Bookmark;
import com.lawencon.books.pojo.PojoBooks;
import com.lawencon.books.service.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkDao bookmarkDao;
	
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
	public InsertDtoRes insert(InsertBookmarkDtoReq data) {
		restTemplate.getForObject("http://BOOK-DATA-SERVICE/book/data/books/"+data.getIdBook(), Object.class);
		restTemplate.getForObject("http://USER-SERVICE/users/"+data.getCreatedBy(), Object.class);
		
		Bookmark bookmark = new Bookmark();
		bookmark.setCreatedBy(data.getCreatedBy());
		bookmark.setIdBook(data.getIdBook());
		
		Bookmark bookmarkData = bookmarkDao.insert(bookmark);
		
		InsertDataDtoRes resData = new InsertDataDtoRes();
		resData.setId(bookmarkData.getId());
		
		InsertDtoRes response = new InsertDtoRes();
		response.setData(resData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());
		
		return response;
	}

	@Override
	public PojoBooks getIdBookFromBookmark(String idUser) {
		PojoBooks result = bookmarkDao.getIdBookFromBookmark(idUser);
		return result;
	}
	
	@Override
	public BigInteger getCountBookmark(String idBook) {
		BigInteger result = bookmarkDao.getCountReview(idBook);
		return result;
	}
}
