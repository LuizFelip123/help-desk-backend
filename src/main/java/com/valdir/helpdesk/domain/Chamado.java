package com.valdir.helpdesk.domain;

import java.time.LocalDate;

import com.valdir.helpdesk.domain.enums.Prioridade;
import com.valdir.helpdesk.domain.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chamado {
    private Integer id;
    private LocalDate dataAbertura = LocalDate.now();
    private LocalDate dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;
    private Tecnico tecnico;
    private Cliente cliente;
    public Chamado(){
        super();
    }
    public Chamado(Integer id, Prioridade prioridade, Status status,String titulo, String observacoes, Tecnico tecnico, Cliente cliente){
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }
}
