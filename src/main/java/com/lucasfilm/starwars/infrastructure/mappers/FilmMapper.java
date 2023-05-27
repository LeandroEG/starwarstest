package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.response.FilmResponse;
import com.lucasfilm.starwars.infrastructure.response.FilmsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class FilmMapper {

    public static List<Film> mapToFilms(FilmsResponse filmsResponses) {
        return filmsResponses.getResults().stream()
                .map(FilmMapper::mapToFilm)
                .collect(Collectors.toList());
    }

    private static Film mapToFilm(FilmResponse filmResponse) {
        Film film = new Film();
        film.setTitle(filmResponse.getTitle());
        film.setEpisodeId(filmResponse.getEpisode_id());
        film.setOpeningCrawl(filmResponse.getOpening_crawl());
        film.setDirector(filmResponse.getDirector());
        film.setProducer(filmResponse.getProducer());
        film.setReleaseDate(filmResponse.getRelease_date());
        film.setCharacters(filmResponse.getCharacters());
        film.setPlanets(filmResponse.getPlanets());
        film.setStarships(filmResponse.getStarships());
        film.setVehicles(filmResponse.getVehicles());
        film.setSpecies(filmResponse.getSpecies());
        film.setCreated(filmResponse.getCreated());
        film.setEdited(filmResponse.getEdited());
        film.setUrl(filmResponse.getUrl());

        return film;
    }

    public static List<FilmDTO> mapToFilmDTOList(List<Film> films) {
        return films.stream()
                .map(FilmMapper::mapToFilmDTO)
                .collect(Collectors.toList());
    }

    public static FilmDTO mapToFilmDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(film.getId());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setEpisodeId(film.getEpisodeId());
        filmDTO.setOpeningCrawl(film.getOpeningCrawl());
        filmDTO.setDirector(film.getDirector());
        filmDTO.setProducer(film.getProducer());
        filmDTO.setReleaseDate(film.getReleaseDate());
        filmDTO.setCharacters(film.getCharacters());
        filmDTO.setPlanets(film.getPlanets());
        filmDTO.setStarships(film.getStarships());
        filmDTO.setVehicles(film.getVehicles());
        filmDTO.setSpecies(film.getSpecies());
        filmDTO.setCreated(film.getCreated());
        filmDTO.setEdited(film.getEdited());
        filmDTO.setUrl(film.getUrl());

        return filmDTO;
    }
}
