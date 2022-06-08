package com.lawencon.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.user.dao.RoleDao;
import com.lawencon.user.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@Override
	public Role insert(Role data) {
		return super.saveInsert(data);
	}

	@Override
	public Role update(Role data) {
		return super.saveUpdate(data);
	}

	@Override
	public Role getById(String id) {
		return super.getById(Role.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Role.class, id);
	}

	@Override
	public List<Role> getAll() {
		List<Role> data = em.createQuery("FROM Role", Role.class).getResultList();
		return data;
	}

}
