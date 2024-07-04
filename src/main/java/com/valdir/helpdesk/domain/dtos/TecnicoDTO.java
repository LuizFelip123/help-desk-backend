package com.valdir.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.enums.Perfil;

import lombok.AccessLevel;
import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected  Integer id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String cpf;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern="dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();
    public TecnicoDTO(){
        super();
    }
    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.cpf = tecnico.getCpf();
        this.email = tecnico.getEmail();
        this.nome = tecnico.getNome();
        this.senha = tecnico.getSenha();
        this.perfis = tecnico.getperfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnico.getDataCriacao();
    }
    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCodigo());
    }
    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x-> Perfil.toEnum(x)).collect(Collectors.toSet());
    }
}
