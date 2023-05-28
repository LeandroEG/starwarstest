package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.FilmService;
import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.mappers.FilmsMapper;
import com.lucasfilm.starwars.infrastructure.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiseImpl implements FilmService {
    @Autowired
    public FilmRepository filmRepository;

    @Autowired
    public FilmsMapper filmsMapper;


    /**
     * Método que devuelte la lista de todas las películas
     * @return List
     *         Listado de todas las películas
     */
    public List<FilmDTO> getFilms() {
        List<Film> lstFilms = filmRepository.findAll();
        return filmsMapper.toDTOList(lstFilms);
    }

    /**
     * Método que devuelve la lista de películas de un personaje
     * @param characterUrl
     *        URL del personaje
     * @return List
     *         Lista de películas por personaje
     */
    public List<Film> findByCharacterIdsContaining(String characterUrl) {
       return filmRepository.findByCharacterIdsContaining(characterUrl);
    }

}
