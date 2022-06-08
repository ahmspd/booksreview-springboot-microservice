package com.lawencon.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.user.dao.ProfileDao;
import com.lawencon.user.model.Profile;

@Repository
public class ProfileDaoImpl extends BaseDaoImpl implements ProfileDao {

	@Override
	public Profile insert(Profile data) {
		return super.saveInsert(data);
	}

	@Override
	public Profile update(Profile data) {
		return super.saveUpdate(data);
	}

	@Override
	public Profile getById(String id) {
		return super.getById(Profile.class, id);
	}

	@Override
	public void deleteById(String id) {
		super.deleteById(Profile.class, id);
		
	}

	@Override
	public List<Profile> getAll() {
		List<Profile> data = em.createQuery("FROM Profile", Profile.class).getResultList();
		return data;
	}
	
	@Override
	public Profile getByUserId(String idUser) {
		Profile profile = null;
		try {
			profile = em.createQuery("FROM Profile p where p.user.id = :idUser", Profile.class)
					.setParameter("idUser", idUser)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return profile;
	}

}
