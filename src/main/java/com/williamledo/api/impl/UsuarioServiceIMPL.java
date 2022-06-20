package com.williamledo.api.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.domain.dto.UsuarioDTO;
import com.williamledo.api.repositories.UsuarioRepository;
import com.williamledo.api.services.UsuarioService;
import com.williamledo.api.services.exceptions.DataIntegratyViolationException;
import com.williamledo.api.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Usuario findById(Integer id) {

		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		
	}

	public List<Usuario> findAll() {
		
		return repository.findAll();
		
	}

	@Override
	public Usuario create(UsuarioDTO obj) {
		
		//Esse objeto será enviado para o servidor, e ele aceita a classe Usuario, então
		//convertemos de DTO para Usuario
		findByEmail(obj);
		return repository.save(mapper.map(obj, Usuario.class));
		
	}
	
	@Override
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
		
	}

	
	@Override
	public Usuario update(UsuarioDTO obj) {
		return repository.save(mapper.map(obj, Usuario.class));
	}
	
	private void findByEmail(UsuarioDTO obj) {
		
		Optional<Usuario> usuario = repository.findByEmail(obj.getEmail()); 
		if (usuario.isPresent()) {
			throw new DataIntegratyViolationException("Email já cadastrado no sistema");
		}
		
	}

	
	
	
}
