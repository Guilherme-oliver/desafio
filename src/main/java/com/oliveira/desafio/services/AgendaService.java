package com.oliveira.desafio.services;

import com.oliveira.desafio.entities.Agenda;
import com.oliveira.desafio.repositories.AgendaRepository;
import com.oliveira.desafio.repositories.ContatosRepository;
import com.oliveira.desafio.services.exceptions.DatabaseException;
import com.oliveira.desafio.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private ContatosRepository contatosRepository;

    public List<Agenda> findAll(){
        return repository.findAll();
    }

    public Agenda find(Long id){
        Optional<Agenda> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Agenda insert(Agenda obj) {
        obj.setId(null);
        obj = repository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public void deleteAll() {
        repository.deleteAll();
    }

    private void updateData(Agenda novoObjeto, @NotNull Agenda objeto){
        novoObjeto.setContatos(objeto.getContatos());
    }

    public Agenda update(Long id, Agenda obj) {
        try {
            Agenda entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
