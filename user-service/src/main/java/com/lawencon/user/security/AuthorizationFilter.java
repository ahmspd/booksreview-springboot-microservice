package com.lawencon.user.security;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lawencon.user.dto.user.auth.AuthorizationResDto;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private List<RequestMatcher> antMatchers;

	@Autowired
	private JwtComponent jwtComponent;

	private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		long count = antMatchers.stream().filter(matcher -> matcher.matches(request)).collect(Collectors.counting());
		if (count == 0l) {
			String authorization = request.getHeader("Authorization");

			if (authorization == null || authorization.length() < 8) {
				response.setStatus(401);
				response.getWriter().append("Invalid token");
				return;
			}

			String token = authorization.replaceFirst("Bearer ", "");
			logger.info("token => " + token);

			try {
				String id = jwtComponent.getClaimId(token);

				// register data to SecurityContext
				AuthorizationResDto authorizationResDto = new AuthorizationResDto();
				authorizationResDto.setToken(token);
				authorizationResDto.setId(id);
				
				
				Authentication auth = new UsernamePasswordAuthenticationToken(authorizationResDto, null);
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				response.setStatus(401);
				response.getWriter().append("Invalid token");
				return;
			}

		}

		filterChain.doFilter(request, response);
	}

}
