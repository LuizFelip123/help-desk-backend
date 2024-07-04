package com.valdir.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Chamado;
import com.valdir.helpdesk.repository.ChamadoRepository;
import com.valdir.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id); 
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id ));
    }
    
}
