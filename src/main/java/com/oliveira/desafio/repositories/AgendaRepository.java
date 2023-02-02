package com.oliveira.desafio.repositories;

import com.oliveira.desafio.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
