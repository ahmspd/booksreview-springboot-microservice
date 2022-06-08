package com.lawencon.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.user.dto.InsertDtoRes;
import com.lawencon.user.dto.UpdateDtoRes;
import com.lawencon.user.dto.user.GetUserDtoRes;
import com.lawencon.user.dto.user.GetUsersDtoRes;
import com.lawencon.user.dto.user.InsertUserDtoReq;
import com.lawencon.user.dto.user.UpdateUserDtoReq;


public interface UserService extends BaseMasterService, UserDetailsService {

	InsertDtoRes insert(InsertUserDtoReq data);

	UpdateDtoRes update(UpdateUserDtoReq data);

	GetUserDtoRes getById(String id);

	GetUsersDtoRes getAll();
}
