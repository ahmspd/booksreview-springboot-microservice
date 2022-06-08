package com.lawencon.user.dao;

import com.lawencon.user.model.Profile;

public interface ProfileDao extends BaseMasterDao<Profile>{
	Profile getByUserId(String idUser);
}
