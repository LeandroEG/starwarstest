package com.lucasfilm.starwars.infrastructure.response;

import java.util.List;

public class StarshipsResponse {

    private List<StarshipResponse> results;

    public List<StarshipResponse> getResults() {
        return results;
    }

    public void setResults(List<StarshipResponse> results) {
        this.results = results;
    }
}
