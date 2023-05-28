package com.lucasfilm.starwars.infrastructure.rest;

import com.lucasfilm.starwars.application.StarwarsService;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonFilmsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StarwarsControllerTest {

    @InjectMocks
    private StarwarsController starwarsController;

    @Mock
    private StarwarsService starwarsService;

    @Test
    public void testGetPeopleWithFilmCountAndTitles() {
        // Datos de prueba
        List<PersonFilmsDTO> expectedList = new ArrayList<>();

        // Llamadas simuladas
        when(starwarsService.getPeopleWithFilmCountAndTitles()).thenReturn(expectedList);

        // Método a testear
        List<PersonFilmsDTO> result = starwarsController.getPeopleWithFilmCountAndTitles();

        // validaciones
        assertEquals(expectedList, result);
        verify(starwarsService).getPeopleWithFilmCountAndTitles();
    }

    @Test
    public void testGetAllFilms() {
        // Datos de prueba
        List<FilmDTO> expectedList = new ArrayList<>();

        // Llamadas simuladas
        when(starwarsService.getFilms()).thenReturn(expectedList);

        // Método a testear
        List<FilmDTO> result = starwarsController.getallFilms();

        // validaciones
        assertEquals(expectedList, result);
        verify(starwarsService).getFilms();
    }

    @Test
    public void testFindMostFrequentStarshipPilot() {

        // Datos de prueba
        List<FilmDTO> lstFilms = new ArrayList<>();
        String expectedResponse = "Luke Skywalker";

        // Llamadas simuladas
        when(starwarsService.findMostFrequentStarshipPilot(lstFilms)).thenReturn(expectedResponse);

        // Método a testear
        String result = starwarsController.findMostFrequentStarshipPilot(lstFilms);

        // validaciones
        assertEquals(expectedResponse, result);
        verify(starwarsService).findMostFrequentStarshipPilot(lstFilms);
    }
}
