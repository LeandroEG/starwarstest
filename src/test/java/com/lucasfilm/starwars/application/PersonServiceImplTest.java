package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.application.impl.PersonServiceImpl;
import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.mappers.PeopleMapper;
import com.lucasfilm.starwars.infrastructure.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PeopleMapper peopleMapper;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void testGetPeople() {
        // Datos de prueba
        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        personList.add(new Person());

        List<PersonDTO> personDTOList = new ArrayList<>();
        personDTOList.add(new PersonDTO());
        personDTOList.add(new PersonDTO());

        // Llamadas simuladas
        when(personRepository.findAll()).thenReturn(personList);
        when(peopleMapper.toDTOList(personList)).thenReturn(personDTOList);

        // Método a testear
        List<PersonDTO> result = personService.getPeople();

        // validaciones
        assertEquals(personDTOList.size(), result.size());
        verify(personRepository, times(1)).findAll();
        verify(peopleMapper, times(1)).toDTOList(personList);
    }

    @Test
    public void testGetPersonByUrl_ExistingPerson() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/person/1";
        Person person = new Person();
        PersonDTO personDTO = new PersonDTO();

        // Llamadas simuladas
        when(personRepository.findByUrl(url)).thenReturn(Optional.of(person));
        when(peopleMapper.toDto(person)).thenReturn(personDTO);

        // Método a testear
        PersonDTO result = personService.getPersonByUrl(url);

        // validaciones
        assertNotNull(result);
        assertEquals(personDTO, result);
        verify(personRepository, times(1)).findByUrl(url);
        verify(peopleMapper, times(1)).toDto(person);
    }

    @Test
    public void testGetPersonByUrl_NonExistingPerson() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/person/1";

        // Llamadas simuladas
        when(personRepository.findByUrl(url)).thenReturn(Optional.empty());

        // validaciones
        assertThrows(NoSuchElementException.class, () -> personService.getPersonByUrl(url));
        verify(personRepository, times(1)).findByUrl(url);
        verifyNoInteractions(peopleMapper);
    }
}

