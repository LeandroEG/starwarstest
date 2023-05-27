/*
package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.repository.StarWarsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class StarwarsServiceTest {

    @Mock
    private StarWarsRepository starwarsRepository;

    private StarwarsService starwarsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        starwarsService = new StarwarsService(starwarsRepository);
    }

    @Test
    void testImportData() {
        // Arrange

        // Act
        starwarsService.importData();

        // Assert
        verify(starwarsRepository, times(1)).importData();
    }

    @Test
    void testGetPeopleWithFilmCountAndTitles() {
        // Arrange
        List<Person> people = new ArrayList<>();
        Person luke = new Person();
        luke.setName("Luke Skywalker");

        Person leia = new Person();
        leia.setName("Luke Skywalker");

        Person hansolo = new Person();
        hansolo.setName("Luke Skywalker");

        people.add(luke);
        people.add(leia);
        people.add(hansolo);

        when(starwarsRepository.getPeople()).thenReturn(people);
        when(starwarsRepository.getFilmCountByPerson(any())).thenReturn(2);
        when(starwarsRepository.getFilmTitlesByPerson(any())).thenReturn(List.of("A New Hope", "The Empire Strikes Back"));

        // Act
        List<Person> result = starwarsService.getPeopleWithFilmCountAndTitles();

        // Assert
        assertEquals(3, result.size());
        assertEquals("Luke Skywalker", result.get(0).getName());
        assertEquals(2, result.get(0).getFilmCount());
        assertEquals(List.of("A New Hope", "The Empire Strikes Back"), result.get(0).getFilmTitles());
    }

    @Test
    void testGetPersonWithMostFrequentStarship() {
        // Arrange
        List<String> selectedFilmTitles = List.of("A New Hope", "The Empire Strikes Back");

        when(starwarsRepository.getPersonWithMostFrequentStarship(any())).thenReturn(new Person("Han Solo"));

        // Act
        Person result = starwarsService.getPersonWithMostFrequentStarship(selectedFilmTitles);

        // Assert
        assertEquals("Han Solo", result.getName());
    }
}
*/
