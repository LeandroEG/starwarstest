package com.lucasfilm.starwars.infrastructure.mappers;

import com.lucasfilm.starwars.domain.Person;
import com.lucasfilm.starwars.infrastructure.dto.PersonDTO;
import com.lucasfilm.starwars.infrastructure.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeopleMapper {
    PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);
    PersonDTO toDto(Person person);
    List<PersonDTO> toDTOList(List<Person> people);

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonResponse peopleResponse);
    List<Person> toEntityList(List<PersonResponse> peopleResponse);
}
