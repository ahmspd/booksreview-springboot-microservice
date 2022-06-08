package com.lawencon.books.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebIgnoringConfig {

	private List<RequestMatcher> matchers = new ArrayList<>();

	@Bean(name = "webIgnoring")
	public List<RequestMatcher> antMatchers() {
		return new ArrayList<RequestMatcher>();
	}

	private WebIgnoringConfig addAntMatchersBuilder(HttpMethod httpMethod, String... antPatterns) {
		String method = (httpMethod != null) ? httpMethod.toString() : null;
		for (String pattern : antPatterns) {
			matchers.add(new AntPathRequestMatcher(pattern, method));
		}
		return this; 
	}

	private List<RequestMatcher> build() {
		return matchers;
	}

}
