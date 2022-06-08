package com.lawencon.user.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.user.dto.user.auth.AuthorizationResDto;

public abstract class BaseServiceImpl {

	protected final String defaultLoginId() {
		return "1";
	}

	protected String loginId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() != null) {
			AuthorizationResDto data = (AuthorizationResDto) auth.getPrincipal();
			return data.getId();
		}
		throw new RuntimeException("Invalid login");
	}

}
