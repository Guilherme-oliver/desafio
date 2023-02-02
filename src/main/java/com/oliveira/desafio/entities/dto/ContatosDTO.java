package com.oliveira.desafio.entities.dto;

import com.oliveira.desafio.services.validation.ContatosUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ContatosUpdate
public class ContatosDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatorio!")
    @Length(min = 2, max = 80, message = "Mínimo 2 e máximo 80 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatorio!")
    @Length(min = 2, max = 80, message = "Mínimo 2 e máximo 80 caracteres")
    private String sobreNome;

    public ContatosDTO(){
    }

    public ContatosDTO(Long id, String nome, String sobreNome) {
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
}
