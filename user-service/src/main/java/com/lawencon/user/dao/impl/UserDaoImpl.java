package com.lawencon.user.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.user.dao.UserDao;
import com.lawencon.user.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public User insert(User data) {
		return super.saveInsert(data);
	}

	@Override
	public User update(User data) {
		return super.saveUpdate(data);
	}

	@Override
	public User getById(String id) {
		return super.getById(User.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(User.class, id);
	}

	@Override
	public List<User> getAll() {
		List<User> data = em.createQuery("FROM User", User.class)
				.getResultList();
		return data;
	}
	
	@Override
	public User getByEmail(String email) {
		User user = null;
		try {
			user = em.createQuery("FROM User where email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
		}
		
		return user;
	}

}
