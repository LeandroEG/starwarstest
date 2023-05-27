package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.response.PeopleResponse;
import com.lucasfilm.starwars.infrastructure.response.PersonResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public static PersonDTO mapToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setHeight(person.getHeight());
        personDTO.setMass(person.getMass());
        personDTO.setHairColor(person.getHairColor());
        personDTO.setSkinColor(person.getSkinColor());
        personDTO.setEyeColor(person.getEyeColor());
        personDTO.setBirthYear(person.getBirthYear());
        personDTO.setGender(person.getGender());
        personDTO.setHomeworld(person.getHomeworld());
        personDTO.setFilms(person.getFilms());
        personDTO.setSpecies(person.getSpecies());
        personDTO.setVehicles(person.getVehicles());
        personDTO.setStarships(person.getStarships());
        personDTO.setCreated(person.getCreated());
        personDTO.setEdited(person.getEdited());
        personDTO.setUrl(person.getUrl());
        return personDTO;
    }

    public static List<PersonDTO> mapToPersonDTOList(List<Person> people) {
        return people.stream()
                .map(PersonMapper::mapToPersonDTO)
                .collect(Collectors.toList());
    }

    public static List<Person> mapToPeople(PeopleResponse peopleResponses) {
        return peopleResponses.getResults().stream()
                .map(PersonMapper::mapToPerson)
                .collect(Collectors.toList());
    }

    private static Person mapToPerson(PersonResponse personResponse) {
        Person person = new Person();
        person.setName(personResponse.getName());
        person.setHeight(personResponse.getHeight());
        person.setMass(personResponse.getMass());
        person.setHairColor(personResponse.getHairColor());
        person.setSkinColor(personResponse.getSkinColor());
        person.setEyeColor(personResponse.getEyeColor());
        person.setBirthYear(personResponse.getBirthYear());
        person.setGender(personResponse.getGender());
        person.setHomeworld(personResponse.getHomeworld());
        person.setFilms(personResponse.getFilms());
        person.setSpecies(personResponse.getSpecies());
        person.setVehicles(personResponse.getVehicles());
        person.setStarships(personResponse.getStarships());
        person.setCreated(personResponse.getCreated());
        person.setEdited(personResponse.getEdited());
        person.setUrl(personResponse.getUrl());
        return person;
    }
}