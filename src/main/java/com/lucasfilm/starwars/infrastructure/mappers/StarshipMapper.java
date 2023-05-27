package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import com.lucasfilm.starwars.infrastructure.response.StarshipResponse;
import com.lucasfilm.starwars.infrastructure.response.StarshipsResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StarshipMapper {

    public static List<Starship> mapToStarships(StarshipsResponse starshipsResponses) {
        return starshipsResponses.getResults().stream()
                .map(StarshipMapper::mapToStarship)
                .collect(Collectors.toList());
    }

    public static Starship mapToStarship(StarshipResponse response) {
        Starship starship = new Starship();
        starship.setName(response.getName());
        starship.setModel(response.getModel());
        starship.setManufacturer(response.getManufacturer());
        starship.setCostInCredits(response.getCostInCredits());
        starship.setLength(response.getLength());
        starship.setMaxAtmospheringSpeed(response.getMaxAtmospheringSpeed());
        starship.setCrew(response.getCrew());
        starship.setPassengers(response.getPassengers());
        starship.setCargoCapacity(response.getCargoCapacity());
        starship.setConsumables(response.getConsumables());
        starship.setHyperdriveRating(response.getHyperdriveRating());
        starship.setMGLT(response.getMGLT());
        starship.setStarshipClass(response.getStarshipClass());
        starship.setPilots(response.getPilots());
        starship.setFilms(response.getFilms());
        starship.setCreated(response.getCreated());
        starship.setEdited(response.getEdited());
        starship.setUrl(response.getUrl());
        return starship;
    }

    public static StarshipDTO toStarshipDTO(Starship starship) {
        StarshipDTO starshipDTO = new StarshipDTO();
        starshipDTO.setName(starship.getName());
        starshipDTO.setModel(starship.getModel());
        starshipDTO.setManufacturer(starship.getManufacturer());
        starshipDTO.setCostInCredits(starship.getCostInCredits());
        starshipDTO.setLength(starship.getLength());
        starshipDTO.setMaxAtmospheringSpeed(starship.getMaxAtmospheringSpeed());
        starshipDTO.setCrew(starship.getCrew());
        starshipDTO.setPassengers(starship.getPassengers());
        starshipDTO.setCargoCapacity(starship.getCargoCapacity());
        starshipDTO.setConsumables(starship.getConsumables());
        starshipDTO.setHyperdriveRating(starship.getHyperdriveRating());
        starshipDTO.setMGLT(starship.getMGLT());
        starshipDTO.setStarshipClass(starship.getStarshipClass());
        starshipDTO.setPilots(starship.getPilots());
        starshipDTO.setFilms(starship.getFilms());
        starshipDTO.setCreated(starship.getCreated());
        starshipDTO.setEdited(starship.getEdited());
        starshipDTO.setUrl(starship.getUrl());
        return starshipDTO;
    }

    public List<StarshipDTO> toStarshipDTOList(List<Starship> starships) {
        return starships.stream()
                .map(StarshipMapper::toStarshipDTO)
                .collect(Collectors.toList());
    }
}






