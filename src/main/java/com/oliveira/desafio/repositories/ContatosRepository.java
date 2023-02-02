package com.oliveira.desafio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.desafio.entities.Contatos;

public interface ContatosRepository extends JpaRepository<Contatos, Long>{
	
	Optional<Contatos> findByNome(String nome);
	Optional<Contatos> findBySobreNome(String sobreNome);
}
