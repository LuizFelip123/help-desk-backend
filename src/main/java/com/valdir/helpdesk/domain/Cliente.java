package com.valdir.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.valdir.helpdesk.domain.Chamado;

public class Cliente extends Pessoa {
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha){
        super(id, nome,cpf,email, senha);
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
