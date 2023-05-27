package com.lucasfilm.starwars.infrastructure.dto;

import com.lucasfilm.starwars.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonFilmsDTO {
    private String name;
    private int filmCount;
    private List<String> filmTitles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFilmCount() {
        return filmCount;
    }

    public void setFilmCount(int filmCount) {
        this.filmCount = filmCount;
    }

    public List<String> getFilmTitles() {
        return filmTitles;
    }

    public void setFilmTitles(List<String> filmTitles) {
        this.filmTitles = filmTitles;
    }

}
