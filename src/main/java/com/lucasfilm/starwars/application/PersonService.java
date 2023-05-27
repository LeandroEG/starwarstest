package com.lucasfilm.starwars.application;


import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getPeople();

    Person getPersonById(int id);

    List<Person> getPeopleByFilm(String filmTitle);
}
