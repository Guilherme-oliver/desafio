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

import com.oliveira.desafio.entities.Telefones;
import com.oliveira.desafio.repositories.TelefonesRepository;
import com.oliveira.desafio.services.exceptions.DatabaseException;
import com.oliveira.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TelefonesService {

	@Autowired
	private TelefonesRepository telefonesRepository;

	public Telefones find(Long id) {
		Optional<Telefones> obj = telefonesRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<Telefones> findAll() {
		return telefonesRepository.findAll();
	}

	@Transactional
	public Telefones insert(Telefones obj) {
		obj.setId(null);
		obj = telefonesRepository.save(obj);
		return obj;
	}

	public void delete(Long id) {
		try {
			telefonesRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public void deleteAll() {
		telefonesRepository.deleteAll();
	}
	
	public Telefones update(Long id, Telefones obj) {
		try {
			Telefones entity = telefonesRepository.getReferenceById(id);
			updateData(entity, obj);
			return telefonesRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Telefones entity, Telefones obj) {
		entity.setCliente(obj.getContatos());
		entity.setDdd(obj.getDdd());
		entity.setNumeroCel(obj.getNumeroCel());
	}
	
	public Page<Telefones> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return telefonesRepository.findAll(pageRequest);
	}

}
