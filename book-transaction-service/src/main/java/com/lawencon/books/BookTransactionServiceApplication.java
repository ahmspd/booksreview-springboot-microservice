package com.lawencon.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTransactionServiceApplication.class, args);
	}

}
