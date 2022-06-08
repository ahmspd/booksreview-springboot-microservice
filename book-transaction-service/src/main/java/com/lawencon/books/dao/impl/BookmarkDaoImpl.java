package com.lawencon.books.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.books.dao.BookmarkDao;
import com.lawencon.books.model.Bookmark;
import com.lawencon.books.pojo.PojoBooks;

@Repository
public class BookmarkDaoImpl extends BaseDaoImpl implements BookmarkDao {

	@Override
	public Bookmark insert(Bookmark data) {
		return super.saveInsert(data);
	}

	@Override
	public Bookmark update(Bookmark data) {
		return super.saveUpdate(data);
	}

	@Override
	public Bookmark getById(String id) {
		return super.getById(Bookmark.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Bookmark.class, id);
	}

	@Override
	public List<Bookmark> getAll() {
		List<Bookmark> data = em.createQuery("FROM Bookmark", Bookmark.class).getResultList();
		return data;
	}

	@Override
	public PojoBooks getIdBookFromBookmark(String idUser) {
		String sql = "select tb.id_book from t_bookmark tb where tb.created_by = :idUser";
		List<?> results = em.createNativeQuery(sql).setParameter("idUser", idUser).getResultList();
		PojoBooks res = new PojoBooks();
		List<String> result = new ArrayList<String>();
		results.stream().forEach(list -> {
			String idBook = list.toString();
			result.add(idBook);
		});
		res.setData(result);
		return res;
	}

	@Override
	public BigInteger getCountReview(String idBook) {
		String sql = "select count(tb.id) from t_bookmark tb where tb.id_book = :idBook";
		Object result = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.getSingleResult();
		return (BigInteger) result;
	}
	
	@Override
	public String getIdBookmark(String idBook, String idUser) {
		String sql = "select tb.id from t_bookmark tb where tb.id_book = :idBook and tb.created_by = :idUser";
		Object result = em.createNativeQuery(sql)
				.setParameter("idBook", idBook)
				.setParameter("idUser", idUser)
				.getSingleResult();
		return (String) result;
	}
}
