package com.valdir.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.helpdesk.domain.dtos.TecnicoDTO;
import com.valdir.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy="tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(){
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome,cpf,email, senha);
    }
    public Tecnico(TecnicoDTO tecnicoDTO) {
        this.id = tecnicoDTO.getId();
        this.cpf = tecnicoDTO.getCpf();
        this.email = tecnicoDTO.getEmail();
        this.nome = tecnicoDTO.getNome();
        this.senha = tecnicoDTO.getSenha();
        this.perfis = tecnicoDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = tecnicoDTO.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
