package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.FilmService;
import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.mappers.FilmMapper;
import com.lucasfilm.starwars.infrastructure.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiseImpl implements FilmService {
    @Autowired
    public FilmRepository filmRepository;


    public List<FilmDTO> getFilms() {
        List<Film> lstFilms = filmRepository.findAll();
        return FilmMapper.mapToFilmDTOList(lstFilms);
    }

}
