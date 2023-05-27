package com.lucasfilm.starwars.infrastructure;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class StarWarsRepository {
    private static final String STAR_WARS_BASE_URL = "https://swapi.py4e.com/api";

    private final RestTemplate restTemplate;

    public StarWarsRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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

        if (filmsResponse != null && filmsResponse.getResults() != null) {
            for (FilmResponse filmResponse : filmsResponse.getResults()) {
                Film film = new Film();
                film.setTitle(filmResponse.getTitle());
                // Guardar en la base de datos
            }
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
                // Guardar en la base de datos
            }
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
                // Guardar en la base de datos
            }
        }
    }
}