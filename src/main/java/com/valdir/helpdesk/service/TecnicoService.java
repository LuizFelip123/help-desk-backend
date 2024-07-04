package com.valdir.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Pessoa;
import com.valdir.helpdesk.domain.Tecnico;
import com.valdir.helpdesk.domain.dtos.TecnicoDTO;
import com.valdir.helpdesk.repository.PessoaRepository;
import com.valdir.helpdesk.repository.TecnicoRepository;
import com.valdir.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.valdir.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCpfEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa>  obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId() ){
            throw new  DataIntegrityViolationException("Cpf já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
            throw new  DataIntegrityViolationException("E-email já cadstrado no sistema!");     
        }
    }

    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldTecnico = findById(id);
        validaPorCpfEmail(tecnicoDTO);
        oldTecnico = new Tecnico(tecnicoDTO);
         return tecnicoRepository.save(oldTecnico);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else{
            tecnicoRepository.deleteById(id);
        }
        
    }

}
