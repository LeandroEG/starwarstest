package com.lucasfilm.starwars.infrastructure.rest;

import com.lucasfilm.starwars.application.StarwarsService;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.dto.PersonFilmsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/starwars")
@Api(tags = "Star Wars")
public class StarwarsController {
    @Autowired
    public StarwarsService starwarsService;

    /**
     * Método que devuelve todos los personajes con el nº de peliculas en las que aparece y el listado de sus títulos
     * @return List
     *         Lista con los personajes y las películas en las que aparecen
     */
    @GetMapping("/characters/characters-films")
    @ApiOperation("Método que devuelve todos los personajes con el nº de peliculas en las que aparece" +
            " y el listado de sus títulos")
    public List<PersonFilmsDTO> getPeopleWithFilmCountAndTitles() {
        return starwarsService.getPeopleWithFilmCountAndTitles();
    }

    /**
     * Método que devuelve el listado de todas las películas
     * @return List
     *         Lista de todas las películas
     */
    @GetMapping("/films/all-films")
    @ApiOperation("Método que devuelve el listado de todas las películas")
    public List<FilmDTO> getallFilms() {
        return starwarsService.getFilms();
    }

    /**
     * Método que devuelve el personaje que conduce la nave que más veces aparece en un listado de películas.
     * @param lstFilms
     *        Lista de peliculas a consultar
     * @return String
     *         Devuelve el personaje que conduce la nave que más veces aparece en el listado de películas
     */
    @PostMapping("/characters/characters-drive-starship")
    @ApiOperation("Método que devuelve el personaje que conduce la nave que más veces aparece en un " +
            "listado de películas.")
    public String findMostFrequentStarshipPilot(@RequestBody List<FilmDTO> lstFilms) {
        return starwarsService.findMostFrequentStarshipPilot(lstFilms);
    }
}
