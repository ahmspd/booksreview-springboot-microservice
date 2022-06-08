package com.lawencon.user.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.user.dao.UserDao;
import com.lawencon.user.dto.login.LoginDataResDto;
import com.lawencon.user.dto.login.LoginReqDto;
import com.lawencon.user.dto.login.LoginResDto;
import com.lawencon.user.model.User;

public class AuthenticationFilter extends OncePerRequestFilter {

	private AuthenticationManager authenticationManager;
	private JwtComponent jwtComponent;
	private ObjectMapper objectMapper;
	private UserDao userDao;

	public AuthenticationFilter(AuthenticationManager authenticationManager, JwtComponent jwtComponent,
			ObjectMapper objectMapper, UserDao userDao) {
		this.authenticationManager = authenticationManager;
		this.jwtComponent = jwtComponent;
		this.objectMapper = objectMapper;
		this.userDao = userDao;
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getRequestURI().equals("/login")) {
			try {
				if (!request.getMethod().equals(HttpMethod.POST.name())) {
					response.setStatus(401);
					return;
				}

				LoginReqDto data = objectMapper.readValue(request.getInputStream(), LoginReqDto.class);

				boolean isAuthenticate = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPass()))
						.isAuthenticated();

				if (isAuthenticate) {
					User user = userDao.getByEmail(data.getEmail());
					String token = jwtComponent.GenerateToken(Duration.ofHours(5), user.getId());
					LoginResDto loginResDto = new LoginResDto();

					LoginDataResDto loginData = new LoginDataResDto();
					loginData.setToken(token);
					loginData.setEmail(user.getEmail());
					loginData.setId(user.getId());

					loginResDto.setData(loginData);

					response.setContentType(MediaType.APPLICATION_JSON_VALUE);
					response.getWriter().append(objectMapper.writeValueAsString(loginResDto));
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(401);
				response.getWriter().append(e.getMessage());
			}

			return;
		}

		filterChain.doFilter(request, response);
	}

}
