package com.williamledo.api.services;

import java.util.List;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.domain.dto.UsuarioDTO;

public interface UsuarioService {

	Usuario findById(Integer id);
	List<Usuario> findAll();
	Usuario create(UsuarioDTO obj);
	Usuario update(UsuarioDTO obj);
	void delete(Integer id);
	
}
