package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {

    Optional<Starship> findByUrl(String url);
}
