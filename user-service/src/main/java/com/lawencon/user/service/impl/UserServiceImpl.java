package com.lawencon.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.user.constant.ResponseMessageType;
import com.lawencon.user.dao.ProfileDao;
import com.lawencon.user.dao.UserDao;
import com.lawencon.user.dto.DeleteDtoRes;
import com.lawencon.user.dto.InsertDataDtoRes;
import com.lawencon.user.dto.InsertDtoRes;
import com.lawencon.user.dto.UpdateDataDtoRes;
import com.lawencon.user.dto.UpdateDtoRes;
import com.lawencon.user.dto.user.GetUserDtoRes;
import com.lawencon.user.dto.user.GetUsersDtoRes;
import com.lawencon.user.dto.user.InsertUserDtoReq;
import com.lawencon.user.dto.user.UpdateUserDtoReq;
import com.lawencon.user.dto.user.UserDataDtoRes;
import com.lawencon.user.model.Profile;
import com.lawencon.user.model.Role;
import com.lawencon.user.model.User;
import com.lawencon.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userDb = userDao.getByEmail(username);
		
		if(userDb != null) {
			return new org.springframework.security.core.userdetails.User(username, userDb.getPassword(), new ArrayList<>());
		}
		
		throw new UsernameNotFoundException("Invalid username or password");
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(String id) {
		userDao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertUserDtoReq data) {
		User user = new User();
		user.setEmail(data.getEmail());
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		user.setCreatedBy("1212");

		Role role = new Role();
		role.setId(data.getRoleId());
		role.setVersion(1);
		user.setRole(role);

		User userSave = userDao.insert(user);
		
		Profile profile = new Profile();
		profile.setProfileName(data.getProfileName());
		profile.setProfilePhone(data.getProfilePhone());
		profile.setUser(userSave);
		profile.setCreatedBy(userSave.getId());
		
		profileDao.insert(profile);
		
		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(userSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateUserDtoReq data) {
		User user = userDao.getById(data.getId());
		user.setVersion(data.getVersion());
		user.setIsActive(data.getIsActive());
		user.setUpdatedBy(loginId());

		Role role = new Role();
		role.setId(data.getRoleId());
		role.setVersion(1);
		user.setRole(role);

		User userSave = userDao.update(user);

		UpdateDtoRes response = new UpdateDtoRes();

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(userSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	public GetUserDtoRes getById(String id) {
		User user = userDao.getById(id);

		GetUserDtoRes response = new GetUserDtoRes();

		UserDataDtoRes responseData = new UserDataDtoRes();
		responseData.setId(user.getId());
		responseData.setEmail(user.getEmail());
		responseData.setRoleId(user.getRole().getId());
		responseData.setVersion(user.getVersion());
		responseData.setIsActive(user.getIsActive());
		responseData.setRoleCode(user.getRole().getRoleCode());
		responseData.setRoleName(user.getRole().getRoleName());
		Profile profileData = profileDao.getByUserId(user.getId());
		responseData.setProfileName(profileData.getProfileName());
		responseData.setProfilePhone(profileData.getProfilePhone());
		
		response.setData(responseData);

		return response;
	}

	@Override
	public GetUsersDtoRes getAll() {
		GetUsersDtoRes response = new GetUsersDtoRes();
		List<UserDataDtoRes> responseData = new ArrayList<>();
		response.setData(responseData);

		List<User> users = userDao.getAll();
		users.forEach(user -> {
			UserDataDtoRes data = new UserDataDtoRes();
			data.setId(user.getId());
			data.setEmail(user.getEmail());
			data.setRoleId(user.getRole().getId());
			data.setVersion(user.getVersion());
			data.setIsActive(user.getIsActive());

			responseData.add(data);
		});

		return response;
	}

}
