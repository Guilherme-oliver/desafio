package com.oliveira.desafio.resources;

import java.net.URI;
import java.util.List;

import com.oliveira.desafio.entities.Enderecos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oliveira.desafio.entities.Telefones;
import com.oliveira.desafio.services.TelefonesService;


@RestController
@RequestMapping(value = "/telefones")
public class TelefonesResource {
	
	@Autowired
	private TelefonesService service;
	
	@GetMapping
	public ResponseEntity<List<Telefones>> findAll(){
		List<Telefones> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Telefones> findById(@PathVariable Long id){
		Telefones obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Telefones> insert(@RequestBody Telefones obj){
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

	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		service.deleteAll();
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Telefones> update(@PathVariable Long id, @RequestBody Telefones obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value="/page/telefones")
	public ResponseEntity<Page> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Telefones> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

}
