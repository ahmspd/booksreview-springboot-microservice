package com.lawencon.book.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.book.constant.ResponseMessageType;
import com.lawencon.book.dao.BookDao;
import com.lawencon.book.dao.GenreBookDao;
import com.lawencon.book.dao.GenreDao;
import com.lawencon.book.dto.DeleteDtoRes;
import com.lawencon.book.dto.InsertDataDtoRes;
import com.lawencon.book.dto.InsertDtoRes;
import com.lawencon.book.dto.book.BookDataDtoRes;
import com.lawencon.book.dto.book.GetBookDtoRes;
import com.lawencon.book.dto.book.GetBooksDtoRes;
import com.lawencon.book.dto.book.InsertBookDtoReq;
import com.lawencon.book.dto.genre.GenreDataDtoRes;
import com.lawencon.book.model.Book;
import com.lawencon.book.model.Genre;
import com.lawencon.book.model.GenreBook;
import com.lawencon.book.pojo.GetUserPojo;
import com.lawencon.book.pojo.PojoBooks;
import com.lawencon.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private GenreDao genreDao;

	@Autowired
	private GenreBookDao genreBookDao;

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
	public InsertDtoRes insert(InsertBookDtoReq data) {
		ResponseEntity<GetUserPojo> userData = restTemplate.getForEntity("http://USER-SERVICE/users/"+data.getCreatedBy(), GetUserPojo.class);
		
		Book book = new Book();
		book.setAuthor(data.getAuthor());
		book.setBookLanguage(data.getBookLanguage());
		book.setCreatedBy(userData.getBody().getData().getId());
		book.setIsbn(data.getIsbn());
		book.setNumPages(data.getNumPages());
		book.setPublisher(data.getPublisher());
		book.setSynopsis(data.getSynopsis());
		book.setTitle(data.getTitle());
		book.setYearPublished(data.getYearPublished());
		
		Book bookData = bookDao.insert(book);
		
		List<GenreDataDtoRes> listGenre = data.getGenreData();
		listGenre.forEach(list -> {
			Genre genre = new Genre();
			genre = genreDao.getById(list.getId());
			
			GenreBook genreBook = new GenreBook();
			genreBook.setBook(bookData);
			genreBook.setGenre(genre);
			genreBook.setCreatedBy(data.getCreatedBy());
			
			genreBook = genreBookDao.insert(genreBook);
		});
		
		InsertDtoRes res = new InsertDtoRes();
		
		InsertDataDtoRes resData = new InsertDataDtoRes();
		resData.setId(bookData.getId());
		
		res.setData(resData);
		res.setMessage(ResponseMessageType.SAVED.getDesc());
		
		return res;
	}
	
	@Override
	public GetBookDtoRes getById(String id) {
		Book book = bookDao.getById(id);
		
		GetBookDtoRes response = new GetBookDtoRes();
		
		BookDataDtoRes resData = new BookDataDtoRes();
		BigDecimal avgRating = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/avg/"+book.getId(), BigDecimal.class);
		BigInteger reviewCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/count/"+book.getId(), BigInteger.class);
		BigInteger bookmarkCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/bookmark/count/"+book.getId(), BigInteger.class);
		resData.setId(book.getId());
		resData.setRating(avgRating);
		resData.setAuthor(book.getAuthor());
		resData.setBookLanguage(book.getBookLanguage());
		resData.setCreatedBy(book.getAuthor());
		resData.setIsbn(book.getIsbn());
		resData.setNumPages(book.getNumPages());
		resData.setPublisher(book.getPublisher());
		resData.setSynopsis(book.getSynopsis());
		resData.setTitle(book.getTitle());
		resData.setReviewCount(reviewCount);
		resData.setBookmarkCount(bookmarkCount);
		resData.setYearPublished(book.getYearPublished());
		
		List<String> listGenre = genreBookDao.getIdGenreByBook(id);
		List<Genre> genreBook = new ArrayList<Genre>();
		listGenre.forEach(list -> {
			Genre genre = new Genre();
			genre = genreDao.getById(list);
			genreBook.add(genre);
		});
		
		resData.setGenreData(genreBook);
		
		response.setData(resData);
		return response;
	}
	
	@Override
	public GetBooksDtoRes getAll() {
		GetBooksDtoRes response = new GetBooksDtoRes();
		List<BookDataDtoRes> responseData = new ArrayList<BookDataDtoRes>();
		
		List<Book> books = bookDao.getAll();
		books.forEach(book -> {
			BookDataDtoRes resData = new BookDataDtoRes();
			BigDecimal avgRating = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/avg/"+book.getId(), BigDecimal.class);
			BigInteger reviewCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/count/"+book.getId(), BigInteger.class);
			BigInteger bookmarkCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/bookmark/count/"+book.getId(), BigInteger.class);
			resData.setId(book.getId());
			resData.setRating(avgRating);
			resData.setAuthor(book.getAuthor());
			resData.setBookLanguage(book.getBookLanguage());
			resData.setCreatedBy(book.getAuthor());
			resData.setIsbn(book.getIsbn());
			resData.setNumPages(book.getNumPages());
			resData.setPublisher(book.getPublisher());
			resData.setSynopsis(book.getSynopsis());
			resData.setTitle(book.getTitle());
			resData.setReviewCount(reviewCount);
			resData.setBookmarkCount(bookmarkCount);
			resData.setYearPublished(book.getYearPublished());
			List<String> listGenre = genreBookDao.getIdGenreByBook(book.getId());
			List<Genre> genreBook = new ArrayList<Genre>();
			listGenre.forEach(list -> {
				Genre genre = new Genre();
				genre = genreDao.getById(list);
				genreBook.add(genre);
			});
			
			resData.setGenreData(genreBook);
			responseData.add(resData);
		});
		
		response.setData(responseData);
		return response;
	}
	
	@Override
	public GetBooksDtoRes getByGenre(String idGenre) {
		List<String> idBooks = genreBookDao.getIdBookByGenre(idGenre);
		List<BookDataDtoRes> result = new ArrayList<BookDataDtoRes>();
		idBooks.forEach(listIdBook -> {
			Book book = bookDao.getById(listIdBook);
			
			BookDataDtoRes resData = new BookDataDtoRes();
			BigDecimal avgRating = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/avg/"+book.getId(), BigDecimal.class);
			BigInteger reviewCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/count/"+book.getId(), BigInteger.class);
			BigInteger bookmarkCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/bookmark/count/"+book.getId(), BigInteger.class);
			resData.setId(book.getId());
			resData.setRating(avgRating);
			resData.setAuthor(book.getAuthor());
			resData.setBookLanguage(book.getBookLanguage());
			resData.setCreatedBy(book.getAuthor());
			resData.setIsbn(book.getIsbn());
			resData.setNumPages(book.getNumPages());
			resData.setPublisher(book.getPublisher());
			resData.setSynopsis(book.getSynopsis());
			resData.setTitle(book.getTitle());
			resData.setReviewCount(reviewCount);
			resData.setBookmarkCount(bookmarkCount);
			resData.setYearPublished(book.getYearPublished());
			
			List<String> listGenre = genreBookDao.getIdGenreByBook(listIdBook);
			List<Genre> genreBook = new ArrayList<Genre>();
			listGenre.forEach(list -> {
				Genre genre = new Genre();
				genre = genreDao.getById(list);
				genreBook.add(genre);
			});
			
			resData.setGenreData(genreBook);
			
			result.add(resData);
		});
		GetBooksDtoRes responseData = new GetBooksDtoRes();
		responseData.setData(result);
		
		return responseData;
	}
	
	@Override
	public GetBooksDtoRes getByBookmark(String idUser) {
		PojoBooks listBook = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/bookmark/user/"+idUser, PojoBooks.class);
		List<BookDataDtoRes> result = new ArrayList<BookDataDtoRes>();
		List<String> idBooks = listBook.getData();
		idBooks.forEach(listIdBook -> {
			Book book = bookDao.getById(listIdBook);
			
			BookDataDtoRes resData = new BookDataDtoRes();
			BigDecimal avgRating = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/avg/"+book.getId(), BigDecimal.class);
			BigInteger reviewCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/reviews/count/"+book.getId(), BigInteger.class);
			BigInteger bookmarkCount = restTemplate.getForObject("http://BOOK-TRANSACTION-SERVICE/book/transaction/bookmark/count/"+book.getId(), BigInteger.class);
			resData.setId(book.getId());
			resData.setRating(avgRating);
			resData.setAuthor(book.getAuthor());
			resData.setBookLanguage(book.getBookLanguage());
			resData.setCreatedBy(book.getAuthor());
			resData.setIsbn(book.getIsbn());
			resData.setNumPages(book.getNumPages());
			resData.setPublisher(book.getPublisher());
			resData.setSynopsis(book.getSynopsis());
			resData.setTitle(book.getTitle());
			resData.setReviewCount(reviewCount);
			resData.setBookmarkCount(bookmarkCount);
			resData.setYearPublished(book.getYearPublished());
			
			List<String> listGenre = genreBookDao.getIdGenreByBook(listIdBook);
			List<Genre> genreBook = new ArrayList<Genre>();
			listGenre.forEach(list -> {
				Genre genre = new Genre();
				genre = genreDao.getById(list);
				genreBook.add(genre);
			});
			
			resData.setGenreData(genreBook);
			
			result.add(resData);
		});
		GetBooksDtoRes responseData = new GetBooksDtoRes();
		responseData.setData(result);
		
		return responseData;
	}
}
