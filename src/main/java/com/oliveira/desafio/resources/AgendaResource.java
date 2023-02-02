package com.oliveira.desafio.resources;

import com.oliveira.desafio.entities.Agenda;
import com.oliveira.desafio.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/agendas")
public class AgendaResource {

    @Autowired
    private AgendaService service;

    @GetMapping
    public ResponseEntity<List<Agenda>> findAll(){
        List<Agenda> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable Long id){
        Agenda objeto = service.find(id);
        return ResponseEntity.ok().body(objeto);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Agenda> insert(@RequestBody Agenda obj){
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
    public ResponseEntity<Agenda> update(@PathVariable Long id, @RequestBody Agenda obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
