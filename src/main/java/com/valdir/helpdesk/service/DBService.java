package com.valdir.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Chamado;
import com.valdir.helpdesk.domain.Cliente;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.enums.Perfil;
import com.valdir.helpdesk.domain.enums.Prioridade;
import com.valdir.helpdesk.domain.enums.Status;
import com.valdir.helpdesk.repository.ChamadoRepository;
import com.valdir.helpdesk.repository.ClienteRepository;
import com.valdir.helpdesk.repository.TecnicoRepository;


@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired 
	private ChamadoRepository chamadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Valdir", "440.668.110-86", "valdir@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "123.668.110-86", "trovalds@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamada 01", "Primeiro chamado", tec1, cli1);	
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	
    }
}
