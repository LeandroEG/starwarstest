package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Starship;
import com.lucasfilm.starwars.infrastructure.dto.StarshipDTO;
import com.lucasfilm.starwars.infrastructure.response.StarshipResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StarshipsMapper {
    StarshipsMapper INSTANCE = Mappers.getMapper(StarshipsMapper.class);
    StarshipDTO toDto(Starship starship);

    @Mapping(target = "id", ignore = true)
    Starship toEntity(StarshipResponse starshipResponse);

    List<Starship> toEntityList(List<StarshipResponse> starshipsResponse);
}
