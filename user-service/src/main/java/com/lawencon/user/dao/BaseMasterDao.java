package com.lawencon.user.dao;

import java.util.List;

public interface BaseMasterDao<T> {

	T insert(T data);

	T update(T data);

	T getById(String id);
	
	void deleteById(String id);

	List<T> getAll();

}
