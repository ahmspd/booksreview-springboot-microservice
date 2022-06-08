package com.lawencon.book.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.book.dao.GenreBookDao;
import com.lawencon.book.model.GenreBook;

@Repository
public class GenreBookDaoImpl extends BaseDaoImpl implements GenreBookDao{

	@Override
	public GenreBook insert(GenreBook data) {
		return super.saveInsert(data);
	}

	@Override
	public GenreBook update(GenreBook data) {
		return super.saveUpdate(data);
	}

	@Override
	public GenreBook getById(String id) {
		return super.getById(GenreBook.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(GenreBook.class, id);
	}

	@Override
	public List<GenreBook> getAll() {
		List<GenreBook> data = em.createQuery("FROM GenreBook",GenreBook.class).getResultList();
		return data;
	}

	@Override
	public List<String> getIdGenreByBook(String idBook) {
		String sql = "select tgb.id_genre from t_genre_book tgb where tgb.id_book = :idBook";
		
		List<?> results = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.getResultList();
		List<String> res = new ArrayList<String>();
		results.forEach(list ->{
			String idGenre = list.toString();
			
			res.add(idGenre);
		});
		
		return res;
	}
	
	@Override
	public List<String> getIdBookByGenre(String idGenre) {
		String sql = "select tgk.id_book from t_genre_book tgk where tgk.id_genre = :idGenre";
		
		List<?> results = em.createNativeQuery(sql)
				.setParameter("idGenre", idGenre)
				.getResultList();
		List<String> res = new ArrayList<String>();
		results.forEach(list-> {
			String idBook = list.toString();
			
			res.add(idBook);
		});
		
		return res;
	}
}
