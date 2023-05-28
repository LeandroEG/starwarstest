package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
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
        logger.info("## START: COMIENZA PROCESO DE IMPORTACIÓN DE DATOS ##");
        importFilms();
        importPeople();
        importStarships();
        logger.info("## END: FINALIZADO PROCESO DE IMPORTACIÓN DE DATOS ##");
    }

    private void importFilms() {
        List<Film> allFilms = new ArrayList<>();
        int page = 1;
        List<Film> currentPage;
        logger.info("##       -- Importando películas .... ##");
        do {
            currentPage = getFilmPage(page);
            if (currentPage.isEmpty()) {
                break;
            }
            allFilms.addAll(currentPage);
            page++;
        } while (currentPage.size() > 0);

        filmRepository.saveAllAndFlush(allFilms);
        logger.info("##       -- Películas importadas correctamente. ##");
    }

    private List<Film> getFilmPage(int page) {
        try {
            ResponseEntity<FilmsResponse> response = restTemplate.exchange(
                    STAR_WARS_BASE_URL + "/films/?page=" + page,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<FilmsResponse>() {}
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                FilmsResponse fimlsResponse = response.getBody();
                if (fimlsResponse != null) {
                    return FilmMapper.mapToFilms(fimlsResponse);
                }
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException exp) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    public void importPeople() {
        List<Person> allPeople = new ArrayList<>();
        int page = 1;
        List<Person> currentPage;
        logger.info("##       -- Importando personajes .... ##");
        do {
            currentPage = getPeoplePage(page);
            if (currentPage.isEmpty()) {
                break;
            }
            allPeople.addAll(currentPage);
            page++;
        } while (currentPage.size() > 0);

        personRepository.saveAllAndFlush(allPeople);
        logger.info("##       -- Personajes importados correctamente. ##");
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
                PeopleResponse peopleResponse = response.getBody();
                if (peopleResponse != null) {
                    return PersonMapper.mapToPeople(peopleResponse);
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
        List<Starship> allStarships = new ArrayList<>();
        int page = 1;
        List<Starship> currentPage;
        logger.info("##       -- Importando naves .... ##");
        do {
            currentPage = getStarshipsPage(page);
            if (currentPage.isEmpty()) {
                break;
            }
            allStarships.addAll(currentPage);
            page++;
        } while (currentPage.size() > 0);

        starshipRepository.saveAllAndFlush(allStarships);
        logger.info("##       -- Naves importadas correctamente. ##");
    }

    private List<Starship> getStarshipsPage(int page) {
        try {
            ResponseEntity<StarshipsResponse> response = restTemplate.exchange(
                    STAR_WARS_BASE_URL + "/starships/?page=" + page,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<StarshipsResponse>() {}
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                StarshipsResponse starshipsResponse = response.getBody();
                if (starshipsResponse != null) {
                    return StarshipMapper.mapToStarships(starshipsResponse);
                }
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Collections.emptyList();
            }
        } catch (HttpClientErrorException exp) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }
}