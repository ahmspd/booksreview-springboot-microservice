package com.lawencon.user.service;

import com.lawencon.user.dto.InsertDtoRes;
import com.lawencon.user.dto.UpdateDtoRes;
import com.lawencon.user.dto.role.GetRoleDtoRes;
import com.lawencon.user.dto.role.GetRolesDtoRes;
import com.lawencon.user.dto.role.InsertRoleDtoReq;
import com.lawencon.user.dto.role.UpdateRoleDtoReq;

public interface RoleService extends BaseMasterService{

	InsertDtoRes insert(InsertRoleDtoReq data);

	UpdateDtoRes update(UpdateRoleDtoReq data);

	GetRoleDtoRes getById(String id);
	
	GetRolesDtoRes getAll();
}
