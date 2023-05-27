package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}
