package com.valdir.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.helpdesk.domain.Chamado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChamadoDTO implements  Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String observacoes;    
    private Integer tecnico;
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        this.cliente = obj.getCliente().getId();
        this.dataFechamento = obj.getDataFechamento();
        this.id = obj.getId();
        this.nomeCliente = obj.getCliente().getNome();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.observacoes = obj.getObservacoes();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.tecnico = obj.getTecnico().getId();
        this.titulo = obj.getTitulo();
    }


}
