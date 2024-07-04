package com.valdir.helpdesk.domain;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.helpdesk.domain.enums.Perfil;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(of={"id","cpf"})
@Entity
@Data
public abstract class Pessoa implements  Serializable {

    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;
    protected String nome;
    @Column(unique=true)
    protected String email;
    protected String senha;
    @CPF
    @Column(unique=true)
    protected String cpf;
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    @Getter(AccessLevel.NONE)
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern="dd/MM/yyyy")
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
    public Set<Perfil> getperfis(){
        return perfis.stream().map(x-> Perfil.toEnum(x)).collect(Collectors.toSet());
    } 

}
