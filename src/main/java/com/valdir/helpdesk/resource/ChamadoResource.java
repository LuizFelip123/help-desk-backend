package com.valdir.helpdesk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.helpdesk.domain.Chamado;
import com.valdir.helpdesk.domain.dtos.ChamadoDTO;
import com.valdir.helpdesk.service.ChamadoService;

import jakarta.validation.Valid;


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
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
      List<Chamado> listChamados = chamadoService.findAll();
      List<ChamadoDTO> listDTO = listChamados.stream().map(x->  new ChamadoDTO(x)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody @Valid ChamadoDTO chamadoDTO){
        Chamado obj =  chamadoService.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri(); 
       return ResponseEntity.created(uri).build();
    }
}
