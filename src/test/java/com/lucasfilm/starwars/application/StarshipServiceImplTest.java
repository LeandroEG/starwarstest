package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.application.impl.StarshipServiceImpl;
import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import com.lucasfilm.starwars.infrastructure.mappers.StarshipsMapper;
import com.lucasfilm.starwars.infrastructure.repository.StarshipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StarshipServiceImplTest {

    @Mock
    private StarshipRepository starshipRepository;

    @Mock
    private StarshipsMapper starshipsMapper;

    @InjectMocks
    private StarshipServiceImpl starshipService;

    @Test
    public void testGetStarshipByUrl_ExistingStarship() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/starship/1";
        Starship starship = new Starship();
        StarshipDTO starshipDTO = new StarshipDTO();

        // Llamadas simuladas
        when(starshipRepository.findByUrl(url)).thenReturn(Optional.of(starship));
        when(starshipsMapper.toDto(starship)).thenReturn(starshipDTO);

        // Método a testear
        StarshipDTO result = starshipService.getStarshipByUrl(url);

        // validaciones
        assertNotNull(result);
        assertEquals(starshipDTO, result);
        verify(starshipRepository, times(1)).findByUrl(url);
        verify(starshipsMapper, times(1)).toDto(starship);
    }

    @Test
    public void testGetStarshipByUrl_NonExistingStarship() {
        // Datos de prueba
        String url = "https://swapi.py4e.com/api/starship/1";

        // Llamadas simuladas
        when(starshipRepository.findByUrl(url)).thenReturn(Optional.empty());

        // validaciones
        assertThrows(NoSuchElementException.class, () -> starshipService.getStarshipByUrl(url));
        verify(starshipRepository, times(1)).findByUrl(url);
        verifyNoInteractions(starshipsMapper);
    }
}
