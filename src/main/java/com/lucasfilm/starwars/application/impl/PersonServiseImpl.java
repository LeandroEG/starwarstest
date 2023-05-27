package com.lucasfilm.starwars.application.impl;

import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.mappers.PersonMapper;
import com.lucasfilm.starwars.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonServiseImpl implements PersonService {

    @Autowired
    public PersonRepository personRepository;


    public List<PersonDTO> getPeople() {
        List<Person> lstPerson = personRepository.findAll();
        return PersonMapper.mapToPersonDTOList(lstPerson);
    }

    public PersonDTO getPersonByUrl(String url) {
        Optional<Person> optionalPerson = personRepository.findByUrl(url);
        if (optionalPerson.isPresent()) {
            return PersonMapper.mapToPersonDTO(optionalPerson.get());
        } else {
            throw new NoSuchElementException("No se ha encontrado ningún personaje: ");
        }
    }
}
