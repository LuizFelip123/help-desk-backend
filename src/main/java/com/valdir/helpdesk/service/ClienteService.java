package com.valdir.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Cliente;
import com.valdir.helpdesk.domain.Pessoa;
import com.valdir.helpdesk.domain.dtos.ClienteDTO;
import com.valdir.helpdesk.repository.ClienteRepository;
import com.valdir.helpdesk.repository.PessoaRepository;
import com.valdir.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.valdir.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
    @Autowired
	private BCryptPasswordEncoder encoder;
    
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        validaPorCpfEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    private void validaPorCpfEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa>  obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != clienteDTO.getId() ){
            throw new  DataIntegrityViolationException("Cpf já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
            throw new  DataIntegrityViolationException("E-email já cadstrado no sistema!");     
        }
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente oldCliente = findById(id);
        validaPorCpfEmail(clienteDTO);
        oldCliente = new Cliente(clienteDTO);
         return clienteRepository.save(oldCliente);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else{
            clienteRepository.deleteById(id);
        }
        
    }

}
