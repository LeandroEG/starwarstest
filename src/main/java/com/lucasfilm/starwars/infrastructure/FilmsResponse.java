package com.lucasfilm.starwars.infrastructure;

import java.util.List;

public class FilmsResponse {

    private List<FilmResponse> results;

    public List<FilmResponse> getResults() {
        return results;
    }

    public void setResults(List<FilmResponse> results) {
        this.results = results;
    }
}
