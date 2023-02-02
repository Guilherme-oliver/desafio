package com.oliveira.desafio.services.validation;

import com.oliveira.desafio.entities.Contatos;
import com.oliveira.desafio.entities.dto.ContatosDTO;
import com.oliveira.desafio.repositories.ContatosRepository;
import com.oliveira.desafio.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContatosUpdateInsert implements ConstraintValidator<ContatosUpdate, ContatosDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ContatosRepository repo;


    @Override
    public void initialize(ContatosUpdate ann) {
    }

    @Override
    public boolean isValid(ContatosDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Optional<Contatos> aux = repo.findByNome(objDto.getNome());
        if(aux != null && !aux.get().equals(uriId)) {
            list.add(new FieldMessage("nome", " Nome já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
