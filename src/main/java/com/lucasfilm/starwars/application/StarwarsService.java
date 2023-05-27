package com.lucasfilm.starwars.application;


import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.StarWarsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
public class StarwarsService {

    private final StarWarsRepository starwarsRepository;

    public StarwarsService(StarWarsRepository starwarsRepository) {
        this.starwarsRepository = starwarsRepository;
    }

    public void importData() {
        starwarsRepository.importData();
    }

    public List<Person> getPeopleWithFilmCountAndTitles() {
        // Implementar la lógica para obtener las personas con el número de películas y los títulos
        return null;
    }

    public Person getPersonWithMostFrequentStarship(List<String> selectedFilmTitles) {
        // Implementar la lógica para obtener la persona que conduce la nave más veces en las películas seleccionadas
        return null;
    }
}
