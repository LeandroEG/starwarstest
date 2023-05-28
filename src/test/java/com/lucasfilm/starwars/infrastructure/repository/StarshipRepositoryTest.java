package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Starship;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StarshipRepositoryTest {

    @InjectMocks
    private StarshipRepository starshipRepository;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindByUrl() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/starship";
        Starship starship = new Starship();
        starship.setUrl(url);

        // Llamadas simuladas
        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(starship);

        // Método a testear
        Optional<Starship> result = starshipRepository.findByUrl(url);

        // validaciones
        assertTrue(result.isPresent());
        assertEquals(url, result.get().getUrl());
    }

    @Test
    public void testFindByUrl_NotFound() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/starship";

        // Llamadas simuladas
        Query query = mock(Query.class);
        when(entityManager.createQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), any())).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        // Método a testear
        Optional<Starship> result = starshipRepository.findByUrl(url);

        // validaciones
        assertFalse(result.isPresent());
    }
}
