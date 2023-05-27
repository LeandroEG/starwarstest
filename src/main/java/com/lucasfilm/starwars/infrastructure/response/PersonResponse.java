package com.lucasfilm.starwars.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonResponse {
    private String name;
    private String height;
    private String mass;
    private String hairColor;
    private String skinColor;
    private String eyeColor;
    private String birthYear;
    private String gender;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private List<String> starships;
    private String created;
    private String edited;
    private String url;

    // Constructor, getters y setters

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("mass")
    public String getMass() {
        return mass;
    }

    @JsonProperty("hair_color")
    public String getHairColor() {
        return hairColor;
    }

    @JsonProperty("skin_color")
    public String getSkinColor() {
        return skinColor;
    }

    @JsonProperty("eye_color")
    public String getEyeColor() {
        return eyeColor;
    }

    @JsonProperty("birth_year")
    public String getBirthYear() {
        return birthYear;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("homeworld")
    public String getHomeworld() {
        return homeworld;
    }

    @JsonProperty("films")
    public List<String> getFilms() {
        return films;
    }

    @JsonProperty("species")
    public List<String> getSpecies() {
        return species;
    }

    @JsonProperty("vehicles")
    public List<String> getVehicles() {
        return vehicles;
    }

    @JsonProperty("starships")
    public List<String> getStarships() {
        return starships;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("edited")
    public String getEdited() {
        return edited;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
}
