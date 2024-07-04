package com.valdir.helpdesk.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valdir.helpdesk.domain.Chamado;
import com.valdir.helpdesk.domain.dtos.ChamadoDTO;
import com.valdir.helpdesk.service.ChamadoService;


@RestController
@RequestMapping("/chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService chamadoService;
 
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
      Chamado chamado = chamadoService.findById(id);
      return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
