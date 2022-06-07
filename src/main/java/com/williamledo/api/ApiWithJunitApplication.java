package com.williamledo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.williamledo.api.domain.User;

@SpringBootApplication
public class ApiWithJunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWithJunitApplication.class, args);
		
		User user = new User(1, "William", "email@gmail.com", "1234");
		
	}

}
