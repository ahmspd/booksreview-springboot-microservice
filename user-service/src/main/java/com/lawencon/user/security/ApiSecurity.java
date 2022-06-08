package com.lawencon.user.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.user.dao.UserDao;
import com.lawencon.user.service.UserService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthorizationFilter authorizationFilter;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtComponent jwtComponent;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private List<RequestMatcher> antMatchers;
	
	@Autowired
	private UserDao userDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();

		var authenticationFilter = new AuthenticationFilter(super.authenticationManagerBean(), jwtComponent, objectMapper, userDao);
		http.addFilterBefore(authenticationFilter, BasicAuthenticationFilter.class);

		http.addFilterAfter(authorizationFilter, BasicAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		antMatchers.forEach(matcher -> web.ignoring().requestMatchers(matcher));
	}

}
