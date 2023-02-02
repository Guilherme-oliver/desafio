package com.oliveira.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oliveira.desafio.entities.Enderecos;
import com.oliveira.desafio.repositories.EnderecosRepository;
import com.oliveira.desafio.services.exceptions.DatabaseException;
import com.oliveira.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EnderecosService {

	@Autowired
	private EnderecosRepository enderecosRepository;

	public Enderecos find(Long id) {
		Optional<Enderecos> obj = enderecosRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Enderecos> findAll() {
		return enderecosRepository.findAll();
	}

	@Transactional
	public Enderecos insert(Enderecos obj) {
		obj.setId(null);
		obj = enderecosRepository.save(obj);
		return obj;
	}

	public void delete(Long id) {
		try {
			enderecosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public void deleteAll() {
		enderecosRepository.deleteAll();
	}
	
	public Enderecos update(Long id, Enderecos obj) {
		try {
			Enderecos entity = enderecosRepository.getReferenceById(id);
			updateData(entity, obj);
			return enderecosRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Enderecos entity, Enderecos obj) {
		entity.setCep(obj.getCep());
		entity.setCidade(obj.getCidade());
		entity.setEstado(obj.getEstado());
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setContatos(obj.getContatos());
	}
	
	public Page<Enderecos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return enderecosRepository.findAll(pageRequest);
	}

}
