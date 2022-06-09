package com.williamledo.api.services;

import org.springframework.stereotype.Service;

import com.williamledo.api.domain.Usuario;

public interface UsuarioService {

	Usuario findById(Integer id);
	
}
