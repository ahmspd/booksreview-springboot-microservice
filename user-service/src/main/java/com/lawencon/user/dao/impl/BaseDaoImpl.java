package com.lawencon.user.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDaoImpl {

	@PersistenceContext
	protected EntityManager em;

	protected <T> T saveInsert(T data) {
		em.persist(data);
		return data;
	}

	protected <T> T saveUpdate(T data) {
		T dataMerge = em.merge(data);
		em.flush();
		return dataMerge;
	}

	protected <T> void deleteById(Class<T> clazz, String id) {
		T removedEntity = em.find(clazz, id);
		em.remove(removedEntity);
	}

	protected <T> T getById(Class<T> clazz, String id) {
		return em.find(clazz, id);
	}

}
