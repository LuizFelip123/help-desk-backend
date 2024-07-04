package com.valdir.helpdesk.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.helpdesk.domain.Cliente;
import com.valdir.helpdesk.domain.dtos.ClienteDTO;
import com.valdir.helpdesk.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = this.clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteDTO clienteDTO){
        Cliente newObject = clienteService.create(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody @Valid ClienteDTO clienteDTO){
        Cliente object = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(object));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
