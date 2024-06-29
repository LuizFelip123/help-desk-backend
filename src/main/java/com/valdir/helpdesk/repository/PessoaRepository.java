package com.valdir.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valdir.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
