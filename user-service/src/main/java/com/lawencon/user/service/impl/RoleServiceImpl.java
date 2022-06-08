package com.lawencon.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.user.constant.ResponseMessageType;
import com.lawencon.user.dao.RoleDao;
import com.lawencon.user.dto.DeleteDtoRes;
import com.lawencon.user.dto.InsertDataDtoRes;
import com.lawencon.user.dto.InsertDtoRes;
import com.lawencon.user.dto.UpdateDataDtoRes;
import com.lawencon.user.dto.UpdateDtoRes;
import com.lawencon.user.dto.role.GetRoleDtoRes;
import com.lawencon.user.dto.role.GetRolesDtoRes;
import com.lawencon.user.dto.role.InsertRoleDtoReq;
import com.lawencon.user.dto.role.RoleDataDtoRes;
import com.lawencon.user.dto.role.UpdateRoleDtoReq;
import com.lawencon.user.model.Role;
import com.lawencon.user.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public DeleteDtoRes deleteById(String id) {
		roleDao.deleteById(id);

		DeleteDtoRes response = new DeleteDtoRes();
		response.setMessage(ResponseMessageType.DELETED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public InsertDtoRes insert(InsertRoleDtoReq data) {
		Role role = new Role();
		role.setRoleCode(data.getRoleCode());
		role.setRoleName(data.getRoleName());
		role.setCreatedBy(loginId());

		Role roleSave = roleDao.insert(role);

		InsertDtoRes response = new InsertDtoRes();

		InsertDataDtoRes responseData = new InsertDataDtoRes();
		responseData.setId(roleSave.getId());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public UpdateDtoRes update(UpdateRoleDtoReq data) {
		Role role = roleDao.getById(data.getId());
		role.setRoleName(data.getRoleName());
		role.setVersion(data.getVersion());
		role.setIsActive(data.getIsActive());
		role.setUpdatedBy(loginId());

		Role roleSave = roleDao.update(role);

		UpdateDtoRes response = new UpdateDtoRes();

		UpdateDataDtoRes responseData = new UpdateDataDtoRes();
		responseData.setVersion(roleSave.getVersion());

		response.setData(responseData);
		response.setMessage(ResponseMessageType.SAVED.getDesc());

		return response;
	}

	@Override
	public GetRoleDtoRes getById(String id) {
		Role role = roleDao.getById(id);

		GetRoleDtoRes response = new GetRoleDtoRes();

		RoleDataDtoRes responseData = new RoleDataDtoRes();
		responseData.setId(role.getId());
		responseData.setRoleCode(role.getRoleCode());
		responseData.setRoleName(role.getRoleName());
		responseData.setVersion(role.getVersion());
		responseData.setIsActive(role.getIsActive());

		response.setData(responseData);

		return response;

	}

	@Override
	public GetRolesDtoRes getAll() {
		GetRolesDtoRes response = new GetRolesDtoRes();
		List<RoleDataDtoRes> responseData = new ArrayList<>();
		response.setData(responseData);

		List<Role> roles = roleDao.getAll();
		roles.forEach(role -> {
			RoleDataDtoRes data = new RoleDataDtoRes();
			data.setId(role.getId());
			data.setRoleCode(role.getRoleCode());
			data.setRoleName(role.getRoleName());
			data.setVersion(role.getVersion());
			data.setIsActive(role.getIsActive());

			responseData.add(data);
		});

		return response;
	}

}
