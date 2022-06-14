package com.williamledo.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamledo.api.domain.Usuario;
import com.williamledo.api.domain.dto.UsuarioDTO;
import com.williamledo.api.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){
		//.map (o objeto que tem a fonte das informações e o tipo de destino que será retornado
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UsuarioDTO.class));		
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		
		//Como essa lista será enviada para o client, para proteção não damos acesso direto a entitade
		//por isso convertemos para um DTO
		
		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList());
		//para cada elemento irá usar o mapper.map que irá transformar o objeto (x), na classe 
		//especificada (UsuarioDTO), depois irá transformalo em lista de novo
		
		return ResponseEntity.ok().body(listDTO);
		
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO obj) { //Para dizer que o obj vai chegar em JSON quando for fazer a requisição
																			// e vai ser desserializado para um objeto User
		Usuario newObj = service.create(obj);
		// Faz parte das boas práticas quando um usuario for criado ele retornar um URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
