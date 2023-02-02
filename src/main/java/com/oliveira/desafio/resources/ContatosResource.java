package com.oliveira.desafio.resources;

import java.net.URI;
import java.util.List;

import com.oliveira.desafio.entities.Enderecos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oliveira.desafio.entities.Contatos;
import com.oliveira.desafio.services.ContatosService;

@RestController
@RequestMapping(value = "/contatos")
public class ContatosResource {
	
	@Autowired
	private ContatosService service;
	
	@GetMapping
	public ResponseEntity<List<Contatos>> findAll(){
		List<Contatos> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Contatos> findById(@PathVariable Long id){
		Contatos obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{nome}")
	public ResponseEntity<Contatos> findByNome(@PathVariable String nome){
		Contatos obj = service.findByNome(nome);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{sobrenome}")
	public ResponseEntity<Contatos> findBySobreNome(@PathVariable String sobreNome){
		Contatos obj = service.findBySobreNome(sobreNome);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Contatos> insert(@RequestBody Contatos obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Void> deleteAll(){
		service.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Contatos> update(@PathVariable Long id, @RequestBody Contatos obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/page/contatos")
	public ResponseEntity<Page> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Contatos> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
}
