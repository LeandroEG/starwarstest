package com.lucasfilm.starwars.infrastructure.rest;

import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@Api(tags = "Personajes")
@ApiOperation("Operaciones con los personajes de Star Wars")
public class PersonController {

    @Autowired
    public PersonService personService;

    @GetMapping
    @ApiOperation("Obtiene el listado de todos los personajes de Star Wars")
    public List<PersonDTO> getAllPeople() {
        return personService.getPeople();
    }
}
