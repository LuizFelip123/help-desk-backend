package com.valdir.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Chamado;
import com.valdir.helpdesk.domain.Cliente;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.dtos.ChamadoDTO;
import com.valdir.helpdesk.domain.enums.Prioridade;
import com.valdir.helpdesk.domain.enums.Status;
import com.valdir.helpdesk.repository.ChamadoRepository;
import com.valdir.helpdesk.service.exceptions.ObjectNotFoundException;


@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;
    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id); 
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id ));
    }

    public List<Chamado> findAll() {
        
        return chamadoRepository.findAll(); 

    }

    public Chamado create(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));
    }
    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico =  tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());
           
        Chamado chamado = new Chamado();
        if(chamado.getId() != null){
            chamado.setId(obj.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }    
}
