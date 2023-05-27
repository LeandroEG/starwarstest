package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE :characterUrl MEMBER OF f.characters")
    List<Film> findByCharacterIdsContaining(@Param("characterUrl") String characterUrl);
}
