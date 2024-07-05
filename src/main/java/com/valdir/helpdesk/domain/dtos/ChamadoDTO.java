package com.valdir.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.helpdesk.domain.Chamado;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="O campo Prioridade é requerido")
    private Integer prioridade;
    @NotBlank(message="O campo Status é requerido")
    private Integer status;
    @NotBlank(message="O campo Titulo é requerido")
    private String titulo;
    @NotBlank(message="O campo Observações é requerido")
    private String observacoes;
    private LocalDate dataFechamento;
    @NotBlank(message="O campo Técnico é requerido")
    private Integer tecnico;
    @NotBlank(message="O campo Cliente é requerido")
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
