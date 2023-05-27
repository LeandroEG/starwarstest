package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonFilmsDTO;

import java.util.List;

public interface StarwarsService {

    List<PersonFilmsDTO> getPeopleWithFilmCountAndTitles();

    List<FilmDTO> getFilms();

    String findMostFrequentStarshipPilot(List<FilmDTO> films);
}
