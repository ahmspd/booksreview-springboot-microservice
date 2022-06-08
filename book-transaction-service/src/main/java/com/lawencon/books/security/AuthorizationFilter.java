package com.lawencon.books.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lawencon.books.pojo.AuthorizationPojo;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Autowired
	@Qualifier(value = "restTemplateAuthorization")
	private RestTemplate restTemplate;

	@Autowired
	private List<RequestMatcher> antMatchers;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		long count = antMatchers.stream().filter(matcher -> matcher.matches(request)).collect(Collectors.counting());
		if (count == 0l) {
			String authorization = request.getHeader("Authorization");
			if (authorization == null || authorization.length() < 8) {
				setResponse(response, 401, "Invalid token");
				return;
			}

			String token = authorization.replaceFirst("Bearer ", "");

			ResponseEntity<AuthorizationPojo> result = null;
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", token);

				RequestEntity<?> req = RequestEntity.post("http://USER-SERVICE/users/verify").headers(headers).build();
				result = restTemplate.exchange(req, AuthorizationPojo.class);
			} catch (Exception e) {
				setResponse(response, 401, "Invalid token");
				return;
			}

			// register email to SecurityContext
			Authentication auth = new UsernamePasswordAuthenticationToken(result.getBody(), null);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);
	}

	private void setResponse(HttpServletResponse response, int httpStatus, String msg) throws IOException {
		response.setStatus(httpStatus);
		response.getWriter().append(msg);
	}

}
