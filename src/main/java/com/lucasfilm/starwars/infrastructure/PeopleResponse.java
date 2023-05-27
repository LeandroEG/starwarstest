package com.lucasfilm.starwars.infrastructure;

import java.util.List;

public class PeopleResponse {
    private List<PersonResponse> results;

    public List<PersonResponse> getResults() {
        return results;
    }

    public void setResults(List<PersonResponse> results) {
        this.results = results;
    }
}
