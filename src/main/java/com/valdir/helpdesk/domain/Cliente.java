package com.valdir.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import com.valdir.helpdesk.domain.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Cliente extends Pessoa {
    private static final long serialVersionUID = 1l;
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

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
