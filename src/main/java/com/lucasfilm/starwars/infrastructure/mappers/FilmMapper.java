package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.response.FilmResponse;
import com.lucasfilm.starwars.infrastructure.response.FilmsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class FilmMapper {

    public static List<Film> mapToFilms(FilmsResponse filmsResponses) {
        return filmsResponses.getResults().stream()
                .map(FilmMapper::mapToFilm)
                .collect(Collectors.toList());
    }

    private static Film mapToFilm(FilmResponse filmResponse) {
        Film film = new Film();
        film.setTitle(filmResponse.getTitle());
        return film;
    }
}
