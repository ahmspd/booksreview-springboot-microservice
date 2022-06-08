package com.lawencon.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.lawencon.user.dto.user.GetUserDtoRes;
import com.lawencon.user.dto.user.GetUsersDtoRes;
import com.lawencon.user.dto.user.InsertUserDtoReq;
import com.lawencon.user.dto.user.UpdateUserDtoReq;
import com.lawencon.user.dto.user.auth.AuthorizationResDto;
import com.lawencon.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService; 

	@PostMapping("verify")
	public ResponseEntity<?> verifyToken() throws Exception {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			AuthorizationResDto data = (AuthorizationResDto) authentication.getPrincipal();
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping
	public ResponseEntity<InsertDtoRes> insert(@RequestBody InsertUserDtoReq data) {
		InsertDtoRes dataSave = userService.insert(data);
		return new ResponseEntity<InsertDtoRes>(dataSave, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateDtoRes> update(@RequestBody UpdateUserDtoReq data) {
		UpdateDtoRes dataSave = userService.update(data);
		return new ResponseEntity<UpdateDtoRes>(dataSave, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteDtoRes> deleteById(@PathVariable("id") String id) {
		DeleteDtoRes dataDelete = userService.deleteById(id);
		return new ResponseEntity<DeleteDtoRes>(dataDelete, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetUsersDtoRes> getAll() {
		GetUsersDtoRes data = userService.getAll();
		return new ResponseEntity<GetUsersDtoRes>(data, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetUserDtoRes> getById(@PathVariable("id") String id) {
		GetUserDtoRes data = userService.getById(id);
		return new ResponseEntity<GetUserDtoRes>(data, HttpStatus.OK);
	}

}
