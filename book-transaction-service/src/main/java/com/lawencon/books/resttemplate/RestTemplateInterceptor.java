package com.lawencon.books.resttemplate;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lawencon.books.pojo.AuthorizationPojo;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		// get token from SecurityContextHolder principal
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			AuthorizationPojo authorizationPojo = (AuthorizationPojo) authentication.getPrincipal();
			request.getHeaders().setBearerAuth(authorizationPojo.getToken());
		}

		return execution.execute(request, body);
	}

}
