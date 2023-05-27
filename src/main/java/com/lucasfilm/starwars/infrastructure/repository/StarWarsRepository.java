package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.mappers.FilmMapper;
import com.lucasfilm.starwars.infrastructure.mappers.PersonMapper;
import com.lucasfilm.starwars.infrastructure.mappers.StarshipMapper;
import com.lucasfilm.starwars.infrastructure.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class StarWarsRepository {
    private static final String STAR_WARS_BASE_URL = "https://swapi.py4e.com/api";
    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public FilmRepository filmRepository;
    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public StarshipRepository starshipRepository;

    public void importData() {
        importFilms();
        importPeople();
        importStarships();
    }

    private void importFilms() {
        FilmsResponse filmsResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/films/",
                FilmsResponse.class
        );

        if(filmsResponse !=null && filmsResponse.getResults() != null) {
            List<Film> listFilms = FilmMapper.mapToFilms(filmsResponse);
            filmRepository.saveAllAndFlush(listFilms);
        }
    }

    private void importPeople() {
        PeopleResponse peopleResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/people/",
                PeopleResponse.class
        );

        if(peopleResponse != null && peopleResponse.getResults() != null) {
            List<Person> lstPerson = PersonMapper.mapToPeople(peopleResponse);
            personRepository.saveAllAndFlush(lstPerson);
        }
    }

    private void importStarships() {
        StarshipsResponse starshipsResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/starships/",
                StarshipsResponse.class
        );

        if(starshipsResponse != null && starshipsResponse.getResults() != null) {
            List<Starship> lstStarships = StarshipMapper.mapToStarships(starshipsResponse);
            starshipRepository.saveAllAndFlush(lstStarships);
        }
    }
}