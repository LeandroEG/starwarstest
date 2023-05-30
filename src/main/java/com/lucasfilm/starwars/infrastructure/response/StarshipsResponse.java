package com.lucasfilm.starwars.infrastructure.response;

import java.util.List;

public class StarshipsResponse {

    private String next;

    private List<StarshipResponse> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<StarshipResponse> getResults() {
        return results;
    }

    public void setResults(List<StarshipResponse> results) {
        this.results = results;
    }
}
