package com.lucasfilm.starwars.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int episodeId;
    @Column(length = 5000)
    private String openingCrawl;
    private String director;
    private String producer;
    private String releaseDate;

    @ElementCollection
    @CollectionTable(name = "film_characters", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "character")
    private List<String> characters;

    @ElementCollection
    @CollectionTable(name = "film_planets", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "planet")
    private List<String> planets;

    @ElementCollection
    @CollectionTable(name = "film_starships", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "starship")
    private List<String> starships;

    @ElementCollection
    @CollectionTable(name = "film_vehicles", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "vehicle")
    private List<String> vehicles;

    @ElementCollection
    @CollectionTable(name = "film_species", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "species")
    private List<String> species;

    private String created;
    private String edited;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}