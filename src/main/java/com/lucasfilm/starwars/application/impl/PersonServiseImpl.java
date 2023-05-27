package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.mappers.PersonMapper;
import com.lucasfilm.starwars.infrastructure.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiseImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiseImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPeopleWithFilmCountAndTitles() {
        // Implementar la lógica para obtener las personas con el número de películas y los títulos
        return null;
    }

    public Person getPersonWithMostFrequentStarship(List<String> selectedFilmTitles) {
        // Implementar la lógica para obtener la persona que conduce la nave más veces en las películas seleccionadas
        return null;
    }

    public List<PersonDTO> getPeople() {
        List<Person> lstPerson = personRepository.findAll();
        return PersonMapper.mapToPersonDTOList(lstPerson);
    }

    public Person getPersonById(int id){
        return null;
    }

    public List<Person> getPeopleByFilm(String filmTitle){
        return null;
    }
}
