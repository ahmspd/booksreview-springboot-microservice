package com.lawencon.books.dao.impl;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bookmark getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bookmark> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PojoBooks getIdBookFromBookmark(String idUser) {
		String sql = "select tb.id_book from t_bookmark tb where tb.created_by = :idUser";
		List<?> results = em.createNativeQuery(sql)
				.setParameter("idUser", idUser)
				.getResultList();
		PojoBooks res = new PojoBooks();
		List<String> result = new ArrayList<String>();
		results.stream().forEach(list -> {
			String idBook = list.toString();
			result.add(idBook);
		});
		res.setData(result);
		return res;
	}
	
}
