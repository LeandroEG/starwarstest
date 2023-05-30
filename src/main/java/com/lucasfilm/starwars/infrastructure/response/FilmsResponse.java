package com.lucasfilm.starwars.infrastructure.response;

import java.util.List;

public class FilmsResponse {

    private String next;
    private List<FilmResponse> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<FilmResponse> getResults() {
        return results;
    }

    public void setResults(List<FilmResponse> results) {
        this.results = results;
    }
}
