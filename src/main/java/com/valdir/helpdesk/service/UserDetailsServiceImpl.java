package com.valdir.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.valdir.helpdesk.domain.Pessoa;
import com.valdir.helpdesk.repository.PessoaRepository;
import com.valdir.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PessoaRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = repository.findByEmail(email);
        if (user.isPresent()) {

            return new UserSS(user.get().getperfis(), user.get().getEmail(), user.get().getId(), user.get().getSenha());
        }
        throw  new UsernameNotFoundException(email);
    }

}
