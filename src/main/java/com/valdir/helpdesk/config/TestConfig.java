package com.valdir.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.valdir.helpdesk.service.DBService;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBService dBService;

   @PostConstruct
    public void instanciaDB(){
        dBService.instanciaDB();
    }
}
