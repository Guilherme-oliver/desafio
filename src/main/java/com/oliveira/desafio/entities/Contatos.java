package com.oliveira.desafio.entities;

import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Contatos implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobreNome;

    @ManyToOne
    private Agenda agenda;

    @OneToMany(mappedBy = "contatos")
    private Set<Telefones> tel = new HashSet<>();

    @OneToMany(mappedBy = "contatos")
    private Set<Enderecos> end = new HashSet<>();

    public Contatos(){
    }

    public Contatos(Long id, String nome, String sobreNome) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public Set<Telefones> getTel() {
        return tel;
    }

    public void setTel(Set<Telefones> tel) {
        this.tel = tel;
    }

    public Set<Enderecos> getEnd() {
        return end;
    }

    public void setEnd(Set<Enderecos> end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contatos contatos = (Contatos) o;
        return Objects.equals(getId(), contatos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}