package com.valdir.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.helpdesk.domain.dtos.ClienteDTO;
import com.valdir.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;



@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1l;
    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome,cpf,email, senha);
    
        addPerfil(Perfil.CLIENTE);
    }
    public Cliente(ClienteDTO clienteDTO) {
        this.id = clienteDTO.getId();
        this.cpf = clienteDTO.getCpf();
        this.email = clienteDTO.getEmail();
        this.nome = clienteDTO.getNome();
        this.senha = clienteDTO.getSenha();
        this.perfis = clienteDTO.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = clienteDTO.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
