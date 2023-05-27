package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {

}
