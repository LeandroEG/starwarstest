package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FilmRepositoryTest {

    @InjectMocks
    private FilmRepository filmRepository;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindByCharacterIdsContaining() {
        // Datos de prueba
        String characterUrl = "https://swapi.py4e.com/api/luke";
        Film film1 = new Film();
        film1.setCharacters(Arrays.asList(characterUrl, "https://swapi.py4e.com/api/leia"));
        Film film2 = new Film();
        film2.setCharacters(Collections.singletonList(characterUrl));
        List<Film> films = Arrays.asList(film1, film2);

        // Llamadas simuladas
        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(films);

        // Método a testear
        List<Film> result = filmRepository.findByCharacterIdsContaining(characterUrl);

        // validaciones
        assertEquals(2, result.size());
        assertEquals(characterUrl, result.get(0).getCharacters().get(0));
        assertEquals("https://swapi.py4e.com/api/leia", result.get(0).getCharacters().get(1));
        assertEquals(characterUrl, result.get(1).getCharacters().get(0));
    }
}

