package com.oliveira.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.oliveira.desafio.entities.Contatos;
import com.oliveira.desafio.repositories.ContatosRepository;
import com.oliveira.desafio.repositories.EnderecosRepository;
import com.oliveira.desafio.repositories.TelefonesRepository;
import com.oliveira.desafio.services.exceptions.DatabaseException;
import com.oliveira.desafio.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ContatosService {

	private ContatosRepository contatosRepository;

	private EnderecosRepository enderecosRepository;
	
	private TelefonesRepository telefonesRepository;

	public Contatos find(Long id) {
		Optional<Contatos> obj = contatosRepository.findById(id);
		return obj.orElse(null);
	}

	public List<Contatos> findAll() {
		return contatosRepository.findAll();
	}
	
	public Contatos findByNome(String nome) {
		Optional<Contatos> objNome = contatosRepository.findByNome(nome);
		return objNome.orElse(null);
	}
	
	public Contatos findBySobreNome(String sobreNome) {
		Optional<Contatos> objSobreNome = contatosRepository.findBySobreNome(sobreNome);
		return objSobreNome.orElse(null);
	}

	@Transactional
	public Contatos insert(Contatos obj) {
		obj.setId(null);
		obj = contatosRepository.save(obj);
		enderecosRepository.saveAll(obj.getEnd());
		telefonesRepository.saveAll(obj.getTel());
		return obj;
	}

	public void delete(Long id) {
		try {
			contatosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public void deleteAll() {
		contatosRepository.deleteAll();
	}
	
	public Contatos update(Long id, Contatos obj) {
		try {
			Contatos entity = contatosRepository.getReferenceById(id);
			updateData(entity, obj);
			return contatosRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Contatos entity, Contatos obj) {
		entity.setNome(obj.getNome());
		entity.setSobreNome(obj.getSobreNome());
		entity.setTel(obj.getTel());
		entity.setEnd(obj.getEnd());
	}
	
	public Page<Contatos> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return contatosRepository.findAll(pageRequest);
	}

}
