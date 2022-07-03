package com.williamledo.api.services.impl;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.domain.dto.UsuarioDTO;
import com.williamledo.api.repositories.UsuarioRepository;
import com.williamledo.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UsuarioServiceImplTest {

	private static final Integer ID = 1;
	private static final String NAME = "William";
	private static final String EMAIL = "william@mail.com";
	private static final String PASSWORD = "123";
	
	@InjectMocks
	//Cria uma instância real, porém os otros ele cria "de mentira"
	private UsuarioServiceImpl service;
	
	@Mock
	private UsuarioRepository repository;

	@Mock
	private ModelMapper mapper;  

	private Usuario usuario;
	private UsuarioDTO usuarioDTO;
	private Optional<Usuario> optionalUsuario;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		usuario = new Usuario(ID, NAME, EMAIL, PASSWORD);
		usuarioDTO = new UsuarioDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUsuario = Optional.of(new Usuario(ID, NAME, EMAIL, PASSWORD));
		
	}
	
	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUsuario);
		
		Usuario response = service.findById(ID);
		
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Usuario.class, response.getClass());
		Assertions.assertEquals(ID, response.getId());
		Assertions.assertEquals(NAME, response.getName());
		Assertions.assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		
		Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		
		try {
			service.findById(ID);
		}catch (Exception ex) {
			Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
			Assertions.assertEquals("Objeto não encontrado", ex.getMessage());
		}
		
		
	}
	
	
}
