package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.StarshipService;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import com.lucasfilm.starwars.infrastructure.mappers.StarshipsMapper;
import com.lucasfilm.starwars.infrastructure.repository.StarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StarshipServiceImpl implements StarshipService {

    @Autowired
    public StarshipRepository starshipRepository;

    @Autowired
    public StarshipsMapper starshipsMapper;

    /**
     * Método que obtiene una nave a partir de su URL
     * @param url
     *        URL de la nave
     * @return StarshipDTO
     *         Objeto StarshipDTO
     */
    @Override
    public StarshipDTO getStarshipByUrl(String url) {
        Optional<Starship> optionalStarship = starshipRepository.findByUrl(url);
        if (optionalStarship.isPresent()) {
            return starshipsMapper.toDto(optionalStarship.get());
        } else {
            throw new NoSuchElementException("No se ha encontrado ninguna nave");
        }
    }
}
