package com.valdir.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.enums.Perfil;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected  Integer id;
    @NotBlank(message="O campo Nome é requerido")
    protected String nome;
    
    @NotBlank(message="O campo EMAIL é requerido")
    protected String email;
    
    @NotBlank(message="O campo Senha é requerido")
    protected String senha;
    
    @NotBlank(message="O campo CPF é requerido")
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
