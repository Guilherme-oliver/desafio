package com.oliveira.desafio.entities.dto;

import com.oliveira.desafio.services.validation.ContatosInsert;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ContatosInsert
public class ContatosNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
    private String sobreNome;

    @NotEmpty(message="Preenchimento obrigatório")
    private Integer cep;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
    private String logradouro;

    @NotEmpty(message="Preenchimento obrigatório")
    private Integer numero;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
    private String cidade;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min = 2, max = 80, message="O tamanho deve ser entre 2 e 80 caracteres")
    private String estado;

    @NotEmpty(message="Preenchimento obrigatório")
    private int ddd;

    @NotEmpty(message="Preenchimento obrigatório")
    private Integer numeroCel;

    public ContatosNewDTO(){
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

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public void setNumeroCel(Integer numeroCel) {
        this.numeroCel = numeroCel;
    }
}
