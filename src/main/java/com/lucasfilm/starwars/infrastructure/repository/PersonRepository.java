package com.lucasfilm.starwars.infrastructure.repository;

import com.lucasfilm.starwars.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Método que recupera un personaje por su URL
     * @param url
     *        URL del personaje
     * @return Person
     *         Objeto personaje
     */
    Optional<Person> findByUrl(String url);


}
