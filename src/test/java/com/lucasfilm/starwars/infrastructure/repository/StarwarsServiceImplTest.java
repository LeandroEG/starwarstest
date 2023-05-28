package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.application.FilmService;
import com.lucasfilm.starwars.application.PersonService;
import com.lucasfilm.starwars.application.StarshipService;
import com.lucasfilm.starwars.application.impl.StarwarsServiceImpl;
import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonFilmsDTO;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StarwarsServiceImplTest {

    @InjectMocks
    private StarwarsServiceImpl starwarsService;

    @Mock
    private PersonService personService;

    @Mock
    private FilmService filmService;

    @Mock
    private StarshipService starshipService;

    @Test
    public void testGetPeopleWithFilmCountAndTitles() {
        // Datos de prueba
        PersonDTO person1 = new PersonDTO();
        person1.setName("Luke Skywalker");
        person1.setUrl("https://swapi.py4e.com/api/luke");
        PersonDTO person2 = new PersonDTO();
        person2.setName("Leia Organa");
        person2.setUrl("https://swapi.py4e.com/api/leia");

        Film film1 = new Film();
        film1.setTitle("A New Hope");
        Film film2 = new Film();
        film2.setTitle("The Empire Strikes Back");

        List<PersonDTO> people = Arrays.asList(person1, person2);
        List<Film> films = Arrays.asList(film1, film2);

        // Llamadas simuladas
        when(personService.getPeople()).thenReturn(people);
        when(filmService.findByCharacterIdsContaining(anyString())).thenReturn(films);

        // Método a testear
        List<PersonFilmsDTO> result = starwarsService.getPeopleWithFilmCountAndTitles();

        // Validaciones
        assertEquals(2, result.size());
        assertEquals("Luke Skywalker", result.get(0).getName());
        assertEquals(2, result.get(0).getFilmCount());
        assertEquals(Arrays.asList("A New Hope", "The Empire Strikes Back"), result.get(0).getFilmTitles());
        assertEquals("Leia Organa", result.get(1).getName());
        assertEquals(2, result.get(1).getFilmCount());
        assertEquals(Arrays.asList("A New Hope", "The Empire Strikes Back"), result.get(1).getFilmTitles());
    }

    @Test
    public void testGetFilms() {
        // Datos de prueba
        FilmDTO film1 = new FilmDTO();
        film1.setTitle("A New Hope");
        FilmDTO film2 = new FilmDTO();
        film2.setTitle("The Empire Strikes Back");

        List<FilmDTO> films = Arrays.asList(film1, film2);

        // Mocking service method
        when(filmService.getFilms()).thenReturn(films);

        // Método a testear
        List<FilmDTO> result = starwarsService.getFilms();

        // Validaciones
        assertEquals(2, result.size());
        assertEquals("A New Hope", result.get(0).getTitle());
        assertEquals("The Empire Strikes Back", result.get(1).getTitle());
    }

    @Test
    public void testFindMostFrequentStarshipPilot() {
        // Datos de prueba
        FilmDTO film1 = new FilmDTO();
        film1.setStarships(Arrays.asList("https://swapi.py4e.com/api/starship1", "https://swapi.py4e.com/api/starship2"));
        FilmDTO film2 = new FilmDTO();
        film2.setStarships(Collections.singletonList("https://swapi.py4e.com/api/starship1"));

        StarshipDTO starship1 = new StarshipDTO();
        starship1.setPilots(Arrays.asList("https://swapi.py4e.com/api/pilot1", "https://swapi.py4e.com/api/pilot2"));
        StarshipDTO starship2 = new StarshipDTO();
        starship2.setPilots(Collections.singletonList("https://swapi.py4e.com/api/pilot1"));

        PersonDTO pilot1 = new PersonDTO();
        pilot1.setName("Han Solo");
        PersonDTO pilot2 = new PersonDTO();
        pilot2.setName("Chewbacca");

        List<FilmDTO> films = Arrays.asList(film1, film2);

        // Llamadas simuladas
        when(starshipService.getStarshipByUrl("https://swapi.py4e.com/api/starship1")).thenReturn(starship1);
        when(starshipService.getStarshipByUrl("https://swapi.py4e.com/api/starship2")).thenReturn(starship2);
        when(personService.getPersonByUrl("https://swapi.py4e.com/api/pilot1")).thenReturn(pilot1);
        when(personService.getPersonByUrl("https://swapi.py4e.com/api/pilot2")).thenReturn(pilot2);

        // Método a testear
        String result = starwarsService.findMostFrequentStarshipPilot(films);

        // Validaciones
        assertEquals("El personaje que conduce la nave que más veces aparece en la lista de películas es: Han Solo", result);
    }
}

