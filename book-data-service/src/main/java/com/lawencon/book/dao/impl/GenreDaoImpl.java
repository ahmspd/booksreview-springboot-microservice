package com.lawencon.book.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.book.dao.GenreDao;
import com.lawencon.book.model.Genre;

@Repository
public class GenreDaoImpl extends BaseDaoImpl implements GenreDao{

	@Override
	public Genre insert(Genre data) {
		return super.saveInsert(data);
	}

	@Override
	public Genre update(Genre data) {
		return super.saveUpdate(data);
	}

	@Override
	public Genre getById(String id) {
		return super.getById(Genre.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Genre.class, id);
	}

	@Override
	public List<Genre> getAll() {
		List<Genre> data = em.createQuery("From Genre", Genre.class).getResultList();
		return data;
	}

}
