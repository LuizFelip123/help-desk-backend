package com.valdir.helpdesk.domain;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.valdir.helpdesk.domain.enums.Perfil;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(of={"id","cpf"})
public abstract class Pessoa {
    protected Integer id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String cpf;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa(){
        addPerfil(Perfil.CLIENTE);
    }
    public Pessoa(Integer id, String nome, String cpf, String email, String senha){
        this.id  = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
    }
    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCodigo());
    }

}
