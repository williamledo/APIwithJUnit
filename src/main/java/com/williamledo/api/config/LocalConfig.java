package com.williamledo.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.repositories.UsuarioRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UsuarioRepository repository;
	
	@Bean //Faz isso sempre que esse perfil estiver ativo
	public void startDB() {
		
		Usuario u1 = new Usuario(null, "William", "william@mail.com", "123");
		Usuario u2 = new Usuario(null, "Luiz", "luiz@mail.com", "123");
	
		repository.saveAll(List.of(u1,u2));
		
	}
	
}
