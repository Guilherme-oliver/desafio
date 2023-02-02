package com.oliveira.desafio.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Telefones implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ddd;
    private Integer numeroCel;

    @JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="contatos_id")
    private Contatos contatos;

    public Telefones(){
    }

    public Telefones(Long id, int ddd, Integer numeroCel) {
        this.id = id;
        this.ddd = ddd;
        this.numeroCel = numeroCel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public Integer getNumeroCel() {
        return numeroCel;
    }

    public void setNumeroCel(Integer numero) {
        this.numeroCel = numero;
    }

    public Contatos getContatos() {
        return contatos;
    }

    public void setCliente(Contatos contatos) {
        this.contatos = contatos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefones telefones = (Telefones) o;
        return Objects.equals(getId(), telefones.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
