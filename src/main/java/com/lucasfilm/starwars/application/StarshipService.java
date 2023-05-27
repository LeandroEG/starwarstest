package com.lucasfilm.starwars.application;

import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;

import java.util.List;

public interface StarshipService {

    StarshipDTO getStarshipByUrl(String url);
}
