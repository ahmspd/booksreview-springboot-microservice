package com.lawencon.book.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.book.dao.BookDao;
import com.lawencon.book.model.Book;

@Repository
public class BookDaoImpl extends BaseDaoImpl implements BookDao{

	@Override
	public Book insert(Book data) {
		return super.saveInsert(data);
	}

	@Override
	public Book update(Book data) {
		return super.saveUpdate(data);
	}

	@Override
	public Book getById(String id) {
		return super.getById(Book.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Book.class, id);
	}

	@Override
	public List<Book> getAll() {
		List<Book> data = em.createQuery("From Book", Book.class).getResultList();
		return data;
	}
	
}
