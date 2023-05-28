package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {

    /**
     * Método que devuelve una nave por su URL
     * @param url
     *        URL de la nave
     * @return Starship
     *         Objeto nave
     */
    Optional<Starship> findByUrl(String url);
}
