package com.lawencon.book.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lawencon.book.constant.ResponseMessageType;
import com.lawencon.book.dao.GenreDao;
import com.lawencon.book.dto.DeleteDtoRes;
import com.lawencon.book.dto.InsertDataDtoRes;
import com.lawencon.book.dto.InsertDtoRes;
import com.lawencon.book.dto.UpdateDataDtoRes;
import com.lawencon.book.dto.UpdateDtoRes;
import com.lawencon.book.dto.genre.GenreDataDtoRes;
import com.lawencon.book.dto.genre.GetGenreDtoRes;
import com.lawencon.book.dto.genre.GetGenresDtoRes;
import com.lawencon.book.dto.genre.InsertGenreDtoReq;
import com.lawencon.book.dto.genre.UpdateGenreDtoReq;
import com.lawencon.book.model.Genre;
import com.lawencon.book.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreDao genreDao;
	
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(String id) {
		genreDao.deleteById(id);
		
		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());
		
		return response;
	}

	@Override
	public GetGenresDtoRes getAll() {
		GetGenresDtoRes response = new GetGenresDtoRes();
		List<GenreDataDtoRes> responseData = new ArrayList<GenreDataDtoRes>();
		
		List<Genre> genres = genreDao.getAll();
		genres.forEach(genre -> {
			GenreDataDtoRes data = new GenreDataDtoRes();
			data.setId(genre.getId());
			data.setGenreCode(genre.getGenreCode());
			data.setGenreName(genre.getGenreName());
			data.setIsActive(genre.getIsActive());
			data.setVersion(genre.getVersion());
			
			responseData.add(data);
		});
		response.setData(responseData);
		return response;
	}

	@Override
	public GetGenreDtoRes getById(String id) {
		Genre genre = genreDao.getById(id);
		
		GetGenreDtoRes response = new GetGenreDtoRes();
		
		GenreDataDtoRes responseData = new GenreDataDtoRes();
		responseData.setId(genre.getId());
		responseData.setGenreCode(genre.getGenreCode());
		responseData.setGenreName(genre.getGenreName());
		responseData.setIsActive(genre.getIsActive());
		responseData.setVersion(genre.getVersion());
		
		response.setData(responseData);
		
		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertGenreDtoReq data) {
		//validate createdBy
		restTemplate.getForObject("http://USER-SERVICE/users/"+data.getCreatedBy(), Object.class);
		
		Genre genre = new Genre();
		genre.setGenreCode(data.getGenreCode());
		genre.setGenreName(data.getGenreName());
		genre.setCreatedBy(data.getCreatedBy());
		
		genre = genreDao.insert(genre);
		
		InsertDtoRes res = new InsertDtoRes();
		
		InsertDataDtoRes resData = new InsertDataDtoRes();
		resData.setId(genre.getId());
		
		res.setData(resData);
		res.setMessage(ResponseMessageType.SAVED.getDesc());
		
		return res;
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateGenreDtoReq data) {
		//validate updatedBy
		restTemplate.getForObject("http://USER-SERVICE/users/"+data.getUpdatedBy(), Object.class);
		
		Genre genre = genreDao.getById(data.getId());
		genre.setGenreName(data.getGenreName());
		if(data.getIsActive() != null) {
			genre.setIsActive(data.getIsActive());
		}
		genre.setUpdatedBy(data.getUpdatedBy());
		
		genre = genreDao.update(genre);
		
		UpdateDtoRes response = new UpdateDtoRes();
		
		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(genre.hashCode());
		
		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());
		
		return response;
	}
}
