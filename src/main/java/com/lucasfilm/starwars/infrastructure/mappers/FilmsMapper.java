package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Film;
import com.lucasfilm.starwars.infrastructure.dto.FilmDTO;
import com.lucasfilm.starwars.infrastructure.response.FilmResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmsMapper {
    FilmsMapper INSTANCE = Mappers.getMapper(FilmsMapper.class);
    FilmDTO toDto(Film film);
    List<FilmDTO> toDTOList(List<Film> films);

    @Mapping(target = "id", ignore = true)
    @Mapping(target="episodeId", source="episode_id")
    @Mapping(target="openingCrawl", source="opening_crawl")
    @Mapping(target="releaseDate", source="release_date")
    Film toEntity(FilmResponse filmResponse);
    List<Film> toEntityList(List<FilmResponse> filmsResponse);
}
