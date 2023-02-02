package com.oliveira.desafio.services.validation;

import com.oliveira.desafio.entities.Contatos;
import com.oliveira.desafio.entities.dto.ContatosNewDTO;
import com.oliveira.desafio.repositories.ContatosRepository;
import com.oliveira.desafio.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContatosInsertValidation implements ConstraintValidator<ContatosInsert, ContatosNewDTO> {


    @Autowired
    private ContatosRepository repo;


    @Override
    public void initialize(ContatosInsert ann) {
    }

    @Override
    public boolean isValid(ContatosNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Optional<Contatos> aux = repo.findByNome(objDto.getNome());
        if(aux != null) {
            list.add(new FieldMessage("nome", " Contato já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
