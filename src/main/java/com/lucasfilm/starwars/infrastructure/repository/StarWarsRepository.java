package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.mappers.FilmMapper;
import com.lucasfilm.starwars.infrastructure.mappers.PersonMapper;
import com.lucasfilm.starwars.infrastructure.mappers.StarshipMapper;
import com.lucasfilm.starwars.infrastructure.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
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

    private static final Logger logger = LoggerFactory.getLogger(StarWarsRepository.class);

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

    /*
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
    */

    public void importPeople() {
        List<Person> allPeople = new ArrayList<>();
        int page = 1;
        List<Person> currentPage;

        do {
            currentPage = getPeoplePage(page);
            if (currentPage.isEmpty()) {
                break;  // Salir del bucle si la página actual está vacía
            }
            allPeople.addAll(currentPage);
            page++;
        } while (currentPage.size() > 0);

        personRepository.saveAllAndFlush(allPeople);
    }

    private List<Person> getPeoplePage(int page) {
        try {
            ResponseEntity<PeopleResponse> response = restTemplate.exchange(
                    STAR_WARS_BASE_URL + "/people/?page=" + page,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<PeopleResponse>() {}
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                PeopleResponse swapiResponse = response.getBody();
                if (swapiResponse != null) {
                    return PersonMapper.mapToPeople(swapiResponse);
                }
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException exp) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
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