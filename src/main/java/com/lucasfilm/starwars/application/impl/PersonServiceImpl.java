package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.mappers.PeopleMapper;
import com.lucasfilm.starwars.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public PeopleMapper peopleMapper;

    /**
     * Método que obtiene la lista de todos los personajes
     * @return List
     *         Listado de todos los personajes
     */
    public List<PersonDTO> getPeople() {
        List<Person> lstPerson = personRepository.findAll();
        return peopleMapper.toDTOList(lstPerson);
    }

    /**
     * Método que obtiene un personajes a partir de su URL
     * @param url
     *        URL del personaje
     * @return PersonDTO
     *         Objeto personajeDTO
     */
    public PersonDTO getPersonByUrl(String url) {
        Optional<Person> optionalPerson = personRepository.findByUrl(url);
        if (optionalPerson.isPresent()) {
            return peopleMapper.toDto(optionalPerson.get());
        } else {
            throw new NoSuchElementException("No se ha encontrado ningún personaje: ");
        }
    }
}
