package com.valdir.helpdesk.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.valdir.helpdesk.domain.enums.Perfil;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> autorities; 

    public UserSS(Set<Perfil> perfils, String email, Integer id, String senha) {
        this.autorities = perfils.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
        this.email = email;
        this.id = id;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
     }

     public Integer getId(){
        return id;
     }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    

}
