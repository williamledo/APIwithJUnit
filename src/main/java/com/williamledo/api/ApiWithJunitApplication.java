package com.williamledo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.williamledo.api.domain.Usuario;

@SpringBootApplication
public class ApiWithJunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWithJunitApplication.class, args);
		
		Usuario user = new Usuario(3, "William", "email@gmail.com", "12345");
		
	}

}
