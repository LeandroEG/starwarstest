package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import java.util.List;

public interface FilmService {

    List<FilmDTO> getFilms();

}
