package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.mappers.FilmMapper;
import com.lucasfilm.starwars.infrastructure.mappers.PersonMapper;
import com.lucasfilm.starwars.infrastructure.response.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class StarWarsRepository {
    private static final String STAR_WARS_BASE_URL = "https://swapi.py4e.com/api";

    private final RestTemplate restTemplate;

    public final FilmRepository filmRepository;
    public final PersonRepository personRepository;

    public StarWarsRepository(RestTemplate restTemplate, FilmRepository filmRepository, PersonRepository personRepository) {
        this.restTemplate = restTemplate;
        this.filmRepository = filmRepository;
        this.personRepository = personRepository;
    }

    public void importData() {
        importFilms();
        importPeople();
        //importStarships();
    }

    private void importFilms() {
        FilmsResponse filmsResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/films/",
                FilmsResponse.class
        );

        if (filmsResponse != null && filmsResponse.getResults() != null) {
            for (FilmResponse filmResponse : filmsResponse.getResults()) {
                Film film = new Film();
                film.setTitle(filmResponse.getTitle());
            }
        }
        if(filmsResponse !=null) {
            List<Film> listFilms = FilmMapper.mapToFilms(filmsResponse);
            filmRepository.saveAllAndFlush(listFilms);
        }
    }

    private void importPeople() {
        PeopleResponse peopleResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/people/",
                PeopleResponse.class
        );

        if (peopleResponse != null && peopleResponse.getResults() != null) {
            for (PersonResponse personResponse : peopleResponse.getResults()) {
                Person person = new Person();
                person.setName(personResponse.getName());
            }
        }
        if(peopleResponse != null) {
            List<Person> lstPerson = PersonMapper.mapToPeople(peopleResponse);
            personRepository.saveAllAndFlush(lstPerson);
        }
    }

    private void importStarships() {
        StarshipsResponse starshipsResponse = restTemplate.getForObject(
                STAR_WARS_BASE_URL + "/starships/",
                StarshipsResponse.class
        );

        if (starshipsResponse != null && starshipsResponse.getResults() != null) {
            for (StarshipResponse starshipResponse : starshipsResponse.getResults()) {
                Starship starship = new Starship();
                starship.setName(starshipResponse.getName());
            }
        }
    }
}