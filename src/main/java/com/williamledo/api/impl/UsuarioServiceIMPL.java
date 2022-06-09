package com.williamledo.api.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.repositories.UsuarioRepository;
import com.williamledo.api.services.UsuarioService;
import com.williamledo.api.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioServiceIMPL implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario findById(Integer id) {

		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	
	
}
