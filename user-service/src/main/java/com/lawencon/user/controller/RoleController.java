package com.lawencon.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.user.dto.DeleteDtoRes;
import com.lawencon.user.dto.InsertDtoRes;
import com.lawencon.user.dto.UpdateDtoRes;
import com.lawencon.user.dto.role.GetRoleDtoRes;
import com.lawencon.user.dto.role.GetRolesDtoRes;
import com.lawencon.user.dto.role.InsertRoleDtoReq;
import com.lawencon.user.dto.role.UpdateRoleDtoReq;
import com.lawencon.user.service.RoleService;

@RestController
@RequestMapping("users/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<InsertDtoRes> insert(@RequestBody InsertRoleDtoReq data) {
		InsertDtoRes dataSave = roleService.insert(data);
		return new ResponseEntity<InsertDtoRes>(dataSave, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateDtoRes> update(@RequestBody UpdateRoleDtoReq data) {
		UpdateDtoRes dataSave = roleService.update(data);
		return new ResponseEntity<UpdateDtoRes>(dataSave, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") String id) {
		DeleteDtoRes dataDelete = roleService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(dataDelete, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetRolesDtoRes> getAll() {
		GetRolesDtoRes data = roleService.getAll();
		return new ResponseEntity<GetRolesDtoRes>(data, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetRoleDtoRes> getById(@PathVariable("id") String id) {
		GetRoleDtoRes data = roleService.getById(id);
		return new ResponseEntity<GetRoleDtoRes>(data, HttpStatus.OK);
	}

}
