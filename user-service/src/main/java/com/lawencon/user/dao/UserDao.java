package com.lawencon.user.dao;

import com.lawencon.user.model.User;

public interface UserDao extends BaseMasterDao<User>{

	User getByEmail(String email);
}
