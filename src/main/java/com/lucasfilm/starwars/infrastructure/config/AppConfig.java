package com.lucasfilm.starwars.infrastructure.config;

import com.lucasfilm.starwars.infrastructure.repository.StarWarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class AppConfig {
    @Autowired
    public StarWarsRepository starWarsRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void importDataOnStartup() {
        starWarsRepository.importData();
    }
}
