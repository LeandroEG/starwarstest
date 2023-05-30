package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.mappers.FilmsMapper;
import com.lucasfilm.starwars.infrastructure.mappers.PeopleMapper;
import com.lucasfilm.starwars.infrastructure.mappers.StarshipsMapper;
import com.lucasfilm.starwars.infrastructure.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @Autowired
    public FilmsMapper filmsMapper;

    @Autowired
    public PeopleMapper peopleMapper;

    @Autowired
    public StarshipsMapper starshipsMapper;

    private static final Logger logger = LoggerFactory.getLogger(StarWarsRepository.class);

    /**
     * Método principal al que se llama cuando arranca el servidor.
     * Realiza las llamadas necesarias para la importación de los datos de la API de Star Wars
     */
    public void importData() {
        logger.info("## START: COMIENZA PROCESO DE IMPORTACIÓN DE DATOS ##");
        importFilms();
        importPeople();
        importStarships();
        logger.info("## END: FINALIZADO PROCESO DE IMPORTACIÓN DE DATOS ##");
    }

    /**
     * Método que importa todas las películas de Star Wars
     */
    private void importFilms() {
        String firstPage = STAR_WARS_BASE_URL + "/films/";
        List<Film> allFilms = new ArrayList<>();
        logger.info("##       -- Importando películas ....");
        getFilmPage(firstPage, allFilms);
        filmRepository.saveAllAndFlush(allFilms);
        logger.info("##       -- Películas importadas correctamente.");
    }

    /**
     * Método que realizada la llamada a la API de Star Wars usando paginación para obtener las películas
     * @param page
     *        URL de la siguiente página para obtener películas
     * @param allFilms
     *        Lista con todas las películas
     */
    private void getFilmPage(String page, List<Film> allFilms) {
        ResponseEntity<FilmsResponse> response = restTemplate.exchange(
                page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<FilmsResponse>() {}
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            FilmsResponse filmsResponse = response.getBody();
            if (filmsResponse != null) {
                allFilms.addAll(filmsMapper.toEntityList(filmsResponse.getResults()));
                if (filmsResponse.getNext() != null) {
                    getFilmPage(filmsResponse.getNext(), allFilms);
                }
            }
        }
    }

    /**
     * Método que importa todos los personajes de Star Wars
     */
    public void importPeople() {
        String firstPage = STAR_WARS_BASE_URL + "/people/";
        List<Person> allPeople = new ArrayList<>();
        logger.info("##       -- Importando personajes ....");
        getPeoplePage(firstPage, allPeople);
        personRepository.saveAllAndFlush(allPeople);
        logger.info("##       -- Personajes importados correctamente.");
    }

    /**
     * Método que realizada la llamada a la API de Star Wars usando paginación para obtener los personajes
     * @param page
     *        URL de la siguiente página para obtener personales
     * @param allPeople
     *        Lista con todos los personajes
     */
    private void getPeoplePage(String page, List<Person> allPeople) {
        ResponseEntity<PeopleResponse> response = restTemplate.exchange(
                page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PeopleResponse>() {}
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            PeopleResponse peopleResponse = response.getBody();
            if (peopleResponse != null) {
                allPeople.addAll(peopleMapper.toEntityList(peopleResponse.getResults()));
                if (peopleResponse.getNext() != null) {
                    getPeoplePage(peopleResponse.getNext(), allPeople);
                }
            }
        }
    }

    /**
     * Método que importa todas las naves de Star Wars
     */
    private void importStarships() {
        String firstPage = STAR_WARS_BASE_URL + "/starships/";
        List<Starship> allStarships = new ArrayList<>();
        logger.info("##       -- Importando naves ....");
        getStarshipsPage(firstPage, allStarships);
        starshipRepository.saveAllAndFlush(allStarships);
        logger.info("##       -- Naves importadas correctamente.");
    }

    /**
     * Método que realizada la llamada a la API de Star Wars usando paginación para obtener las naves
     * @param page
     *        URL de la siguiente página para obtener naves
     * @param allStarships
     *        Lista con todas las naves
     */
    private void getStarshipsPage(String page,  List<Starship> allStarships) {
        ResponseEntity<StarshipsResponse> response = restTemplate.exchange(
                page,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StarshipsResponse>() {}
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            StarshipsResponse starshipsResponse = response.getBody();
            if (starshipsResponse != null) {
                allStarships.addAll(starshipsMapper.toEntityList(starshipsResponse.getResults()));
                if (starshipsResponse.getNext() != null) {
                    getStarshipsPage(starshipsResponse.getNext(), allStarships);
                }
            }
        }
    }
}