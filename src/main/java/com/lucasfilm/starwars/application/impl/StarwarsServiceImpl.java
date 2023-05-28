package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.FilmService;
import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.application.StarshipService;
import com.lucasfilm.starwars.application.StarwarsService;
import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonFilmsDTO;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StarwarsServiceImpl implements StarwarsService  {

    private static final String TEXT_RESPONSE = "El personaje que conduce la nave que más veces aparece en la lista de películas es: ";
    @Autowired
    public PersonService personService;
    @Autowired
    public FilmService filmService;

    @Autowired
    public StarshipService starshipService;


    /**
     * Método que obtiene la lista de personajes junto con las peículas en las que aparece
     * @return List
     *         Lista de personajes con sus películas
     */
    public List<PersonFilmsDTO> getPeopleWithFilmCountAndTitles() {
        List<PersonDTO> people = personService.getPeople();
        List<PersonFilmsDTO> personFilmsDTOList = new ArrayList<>();

        for (PersonDTO person : people) {
            List<Film> films = filmService.findByCharacterIdsContaining(person.getUrl());

            List<String> filmTitles = films.stream()
                    .map(Film::getTitle)
                    .collect(Collectors.toList());

            int filmCount = films.size();

            PersonFilmsDTO personFilmsDTO = new PersonFilmsDTO();
            personFilmsDTO.setName(person.getName());
            personFilmsDTO.setFilmCount(filmCount);
            personFilmsDTO.setFilmTitles(filmTitles);

            personFilmsDTOList.add(personFilmsDTO);
        }

        return personFilmsDTOList;
    }

    /**
     * Método que devuelve todas las películas
     * @return List
     *         Listado de todas las películas
     */
    public List<FilmDTO> getFilms() {
        return filmService.getFilms();
    }

    /**
     * Método que devuelve el personaje que conduce la nave que más aparece en un listado de películas
     * @param films
     *        Listado de películas
     * @return String
     *         Nombre del personaje
     */
    public String findMostFrequentStarshipPilot(List<FilmDTO> films) {
        Map<String, Integer> starshipPilotsCount = new HashMap<>();

        for (FilmDTO film : films) {
            for (String starshipUrl : film.getStarships()) {
                StarshipDTO starship = starshipService.getStarshipByUrl(starshipUrl);
                for (String pilotUrl : starship.getPilots()) {
                    PersonDTO pilot = personService.getPersonByUrl(pilotUrl);
                    starshipPilotsCount.put(pilot.getName(), starshipPilotsCount.getOrDefault(pilot.getName(), 0) + 1);
                }
            }
        }

        String mostFrequentPilot = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : starshipPilotsCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentPilot = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return TEXT_RESPONSE + mostFrequentPilot;
    }
}
