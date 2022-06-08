package com.lawencon.books.resttemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.lawencon.books.exception.ValidateException;

public class RestTemplateExceptionHandler implements ResponseErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateExceptionHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String responseStr = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
		logger.info("error => " + responseStr);

		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			// handle SERVER_ERROR
		} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			throw new ValidateException(responseStr);
		}
	}

}
