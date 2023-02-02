package com.oliveira.desafio.services;

import com.oliveira.desafio.entities.Agenda;
import com.oliveira.desafio.entities.Contatos;
import com.oliveira.desafio.entities.Enderecos;
import com.oliveira.desafio.entities.Telefones;
import com.oliveira.desafio.repositories.AgendaRepository;
import com.oliveira.desafio.repositories.ContatosRepository;
import com.oliveira.desafio.repositories.EnderecosRepository;
import com.oliveira.desafio.repositories.TelefonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    ContatosRepository contatosRepository;

    @Autowired
    EnderecosRepository enderecosRepository;

    @Autowired
    TelefonesRepository telefonesRepository;

    public void instantiateTestDatabase() throws ParseException {

        Contatos contatos1 = new Contatos(null, "Bill", "Snow");
        Contatos contatos2 = new Contatos(null, "Prof", "Oak");

        Telefones telefones1 = new Telefones(null,11, 989898);
        Telefones telefones2 = new Telefones(null, 34, 845874);

        Enderecos enderecos1 = new Enderecos(null, 38383838, "Pedro Jose Samora", 301, "Uberlandia","MG",contatos2 );
        Enderecos enderecos2 = new Enderecos(null, 85858585, "Joao Pedro Carvalho", 505, "Uberaba","MG",contatos1 );

        contatos1.getEnd().add(enderecos2);
        contatos1.getTel().add(telefones1);
        contatos2.getEnd().add(enderecos1);
        contatos2.getTel().add(telefones2);

        telefonesRepository.saveAll(Arrays.asList(telefones1, telefones2));
        enderecosRepository.saveAll(Arrays.asList(enderecos1, enderecos2));
        contatosRepository.saveAll(Arrays.asList(contatos1, contatos2));

        Agenda agenda = new Agenda(null);
        agenda.getContatos().addAll(Arrays.asList(contatos1, contatos2));

        agendaRepository.save(agenda);

    }
}
