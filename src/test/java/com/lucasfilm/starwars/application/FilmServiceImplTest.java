package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.application.impl.FilmServiceImpl;
import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.mappers.FilmsMapper;
import com.lucasfilm.starwars.infrastructure.repository.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class FilmServiceImplTest {
    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmsMapper filmsMapper;

    @InjectMocks
    private FilmServiceImpl filmService;

    @Test
    public void testGetFilms() {
        Film firstFilm = new Film();
        firstFilm.setId(1L);
        firstFilm.setTitle("The Empire Strikes Back");

        Film secondFilm = new Film();
        secondFilm.setId(1L);
        secondFilm.setTitle("A New Hope");

        FilmDTO firstFilmDTO = new FilmDTO();
        firstFilmDTO.setId(1L);
        firstFilmDTO.setTitle("The Empire Strikes Back");

        FilmDTO secondFilmDTO = new FilmDTO();
        secondFilmDTO.setId(1L);
        secondFilmDTO.setTitle("A New Hope");

        // Datos de prueba
        List<Film> mockFilms = Arrays.asList(firstFilm, secondFilm);
        List<FilmDTO> mockFilmDTOs = Arrays.asList(firstFilmDTO, secondFilmDTO);

        // Llamadas simuladas
        when(filmRepository.findAll()).thenReturn(mockFilms);
        when(filmsMapper.toDTOList(mockFilms)).thenReturn(mockFilmDTOs);

        // Método a testear
        List<FilmDTO> result = filmService.getFilms();

        // Validaciones
        assertEquals(mockFilmDTOs, result);
    }

    @Test
    public void testFindByCharacterIdsContaining() {

        // Datos de prueba
        String characterUrl = "https://swapi.py4e.com/api/character/1";
        Film firstFilm = new Film();
        firstFilm.setId(1L);
        firstFilm.setTitle("The Empire Strikes Back");

        Film secondFilm = new Film();
        secondFilm.setId(1L);
        secondFilm.setTitle("The Empire Strikes Back");

        List<Film> mockFilms = Arrays.asList(firstFilm, secondFilm);

        // Llamadas simuladas
        when(filmRepository.findByCharacterIdsContaining(characterUrl)).thenReturn(mockFilms);

        // Método a testear
        List<Film> result = filmService.findByCharacterIdsContaining(characterUrl);

        // Validaciones
        assertEquals(mockFilms, result);
    }
}

